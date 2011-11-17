package com.apo.operator;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.apo.debug.Log;
import com.apo.mysql.Server;
import com.apo.mysql.exception.DatabaseNotFoundException;
import com.apo.operator.exception.InvalidColumnsException;
import com.apo.operator.exception.NoHeadException;
import com.apo.operator.exception.NoIDExistsException;
import com.apo.operator.exception.NoRevisionExistsException;
import com.apo.operator.exception.UnmarkedDeleteException;

/**If you have a revisable database of a certain design, you can use this class.
 * 
 * Table design features: 
 * * has a HEAD column that accepts 0 (false) or 1 (true) - HEAD marks the current "official" copy of an entry; think Version Control
 * * has a deleted marker column that accepts 0 (false) or 1 (true) - Deleted marks whether a user has chosen to non-destructively delete an entry; this is applied to all revisions of an entry with markDelete()
 * * has a revision id column and an item id column
 * 
 * While the DBOperator methods update(), insert() and delete() are available to you, use them with caution. Better yet, don't if the table doesn't seem to apply.
 * query() is safe to use.
 * 
 * @author Kevin Panuelos
 *
 */
public class RevisableDBOperator extends DBOperator {
	
	/**The default id assigned to a new revision**/
	public static final int DEFAULT_FIRST_REVISION_ID = 1;
	
	public RevisableDBOperator(String username, String password, String url, String dbName) throws DatabaseNotFoundException, SQLException {
		super (username, password, url, dbName);
	}
	public RevisableDBOperator(String username, char[] password, String url, String dbName) throws DatabaseNotFoundException, SQLException {
		super (username, password, url, dbName);
	}
	public RevisableDBOperator(Server db) {
		super (db);
	}
	
	/**When creating a new item, don't put in the revision id, item id, head and
	 * deleted fields into the Map of values; however it won't add columns from tables that
	 * reference the table contained the tableName parameter. For that, use updateLinkedTable()
	 * 
	 * @param tableName The name of the table where you'll insert the new item
	 * @param idColumn The name of the column that holds the entry ids
	 * @param revIdColumn The name of the column that holds the revision ids
	 * @param headColumn The name of the column that holds the head marker
	 * @param deleteMarkerColumn The name of the column that holds the delete marker
	 * @param values The Map (key-value pair) of pertinent values for the specific table; the column name is the key, the value is the corresponding value to the column name
	 * @return returns the id number that was created
	 * @throws SQLException Whenever a conflict arises/SQL operation failure
	 * @throws InvalidColumnsException Whenever the revision id, item id, head or deleted fields are in the map of values, this is thrown. Perhaps you were thinking of doing a different operation if you encounter this.
	 */
	public int newItem (String tableName, String idColumn, String revIdColumn, String headColumn, String deleteMarkerColumn, Map values) throws SQLException, InvalidColumnsException, NoIDExistsException {
		//check if the keySet has revId, itemId, head and deleted fields, then throw an InvalidColumnsException if at least one of them exists
		if (this.hasIllegalKeys(values, idColumn, revIdColumn, headColumn, deleteMarkerColumn)) {
			throw new InvalidColumnsException();
		}
		//check the table for the max ID number and add 1 to it
		int newId;
		try {
			Log.debugMsg(TAG, "Let's see if the table has IDs already and get the largest one");
			newId = this.getMaxId(tableName, idColumn) + 1;
		}
		catch (NoIDExistsException ex) {
			Log.debugMsg(TAG, "No, it seems that the table doesn't have IDs yet, but don't worry, there's a first time for everything.");
			newId = 1;
		}
		values.put(idColumn, newId);
		//assign revision number to 1
		values.put(revIdColumn, DEFAULT_FIRST_REVISION_ID);
		//assign the revision to head
		values.put(headColumn, TRUE);
		//and just to be sure, set Delete marker to false
		values.put(deleteMarkerColumn, FALSE);
		//insert whole thing into tableName
		this.insert(tableName, values);
		
		return newId;
	}
			
	/**When creating a new revision of an existing item, don't put in the revision id,
	 * item id, head and deleted fields into the Map of values; also creates new revisions for tables linked to the main
	 * table (via foreign keys) but does not accept keys (columns) in the values HashMap parameter that belong to those linked tables
	 * 
	 * In order to update the values for those linked tables, use updateLinkedTable(), but only after using newRevision()
	 * 
	 * @param tableName The name of the table where you'll insert the new item
	 * @param uniqueId The unique id of the item to be made a new revision of
	 * @param idColumn The name of the column holding the unique ids
	 * @param revIdColumn The name of the column holding the revision ids
	 * @param headColumn The name of the column holding the head status
	 * @param deletedColumn The name of the column holding the deleted marker status
	 * @param values The Map (key-value pair) of pertinent values for a specific table; key are column names, values are column values
	 * @return the new revision id for a certain record
	 * @throws NoIDExistsException No id exists for that table
	 * @throws NoHeadException No head exists for that id (this should not happen!)
	 * @throws InvalidColumnsException When the revision id, item id, head or deleted fields are put in the Map of values, this is thrown.
	 * @throws SQLException There may be something wrong operationally in the SQL syntax/server
	 * @throws NoRevisionExistsException The revision you're looking for may not exist (in this case, the current HEAD revision that will be set to false)
	 */
	public int newRevision (String tableName, int uniqueId, String idColumn, String revIdColumn, String headColumn, String deletedColumn, Map values) throws NoIDExistsException, NoHeadException, InvalidColumnsException, SQLException, NoRevisionExistsException {
		
		//check for invalid keys
		if (this.hasIllegalKeys(values, revIdColumn, idColumn, headColumn, deletedColumn)) {
			Log.errorMsg(TAG, "Y U PUT " + headColumn + ", " + idColumn + " OR " + revIdColumn + " COLUMNS TO VALUES PARAMETER?!");
			throw new InvalidColumnsException();
		}
		
		//get the original data (in case there are missing columns in the HashMap given)
		Log.debugMsg(TAG, "Retrieving original copy in case of missing columns...");
		HashMap originalValues = this.getHeadMap(tableName, uniqueId, idColumn, revIdColumn, headColumn);
		for (Object column : originalValues.keySet()) {
			if (!column.toString().equalsIgnoreCase(headColumn) || column.toString().equalsIgnoreCase(revIdColumn) || column.toString().equals(headColumn)) {
				for (Object valuesKey : originalValues.keySet()) {
					if (values.containsKey(valuesKey.toString())) {
						continue;
					}
					else {
						Log.debugMsg(TAG, "Unspecified column " + column + " found. Adding original value...");
						values.put(column, originalValues.get(column));
					}
				}
			}
		}
		
		//check the table if the uniqueId exists, throw NoIDExistsException if the ID cannot be found
		if (hasId(tableName, uniqueId, idColumn)) {
			//query for the HEAD and update() that value to false, if no head is found to be true, throw NoHeadException
			HashMap<String, Integer> headReset = new HashMap<String, Integer>();
			headReset.put(headColumn, FALSE);
			headReset.put(deletedColumn, FALSE);
			try {
				this.update(tableName, headReset, tableName + "." + headColumn + " = " + TRUE);
			}
			catch (SQLException ex) {
				throw new NoHeadException();
			}
			
			//check the table for the max revId number and add 1 to it
			int newRevId = this.getMaxId(tableName, idColumn) + 1;
			
			//put the uniqueId, new revId and head values to the values map
			values.put(idColumn, uniqueId);
			values.put(revIdColumn, newRevId);
			values.put(headColumn, TRUE);
			
			//insert the whole thing into tableName
			this.insert(tableName, values);
			this.newLinkedTablesRevision(tableName, uniqueId, idColumn, newRevId, revIdColumn);
			
			return newRevId;
		}
		else {
			throw new NoIDExistsException();
		}
				
		
	}
	
	/**Creates new revisions for tables linked to another table and copies their existing information.
	 * 
	 * To update the information in these linked tables, use updateLinkedTableItem() or insertLinkedTableItem()
	 * 
	 * @param tableName The name of the original table where a new revision was made
	 * @param uniqueId The id that is being referenced by one of the linked tables' foreign keys
	 * @param pkIdColumn The column in the original table that holds the id
	 * @param revId The revision id belonging to the original table that is referenced by one of the linked tables' foreign keys
	 * @param pkRevIdColumn The name of the column that holds the revision id's in the original table
	 * @throws SQLException There may be a problem in the sql server or syntax error in a statement
	 */
	private void newLinkedTablesRevision (String tableName, int uniqueId, String pkIdColumn, int revId, String pkRevIdColumn) throws SQLException {
		ResultSet exportedKeys = getForeignReferences(tableName);
				
		String tempFkTableName = null; //temporarily holds the value for the foreign key's table name
		String tempFkRevIdColumn = null;
		String tempFkIdColumn = null;
		
		while (exportedKeys.next()) {
			
			//check if a foreign key references pkIdColumn or pkRevIdColumn
			String pkColumnName = exportedKeys.getString("PKCOLUMN_NAME");
			if (!pkColumnName.equals(pkIdColumn) || !pkColumnName.equals(pkRevIdColumn)) {
				Log.debugMsg(TAG, "This foreign key does not reference " + pkIdColumn + " nor " + pkRevIdColumn);
				tempFkTableName = null;
				continue;
			}
			else {
				Log.debugMsg(TAG, "This foreign key references " + tableName + "." + exportedKeys.getString("FKCOLUMN_NAME"));
				
				//assign id column or rev id column names to variables (depending on which ones they reference)
				if (pkColumnName.equalsIgnoreCase(pkRevIdColumn)) {
					tempFkRevIdColumn = exportedKeys.getString("FKCOLUMN_NAME");
				}
				else if (pkColumnName.equalsIgnoreCase(pkIdColumn)) {
					tempFkIdColumn = exportedKeys.getString("FKCOLUMN_NAME");
				}
				
				if (tempFkTableName == null) { 
					//if the temporary fk table name is null, then no point of comparison is available yet
					tempFkTableName = exportedKeys.getString("FKTABLE_NAME");
				}
				else if (exportedKeys.getString("FKTABLE_NAME").equalsIgnoreCase(tempFkTableName)) { 
					//if temporary fk table is not null, then a pre-existing table name already exists, and must be equal in order to...
					//commence the "update" (more like replicate and increment one on it)
					
					Log.debugMsg(TAG, "Commencing update to " + exportedKeys.getString("FKTABLE_NAME"));
					//replicate the existing version of the record
					String whereCondition = tempFkIdColumn + " = " + uniqueId + " AND "+ tempFkRevIdColumn + " = " + (revId-1);
					ResultSet queryResult = this.query(false, null, exportedKeys.getString("FKTABLE_NAME"), whereCondition, null, null, null);
					ArrayList<HashMap> data = this.getMapOfRows(queryResult);
					if (data != null) {
						for (HashMap row : data) {
							row.remove(tempFkRevIdColumn);
							row.put(tempFkRevIdColumn, revId);
							this.insert(tempFkTableName, row); //confirms the new revision
						}
						Log.debugMsg(TAG, tempFkTableName + " has been updated.");
						tempFkTableName = null; //reset the temp fk table name because a new fk table will be looked up					
						tempFkIdColumn = null; //reset the temp fk id column because a new fk table will be looked up
						tempFkRevIdColumn = null; //reset the temp fk rev id column because a new fk table will be looked up
					}
				}
			}			
		}
		
		exportedKeys.close();
		
	}
	
	/**Adds an item to a linked table's entries (eg, if a Contacts table has 12 existing entries assigned to 1 person
	 * whose information can be seen in a separate table called Person, and you want to add a 13th entry, you can use this method)
	 * 
	 * @param linkedTableName The name of the linked table
	 * @param idReference The id referencing the main table
	 * @param idReferenceColumn The column name holding the id that references the main table
	 * @param revIdReference The revision id referencing the main table's revision id; if null, the revision id is disregarded
	 * @param revIdReferenceColumn The column name holding the revision id that references the main table; if null, this is disregarded
	 * @param idColumn The name of the column holding the table's own id's (eg, a contact entry is tied to a person, but a person can have multiple contact entries
	 * therefore there is a need to have a separate id columns for these multiple entries)
	 * @param values The value of the entries that should not contain idReferenceColumn, revIdReferenceColumn and idColumn
	 * @throws SQLException Something happened with the SQL server's connection, or perhaps a syntax error occurred
	 * @throws NoIDExistsException Thrown when an ID does not seem to exist such that the entry is tied to an entry in the main table
	 */
	public void addLinkedTableItem (String linkedTableName, int idReference, String idReferenceColumn, Integer revIdReference, String revIdReferenceColumn, String idColumn, Map values) throws SQLException, InvalidColumnsException {
		boolean hasRevId;
		
		if (revIdReferenceColumn == null || revIdReference <= 0 || revIdReference == null) {
			Log.debugMsg(TAG, "Revision id does not exist.");
			hasRevId = false;
		}
		else {
			Log.debugMsg(TAG, "Revision id exists.");
			hasRevId = true;
		}
		
		//check for illegal keys in values map
		if (hasRevId) {
			if (this.hasIllegalKeys(values, idReferenceColumn, revIdReferenceColumn, idColumn)) {
				Log.errorMsg(TAG, "Y U PUT " + idReferenceColumn + " OR " + revIdReferenceColumn + " OR " + idColumn + " COLUMNS TO VALUES PARAMETER?!");
				throw new InvalidColumnsException();
			}
		}
		else {
			if (this.hasIllegalKeys(values, idReferenceColumn, idColumn)) {
				Log.errorMsg(TAG, "Y U PUT " + idReferenceColumn + " OR " + idColumn + " COLUMNS TO VALUES PARAMETER?");
				throw new InvalidColumnsException();
			}
		}
		
		//check if entries exist that reference the id of the original table
		//SELECT idReferenceColumn FROM linkedTableName WHERE linkedTableName.idReferenceColumn = idReference
		String whereCondition = linkedTableName + "." + idReferenceColumn + " = " + idReference;
		Log.debugMsg(TAG, "Entries exist under this id.");
			
		values.put(idReferenceColumn, idReference);
			
		if (hasRevId) {
			values.put(revIdReferenceColumn, revIdReference);
			whereCondition.concat(" AND " + linkedTableName + "." + revIdReferenceColumn + " = " + revIdReference);
		}
		
		try {
			int newId = this.getMaxId(linkedTableName, idColumn, whereCondition) + 1;
			values.put(idColumn, newId);
		}
		catch (NoIDExistsException ex) {
			values.remove(idColumn);
			values.put(idColumn, 1);
		}
		this.insert(linkedTableName, values);			
		
	}
	
	/**When creating a new item, or creating a new revision, you can't gain access to tables that reference
	 * these items safely. This method lets you do that.
	 * 
	 * @param linkedTableName The name of the linked table to be updated
	 * @param idReference The id of the item the linked table is referencing
	 * @param idReferenceColumn The column that contains the foreign key to the id of a main table
	 * @param revIdReference The revision id linked to the item being referenced in the linked table
	 * @param revIdReferenceColumn The column that contains the foreign key to the revision id of a main table; putting null will only update/insert a new id
	 * @param idRow The id of the item that the linked table assigns for data contained in values
	 * @param idColumn The label of the column containing the ids for the data contained in values
	 * @param values The Map of key-value pairs that make up the entries for each column in the linked table except for idReference, revIdReference and idRow
	 * @throws SQLException SQL server may be down, syntax error occurred, or something else unexpected happened.
	 * @throws InvalidColumnsException Invalid columns were placed in the Map of values
	 * @throws NoIDExistsException The linked record id does not exist and thus cannot be updated
	 */
	public void updateLinkedTableItem (String linkedTableName, int idReference, String idReferenceColumn, Integer revIdReference, String revIdReferenceColumn, int idRow, String idColumn, Map values) throws SQLException, InvalidColumnsException, NoIDExistsException {
		boolean hasRevId;
		
		//check if values have invalid keys (also check if revIdReference and/or revIdColumn is/are <=0 or null)
		if (revIdReferenceColumn == null || revIdReference <= 0 || revIdReference == null) {
			Log.debugMsg(TAG, "Revision id does not exist.");
			hasRevId = false;
		}
		else {
			Log.debugMsg(TAG, "Revision id exists.");
			hasRevId = true;
		}
		
		if (hasRevId) {
			if (this.hasIllegalKeys(values, idReferenceColumn, revIdReferenceColumn, idColumn)) {
				Log.errorMsg(TAG, "Y U PUT " + idReferenceColumn + " OR " + revIdReferenceColumn + " OR " + idColumn + " COLUMNS TO VALUES PARAMETER?!");
				throw new InvalidColumnsException();
			}
		}
		else {
			if (this.hasIllegalKeys(values, idReferenceColumn, idColumn)) {
				Log.errorMsg(TAG, "Y U PUT " + idReferenceColumn + " OR " + idColumn + " COLUMNS TO VALUES PARAMETER?");
				throw new InvalidColumnsException();
			}
		}
		
		//check if entries exist that reference the id of the original table
		//SELECT idReferenceColumn FROM linkedTableName WHERE linkedTableName.idReferenceColumn = idReference
		String whereCondition = linkedTableName + "." + idReferenceColumn + " = " + idReference;
		ResultSet queryResult = query(false, new String[] {idReferenceColumn}, linkedTableName, whereCondition, null, null, null);
		
		if(!queryResult.first()) {
			throw new NoIDExistsException();
		}
		else {
			Log.debugMsg(TAG, "Entries exist under this id.");
			queryResult.close();
			
			whereCondition = linkedTableName + "." + idReferenceColumn + " = " + idReference + " AND " + linkedTableName + "." + revIdReferenceColumn + " = " + revIdReference + " AND " + linkedTableName + "." + idColumn + " = " + idRow;
			this.update(linkedTableName, values, whereCondition);			
		}
		
	}	 
	
	/**Deletes a linked table item/record
	 * 
	 * @param linkedTableName The name of the linked table
	 * @param idReference The reference to the id of the main table
	 * @param idReferenceColumn The column containing the reference to the main table
	 * @param revIdReference The reference to the revId of the main table; putting null will let the method ignore revId
	 * @param revIdReferenceColumn The reference to the revId column of the linked table; putting null will let the method ignore revId
	 * @param idRow The id number for the record corresponding to the referenced id and revId that is to be deleted
	 * @param idColumn The name of the column holding the id's described in idRow
	 * @throws SQLException The SQL server may have been disconnected, or a syntax error occurred
	 */
	public void deleteLinkedTableItem (String linkedTableName, int idReference, String idReferenceColumn, Integer revIdReference, String revIdReferenceColumn, int idRow, String idColumn) throws SQLException {
		boolean hasRevId;
		//check if values have invalid keys (also check if revIdReference and/or revIdColumn is/are <=0 or null)
		if (revIdReferenceColumn == null || revIdReference <= 0 || revIdReference == null) {
			Log.debugMsg(TAG, "Revision id does not exist.");
			hasRevId = false;
		}
		else {
			Log.debugMsg(TAG, "Revision id exists.");
			hasRevId = true;
		}
		
		Log.debugMsg(TAG, "Deleting...");
		if (hasRevId) {
			this.delete(linkedTableName, idReferenceColumn + " = " + idReference + " AND " + revIdReferenceColumn + " = " + revIdReference + " AND " + idColumn + " = " + idRow);
		}
		else {
			delete(linkedTableName, idReferenceColumn + " = " + idReference + " AND " + idColumn + " = " + idRow);
		}
	}
	
	/**Copies the "information" or key-value pairs for each column that are present in the current (HEAD) revision of a record
	 * 
	 * @param tableName The name of the table to be looked up
	 * @param id The identifier of the record to be copied
	 * @param idColumn The label of the column holding the id
	 * @param revIdColumn The label of the column holding the revision ids
	 * @param headColumn The label of the column holding the head marker
	 * @return The HashMap representation of the HEAD revision of a record; null if no results come up
	 * @throws NoIDExistsException The ID does not seem to exist/cannot be found
	 * @throws NoRevisionExistsException The revision number specified does not seem to exist
	 * @throws SQLException There may be something wrong with the syntax/operational error
	 * @throws NoHeadException There may be no Head available for this series of records (which should NOT happen)
	 */
	protected HashMap getHeadMap (String tableName, int id, String idColumn, String revIdColumn, String headColumn) throws NoIDExistsException, NoRevisionExistsException, SQLException, NoHeadException {
		ResultSet result = this.retrieveHeadInfo(tableName, id, idColumn, revIdColumn, headColumn);
		Log.debugMsg(TAG, "Retrieving head information for copying...");
		ResultSetMetaData metadata = result.getMetaData();
		HashMap map = null;
		if (result.next()) {
			map = new HashMap();
			int columns = metadata.getColumnCount();
			int ctr = 1;
			Log.debugMsg(TAG, "Copying existing data");
			while (ctr <= columns) {
				String columnName = result.getMetaData().getColumnName(ctr);
				map.put(columnName, result.getObject(ctr));
				ctr++;
			}
			Log.debugMsg(TAG, "HashMap of values created.");
		}
		result.close();
		return map;
		
	}
	
	/**Looks up values Map for "illegal keys" as dictated by the illegalKeys parameter
	 * 
	 * @param values Key-value pair whose keys will be inspected
	 * @param illegalKeys The "illegal keys" that should not be present in the values Map
	 * @return true if there is at least one illegal key in the map of values, false if there are none
	 */
	private boolean hasIllegalKeys (Map values, Object... illegalKeys) {
		for (Object key : illegalKeys) {
			if (values.containsKey(key)) {
				Log.debugMsg(TAG, "Found illegal keys");
				return true;
			}
		}
		return false;
	}
	
	/**Check if a certain id's records is marked for deletion
	 * 
	 * @param tableName the name of the table to be looked up
	 * @param uniqueId the unique id tied to records that are to be checked
	 * @param idColumn the label of the id column for this table
	 * @param deleteMarkColumn the name of the deletion marker column (ie, a marker in the table that helps determine whether it has been marked for deletion (a value of 'true' or 1) or not ('false' or 0)
	 * @return whether the records associated with the uniqueId have been marked for deletion
	 * @throws InvalidColumnsException The columns were not found in this table
	 * @throws NoIDExistsException There is no id that fits the uniqueId parameter
	 * @throws SQLException Something's wrong with the server connection/columns/where condition/syntax
	 */
	public boolean isMarked (String tableName, int uniqueId, String idColumn, String deleteMarkColumn) throws InvalidColumnsException, NoIDExistsException, SQLException {
		String whereCondition = tableName + "." + idColumn + " = " + uniqueId;
		//SELECT tableName.deleteMarkColumn FROM tableName WHERE tableName.idColumn = uniqueId
		ResultSet result = query(false, new String[] {tableName + "." + deleteMarkColumn}, tableName, whereCondition, null, null, null);
		if (result.first()) {
			try {
				if (result.getBoolean(deleteMarkColumn)) {
					result.close();
					return true;
				}
				else {
					result.close();
					return false;
				}
			}
			catch (SQLException ex) {
				throw new InvalidColumnsException();
			}
			finally {
				result.close();
			}
		}
		else {
			throw new NoIDExistsException();
		}
	}	
	
	/**Mark a certain id for deletion (but not totally delete the entry containing the uniqueId; non-destructive "deletion")
	 * 
	 * @param tableName The name of the table to be looked up
	 * @param uniqueId The unique id that points to record(s) to be marked for deletion
	 * @param idColumn The name of the id column that holds the ids
	 * @param deleteMarkerColumn The name of the column holding the 'deleted' status markers
	 * @throws SQLException Operational/Syntax error in SQL backend
	 * @throws NoIDExistsException The ID does not exist
	 */
	public void markDelete (String tableName, int uniqueId, String idColumn, String deleteMarkerColumn) throws SQLException, NoIDExistsException {
		String whereCondition = tableName + "." + idColumn + " = " + uniqueId;
		Log.debugMsg(TAG, "Checking if this id exists in the table...");
		if (this.hasId(tableName, uniqueId, idColumn)) {
			Log.debugMsg(TAG, "Id is available. Commencing marking...");
			Log.debugMsg(TAG, "Packaging new delete property to HashMap");
			HashMap<String, Integer> deleteMarker = new HashMap<String, Integer>();
			deleteMarker.put(deleteMarkerColumn, TRUE);
			Log.debugMsg(TAG, "Marking entries...");
			this.update(tableName, deleteMarker, whereCondition);
		}
		else {
			throw new NoIDExistsException();
		}
		
	}
	
	/**Really (destructively) delete entries containing a certain unique id in a table; if the entries aren't marked for
	 * deletion, an exception is returned.
	 * 
	 * @param tableName The name of the table to be looked up
	 * @param uniqueId The unique id that points to the record(s) to be deleted permanently
	 * @param idColumn The name of the column that holds the id
	 * @param deleteMarkerColumn The name of the column that holds the delete marker; leaving this null will still throw an UnMarkedDeleteException
	 * @throws UnmarkedDeleteException thrown when the records you are trying to delete are not marked for deletion
	 * @throws NoIDExistsException the id does not exist
	 * @throws SQLException Operational/syntax error in SQL backend
	 * @throws InvalidColumnsException The columns that are being looked up doesn't apply to this table
	 */
	public void deleteMarked (String tableName, int uniqueId, String idColumn, String deleteMarkerColumn) throws UnmarkedDeleteException, NoIDExistsException, InvalidColumnsException, SQLException {
		//check if the deleted markers are set to true
		String whereCondition = tableName + "." + idColumn + " = " + uniqueId;
		Log.debugMsg(TAG, "Checking if the deleted marker is set to true...");
		if (this.isMarked(tableName, uniqueId, idColumn, deleteMarkerColumn)) {
			Log.debugMsg(TAG, "Commencing deletion");
			deleteLinked(tableName, uniqueId, idColumn);
			this.delete(tableName, whereCondition);
		}
		else {
			Log.errorMsg(TAG, "It seems the entry hasn't been marked for deletion. Cancelling deletion...");
			throw new UnmarkedDeleteException();
		}
	}
	
	private void deleteLinked (String tableName, int pkId, String pkIdColumn) throws SQLException {
		ResultSet exportedKeys = getForeignReferences(tableName);
	}
	
	
	private void deleteLinked (String tableName, int pkId, String pkIdColumn, int revPkId, String pkRevIdColumn) throws SQLException {
		ResultSet exportedKeys = getForeignReferences(tableName);
		
		String tempFkTableName = null; //temporarily holds the value for the foreign key's table name
		String tempFkRevIdColumn = null;
		String tempFkIdColumn = null;
		
		while (exportedKeys.next()) {
			
			//check if a foreign key references pkIdColumn or pkRevIdColumn
			String pkColumnName = exportedKeys.getString("PKCOLUMN_NAME");
			if (!pkColumnName.equals(pkIdColumn) || !pkColumnName.equals(pkRevIdColumn)) {
				Log.debugMsg(TAG, "This foreign key does not reference " + pkIdColumn + " nor " + pkRevIdColumn);
				tempFkTableName = null;
				continue;
			}
			else {
				/*Log.debugMsg(TAG, "This foreign key references " + tableName + "." + exportedKeys.getString("FKCOLUMN_NAME"));
				
				//assign id column or rev id column names to variables (depending on which ones they reference)
				if (pkColumnName.equalsIgnoreCase(pkRevIdColumn)) {
					tempFkRevIdColumn = exportedKeys.getString("FKCOLUMN_NAME");
				}
				else if (pkColumnName.equalsIgnoreCase(pkIdColumn)) {
					tempFkIdColumn = exportedKeys.getString("FKCOLUMN_NAME");
				}
				
				if (tempFkTableName == null) { 
					//if the temporary fk table name is null, then no point of comparison is available yet
					tempFkTableName = exportedKeys.getString("FKTABLE_NAME");
				}
				else if (exportedKeys.getString("FKTABLE_NAME").equalsIgnoreCase(tempFkTableName)) { 
					//if temporary fk table is not null, then a pre-existing table name already exists, and must be equal in order to...
					//commence the "update" (more like replicate and increment one on it)
					
					Log.debugMsg(TAG, "Commencing update to " + exportedKeys.getString("FKTABLE_NAME"));
					//replicate the existing version of the record
					String whereCondition = tempFkIdColumn + " = " + uniqueId + " AND "+ tempFkRevIdColumn + " = " + (revId-1);
					ResultSet queryResult = this.query(false, null, exportedKeys.getString("FKTABLE_NAME"), whereCondition, null, null, null);
					ArrayList<HashMap> data = this.getMapOfRows(queryResult);
					if (data != null) {
						for (HashMap row : data) {
							row.remove(tempFkRevIdColumn);
							row.put(tempFkRevIdColumn, revId);
							this.insert(tempFkTableName, row); //confirms the new revision
						}
						Log.debugMsg(TAG, tempFkTableName + " has been updated.");
						tempFkTableName = null; //reset the temp fk table name because a new fk table will be looked up					
						tempFkIdColumn = null; //reset the temp fk id column because a new fk table will be looked up
						tempFkRevIdColumn = null; //reset the temp fk rev id column because a new fk table will be looked up
					}
				}*/
			}
		}
		
		exportedKeys.close();
	}
	
	/**Deletes revisions tied to an id that aren't marked HEAD
	 * 
	 * @param tableName The table to be looked up
	 * @param uniqueId The id that is tied to the revisions
	 * @param idColumn The column name that holds the ids
	 * @param headMarkerColumn The column name that holds the HEAD indicator
	 * @throws SQLException There must have been an operational error/syntax error
	 */
	public void deleteNonHeadRevisions (String tableName, int uniqueId, String idColumn, String headMarkerColumn) throws SQLException {
		String whereCondition = tableName + "." + idColumn + " = " + uniqueId + " AND " + tableName + "." + headMarkerColumn + " = " + FALSE;
		Log.debugMsg(TAG, "Deleting non-head revisions...");
		this.delete(tableName, whereCondition);
	}
	
	/**Re-assigns a new HEAD record linked to a unique id
	 * 	
	 * @param tableName the table name to be looked up
	 * @param uniqueId the unique id of the record to be reverted
	 * @param idColumn The label of the column holding the ids
	 * @param newHeadRevId the EXISTING revision id that the record will be reverted to
	 * @param revIdColumn The label of the column holding the revision ids
	 * @param headMarkerColumn The label of the column holding the head markers
	 * @throws NoRevisionExistsException the revision id you put in this method does not exist
	 * @throws NoIDExistsException The id being requested doesn't even exist
	 * @throws NoHeadException The current head of an item cannot be found (THIS SHOULD NOT HAPPEN)
	 * @throws SQLException There may be an operational/syntax error. This may happen because an error occurred restoring the old head (SHOULD NOT HAPPEN THOUGH)
	 */
	public void revert (String tableName, int uniqueId, String idColumn, int newHeadRevId, String revIdColumn, String headMarkerColumn) throws NoRevisionExistsException, NoIDExistsException, SQLException, NoHeadException {
		//get the old head id in case it fails later
		int formerHeadRevId = getHeadRevId(tableName, uniqueId, idColumn, revIdColumn, headMarkerColumn);
		
		//convert all head markers of the entries under this id to false, just as a precaution
		String whereCondition = tableName + "." + idColumn + " = " + uniqueId;
		HashMap<String, Integer> values = new HashMap<String, Integer>();
		values.put(headMarkerColumn, FALSE);
		Log.debugMsg(TAG, "Converting all head markers to false for id " + uniqueId);
		try {
			this.update(tableName, values, whereCondition);
		} catch (SQLException e) {
			throw new NoIDExistsException();
		}
		
		//find the entry with newHeadRevId as the entry under idColumn and mark headMarkerColumn as TRUE
		whereCondition.concat(" AND " + revIdColumn + " = " + newHeadRevId);
		values.clear();
		values.put(headMarkerColumn, TRUE);
		Log.debugMsg(TAG, "Marking new head revision");
		try {
			this.update(tableName, values, whereCondition);
		} catch (SQLException e) {
			//re-set the head to the former head revision
			whereCondition = tableName + "." + idColumn + " = " + uniqueId + " AND " + revIdColumn + " = " + formerHeadRevId;
			this.update(tableName, values, whereCondition);
			throw new NoRevisionExistsException();
		}
	}
	
	/**Retrieves records from a table with a filter
	 * 
	 * @param filter The string filter that is compared to every column specified in the columns parameter
	 * @param tableName The name of the table to be looked up
	 * @param columns The columns of the table that will be searched using the filter
	 * @return a ResultSet consisting of all rows that fit the filter; returns null if the columns parameter is null
	 * @throws SQLException if an operational/syntax error occurred...
	 */
	public ResultSet retrieveWithFilter (String filter, String tableName, String[] columns) throws SQLException {
		StringBuilder whereCondition = new StringBuilder();
		if (columns != null) {
			for (int ctr = 0; ctr < columns.length; ctr++) {
				whereCondition.append(tableName + "." + columns[ctr] + " = " + "'%" + filter + "%'");
				if (ctr < columns.length - 1) {
					whereCondition.append(" OR ");
				}
			}
			Log.debugMsg(TAG, whereCondition.toString());
			return query(false, null, tableName, whereCondition.toString(), null, null, null);
		}
		else return null;
		
	}
	
	/**Convenience method for retrieveHeadInfo(); retrieves the latest edition of a piece of information
	 * 
	 * @param tableName the table name to be looked up
	 * @param uniqueId the unique id attached to the record to be retrieved
	 * @return the entire record in a ResultSet object
	 * @throws NoIDExistsException when the ID does not exist in the table
	 * @throws NoHeadException the HEAD record does not exist
	 * @throws SQLException 
	 * @throws NoRevisionExistsException 
	 */
	public ResultSet retrieve (String tableName, int uniqueId, String idColumn, String revIdColumn, String headColumn) throws NoIDExistsException, NoRevisionExistsException, SQLException, NoHeadException {
		return retrieveHeadInfo (tableName, uniqueId, idColumn, revIdColumn, headColumn);
	}
	
	/**Retrieves the version of the record where its HEAD field is marked true
	 * 
	 * @param tableName the table name to be looked up
	 * @param uniqueId the unique id attached to the record to be retrieved
	 * @param idColumn the column name holding the unique id's
	 * @param revIdColumn the column name holding the revisions
	 * @param headColumn the column name of the HEAD record determiner
	 * @return the entire record in a ResultSet object
	 * @throws SQLException the server may not be working well
	 * @throws NoRevisionExistsException there is no revision column/contents
	 * @throws NoHeadException the HEAD record does not exist
	 * @throws NOIDExistsException when the ID does not exist in the table
	 */
	public ResultSet retrieveHeadInfo (String tableName, int uniqueId, String idColumn, String revIdColumn, String headColumn) throws NoIDExistsException, NoRevisionExistsException, SQLException, NoHeadException {
		int headRevId = this.getHeadRevId(tableName, uniqueId, idColumn, revIdColumn, headColumn);
		//SELECT * FROM tableName WHERE tableName.idColumn = uniqueId AND tableName.revIdColumn = headRevId;
		String whereCondition = tableName + "." + idColumn + " = " + uniqueId + " AND " + tableName + "." + revIdColumn + " = " + headRevId;
		Log.debugMsg(TAG, "Retrieving the head for this id...");
		return query(false, null, tableName, whereCondition, null, null, null);
	}
	
	/**Retrieves a specific version of the record, regardless of whether its HEAD field is true or not
	 * 
	 * @param tableName the table name to be looked up
	 * @param uniqueId the unique id attached to the record to be retrieved
	 * @param idColumn the name of the column that will contain the id's
	 * @param revId the revision id attached to the version of a record to be retrieved
	 * @param revIdColumn the revision id column label
	 * @return the entire record in a ResultSet object
	 * @throws SQLException If the query is not successful (ID or revID may not exist)
	 */
	public ResultSet retrieveRevisionInfo (String tableName, int uniqueId, String idColumn, int revId, String revIdColumn) throws SQLException {
		//SELECT * FROM tableName WHERE tableName.idColumn = uniqueId AND tableName.revIdColumn = revId
		String whereCondition = tableName + "." + idColumn + " = " + uniqueId + " AND " + tableName + "." + revIdColumn + " = " + revIdColumn;
		Log.debugMsg(TAG, "Retrieving revision " + revId + " for this id.");
		return query(false, null, tableName, whereCondition, null, null, null);
	}
	
	
	/**Determines whether a specific id number exists for a table's id column
	 * 	
	 * @param tableName The name of the table to be looked up
	 * @param uniqueId The unique id to be searched for in the id column
	 * @param idColumn The id column name associated with the table
	 * @return whether the id number exists on the table or not
	 * @throws SQLException there may be non-existent columns, faulty statements, non-existent table name, server is non-operational
	 */
	protected boolean hasId (String tableName, int uniqueId, String idColumn) throws SQLException {
		String whereCondition = tableName + "." + idColumn + " = " + uniqueId;
		ResultSet result = query(false, new String[] {tableName + "." + idColumn}, tableName, whereCondition, null, null, null);
		if (result.first()) {
			if (result.getInt(idColumn) == uniqueId) {
				Log.debugMsg(TAG, "Yes, there is an id");
				result.close();
				return true;
			}
			else {
				Log.debugMsg(TAG, "No, the id doesn't exist.");
				result.close();
				return false;
			}
		}
		else {
			Log.debugMsg(TAG, "No results at all.");
			return false;
		}
	}
	
	/**Returns the maximum number of an id column in a table
	 * 
	 * @param tableName The table that will be looked up
	 * @param idColumn The column of the table that will contain integers; the largest number itself is returned
	 * @return the largest integer value for the id column
     * @throws SQLException Throws an SQL Exception if either server isn't connected properly, or table/id column may not exist
	 * @throws NoIDExistsException Throws this if the ID column doesn't seem to have any contents
	 */
	public int getMaxId (String tableName, String idColumn) throws SQLException, NoIDExistsException {
		return getMaxId (tableName, idColumn, null);
	}
	
	/**Returns the maximum number of an id column in a table
	 * 
	 * @param tableName The table that will be looked up
	 * @param idColumn The column of the tabl that will contain integers; the largest number itself is returned
	 * @param where use this parameter to drill down into another id column, if available; null will have the same effect as getMaxId (tableName, idColumn)
	 * @return the largest integer value for the specified id column
	 * @throws SQLException Throws an SQL Exception if teither server isn't connected properly, or table/id column may not exist
	 * @throws NoIDExistsException Throws this if the ID column doesn't seem to have any contents
	 */
	protected int getMaxId (String tableName, String idColumn, String where) throws SQLException, NoIDExistsException {
		Statement request = db.getStatement();
		//SELECT MAX(tableName.idColumn) FROM tableName WHERE where;
		String command;
		if (where != null) {
			command = "SELECT MAX(" + tableName + "." + idColumn + ") FROM " + tableName + " WHERE " + where + ";";
		}
		else {
			command = "SELECT MAX(" + tableName + "." + idColumn + ") FROM " + tableName + ";";
		}
		Log.debugMsg(TAG, "Retrieving max id");
		ResultSet result = request.executeQuery(command);
		if (result.first()) {
			Log.debugMsg(TAG, "Looks like we have a result... returning max id");
			int maxId = result.getInt(idColumn);
			result.close();
			return maxId;
		}
		else {
			Log.debugMsg(TAG, "Looks like the ID doesn't exist");
			throw new NoIDExistsException();
		}
	}
	
	/**Retrieves the revision id with the largest integer value
	 * 
	 * @param tableName The table name to be looked up
	 * @param id the unique id attached to the table that holds a record/multiple records
	 * @param idColumn The specific name of the id column
	 * @param revIdColumn the specific name of the revision id column
	 * @return the maximum integer value of the revision id column specified
	 * @throws NoRevisionExistsException if the revision id column specified does not even exist in the table
	 * @throws NoIDExistsException if the unique id specified does not exist for a table 
	 * @throws SQLException if the SQL Server isn't working, or an invalid statement was executed
	 */
	protected int getMaxRevId (String tableName, int id, String idColumn, String revIdColumn) throws NoRevisionExistsException, NoIDExistsException, SQLException {
		Statement request = db.getStatement();
		//SELECT MAX(tableName.revIdColumnName) FROM tableName WHERE tableName.idColumnName = id;
		String command = "SELECT MAX(" + tableName + "." + revIdColumn + ") FROM " + tableName + "WHERE " + tableName + "." + idColumn + " = " + id;
		command = command.concat(";");
		Log.debugMsg(TAG, "Executing getMaxRevId() query...");
		ResultSet result = request.executeQuery(command);
		if (result.first()) {
			try {
				Log.debugMsg(TAG, "Returning getMaxRevId() result...");
				return result.getInt(revIdColumn);
			}
			catch (SQLException ex) {
				Log.debugMsg(TAG, "getMaxRevId() result failed");
				throw new NoRevisionExistsException();
			}
		}
		else {
			throw new NoIDExistsException();
		}
	}
	
	/**Get the revision number containing the official version of a record (the 'head')
	 * 
	 * @param tableName The name of the table to be looked up
	 * @param id The specific id number of the record in the table
	 * @param idColumn The label of the id column containing the records
	 * @param revIdColumn The label of the revision id column
	 * @param headColumn The label of the head determiner column
	 * @return The revision id value of the HEAD record
	 * @throws NoRevisionExistsException The revision column does not exist
	 * @throws SQLException The statement was faulty/sql server is not operational/multiple columns do not exist/table does not exist
	 * @throws NoHeadException There is no HEAD record in the result (usually this means there are no records for that id yet, or the ID does not exist)
	 */
	protected int getHeadRevId (String tableName, int id, String idColumn, String revIdColumn, String headColumn) throws SQLException, NoHeadException, NoRevisionExistsException {
		//SELECT tableName.revIdColumnName FROM tableName WHERE headColumn='true'
		String whereCondition = headColumn + " = " + TRUE;
		ResultSet result = this.query(false, new String[] {tableName + "." + revIdColumn}, tableName, whereCondition, null, null, null);
		Log.debugMsg(TAG, "Query success!");
		if (result.first()) {
			try {
				Log.debugMsg(TAG, "Returning the head rev id...");
				int headRevId = result.getInt(revIdColumn);
				result.close();
				return headRevId;
			}
			catch (SQLException ex) {
				Log.errorMsg(TAG, "head rev id return failed.");
				result.close();
				throw new NoRevisionExistsException();
			}
			
		}
		else {
			Log.debugMsg(TAG, "No results from the ResultSet... there is no head revision id.");
			throw new NoHeadException();
		}
		
	}
	
	/**Get the foreign keys that reference columns in the table specified in tableName
	 * 
	 * More information can be found by looking at java.sql.DatabaseMetaData's getExportedKeys() method.
	 * 
	 * @param tableName The table whose columns will be referenced by the foreign keys 
	 * @return The foreign keys that reference the columns in the table tableName
	 * @throws SQLException Server may be down, or a request has a syntax error
	 */
	private ResultSet getForeignReferences (String tableName) throws SQLException {
		DatabaseMetaData metadata = this.db.getConnection().getMetaData();
		ResultSet exportedKeys = metadata.getExportedKeys(this.db.getConnection().getCatalog(), this.db.getSchemaName(), tableName);
		Log.debugMsg(TAG, "Got foreign references to columns in " + tableName);
		return exportedKeys;
	}
	
}
