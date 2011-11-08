package com.apo.operator;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
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
		
	public RevisableDBOperator(String username, String password, String url, String dbName) throws DatabaseNotFoundException, SQLException {
		super (username, password, url, dbName);
	}
	public RevisableDBOperator(String username, char[] password, String url, String dbName) throws DatabaseNotFoundException {
		super (username, password, url, dbName);
	}
	public RevisableDBOperator(Server db) {
		super (db);
	}
	
	/**When creating a new item, don't put in the revision id, item id, head and
	 * deleted fields into the Map of values 
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
		values.put(revIdColumn, 1);
		//assign the revision to head
		values.put(headColumn, TRUE);
		//and just to be sure, set Delete marker to false
		values.put(deleteMarkerColumn, FALSE);
		//insert whole thing into tableName
		this.insert(tableName, values);
		
		return newId;
	}
	
	
	/**When creating a new revision of an existing item, don't put in the revision id,
	 * item id, head and deleted fields into the Map of values
	 * 
	 * @param tableName The name of the table where you'll insert the new item
	 * @param uniqueId The unique id of the item to be made a new revision of
	 * @param idColumn The name of the column holding the unique ids
	 * @param revIdColumn The name of the column holding the revision ids
	 * @param headColumn The name of the column holding the head status
	 * @param deletedColumn The name of the column holding the deleted marker status
	 * @param values The Map (key-value pair) of pertinent values for a specific table; key are column names, values are column values
	 * @return whether the operation was successful or not
	 * @throws NoIDExistsException No id exists for that table
	 * @throws NoHeadException No head exists for that id (this should not happen!)
	 * @throws InvalidColumnsException When the revision id, item id, head or deleted fields are put in the Map of values, this is thrown.
	 * @throws SQLException There may be something wrong operationally in the SQL syntax/server
	 * @throws NoRevisionExistsException The revision you're looking for may not exist (in this case, the current HEAD revision that will be set to false)
	 */
	public boolean newRevision (String tableName, int uniqueId, String idColumn, String revIdColumn, String headColumn, String deletedColumn, Map values) throws NoIDExistsException, NoHeadException, InvalidColumnsException, SQLException, NoRevisionExistsException {
		
		//check for invalid keys
		if (this.hasIllegalKeys(values, revIdColumn, idColumn, headColumn, deletedColumn)) {
			Log.errorMsg(TAG, "Y U PUT " + headColumn + ", " + idColumn + " OR " + revIdColumn + " COLUMNS TO VALUES PARAMETER?!");
			throw new InvalidColumnsException();
		}
		
		//get the original data (in case there are missing columns in the HashMap given)
		Log.debugMsg(TAG, "Retrieving original copy in case of missing columns...");
		HashMap originalValues = this.copyHeadInformation(tableName, uniqueId, idColumn, revIdColumn, headColumn);
		for (Object column : originalValues.keySet()) {
			if (values.containsKey(column)) {
				continue;
			}
			else if (!values.containsKey(column) && (column.toString().equalsIgnoreCase(idColumn) || column.toString().equalsIgnoreCase(revIdColumn) || column.toString().equalsIgnoreCase(headColumn))) {
				Log.debugMsg(TAG, "Unspecified column " + column + " found. Adding original value...");
				values.put(column, originalValues.get(column));
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
			return this.insert(tableName, values);
		}
		else {
			throw new NoIDExistsException();
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
	protected HashMap copyHeadInformation (String tableName, int id, String idColumn, String revIdColumn, String headColumn) throws NoIDExistsException, NoRevisionExistsException, SQLException, NoHeadException {
		ResultSet result = this.retrieveHeadInfo(tableName, id, idColumn, revIdColumn, headColumn);
		Log.debugMsg(TAG, "Retrieving head information for copying...");
		ResultSetMetaData metadata = result.getMetaData();
		HashMap map = new HashMap();
		if (result.next()) {
			int columns = metadata.getColumnCount();
			int ctr = 1;
			Log.debugMsg(TAG, "Copying existing data");
			while (ctr <= columns) {
				String columnName = result.getMetaData().getColumnName(ctr);
				map.put(columnName, result.getObject(ctr));
				ctr++;
			}
			Log.debugMsg(TAG, "HashMap of values created.");
			return map;
		}
		
		return null;
		
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
					return true;
				}
				else {
					return false;
				}
			}
			catch (SQLException ex) {
				throw new InvalidColumnsException();
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
			this.delete(tableName, whereCondition);
		}
		else {
			Log.errorMsg(TAG, "It seems the entry hasn't been marked for deletion. Cancelling deletion...");
			throw new UnmarkedDeleteException();
		}
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
				return true;
			}
			else {
				Log.debugMsg(TAG, "No, the id doesn't exist.");
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
	protected int getMaxId (String tableName, String idColumn) throws SQLException, NoIDExistsException {
		Statement request = db.getStatement();
		//SELECT MAX(tableName.idColumn) FROM tableName;
		String command = "SELECT MAX(" + tableName + "." + idColumn + ") FROM " + tableName + ";";
		Log.debugMsg(TAG, "Retrieving max id");
		ResultSet result = request.executeQuery(command);
		if (result.first()) {
			Log.debugMsg(TAG, "Looks like we have a result... returning max id");
			return result.getInt(idColumn);
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
				return result.getInt(revIdColumn);
			}
			catch (SQLException ex) {
				Log.errorMsg(TAG, "head rev id return failed.");
				throw new NoRevisionExistsException();
			}
		}
		else {
			Log.debugMsg(TAG, "No results from the ResultSet... there is no head revision id.");
			throw new NoHeadException();
		}
		
	}
	
}
