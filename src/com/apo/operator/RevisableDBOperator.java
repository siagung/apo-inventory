package com.apo.operator;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

import com.apo.debug.Log;
import com.apo.mysql.Server;
import com.apo.mysql.exception.DatabaseNotFoundException;
import com.apo.operator.exception.InvalidColumnsException;
import com.apo.operator.exception.NoHeadException;
import com.apo.operator.exception.NoIDExistsException;
import com.apo.operator.exception.NoRevisionExistsException;
import com.apo.operator.exception.UnmarkedDeleteException;

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
	 * @param values The Map (key-value pair) of pertinent values for the specific table
	 * @throws SQLException Whenever a conflict arises/SQL operation failure
	 * @throws InvalidColumnsException Whenever the revision id, item id, head or deleted fields are in the map of values, this is thrown. Perhaps you were thinking of doing a different operation if you encounter this.
	 */
	public void newItem (String tableName, Map values) throws SQLException, InvalidColumnsException {
		//check if the keySet has revId, itemId, head and deleted fields, then throw an InvalidColumnsException if at least one of them exists
		//check the table for the max ID number and add 1 to it
		//int newId = getMax(tableName, id);
		//assign revision number to 1
		//assign the revision to head
		//insert the revision number, id number, and head fields into the Map and insert whole thing into tableName
		
	}
	
	/**When creating a new revision of an existing item, don't put in the revision id,
	 * item id, head and deleted fields into the Map of values
	 * 
	 * @param tableName The name of the table where you'll insert the new item
	 * @param uniqueId The unique id of the item to be made a new revision of
	 * @param values The Map (key-value pair) of pertinent values for a specific table
	 * @return whether the operation was successful or not
	 * @throws NoIDExistsException No id exists for that table
	 * @throws NoHeadException No head exists for that id (this should not happen!)
	 * @throws InvalidColumnsException When the revision id, item id, head or deleted fields are put in the Map of values, this is thrown.
	 */
	public boolean newRevision (String tableName, int uniqueId, Map values) throws NoIDExistsException, NoHeadException, InvalidColumnsException {
		//check the table if the uniqueId exists, throw NoIDExistsException if the ID cannot be found
		//query for the HEAD and update() that value to false, if no head is found to be true, throw NoHeadException
		//check the table for the max revId number and add 1 to it
		//int newRevId = getMax(
		//insert the whole thing into tableName
	}
	
	/**Check if a certain id's records is marked for deletion
	 * 
	 * @param tableName the name of the table to be looked up
	 * @param uniqueId the unique id tied to records that are to be checked
	 * @param deleteMarkColumn the name of the deletion marker column (ie, a marker in the table that helps determine whether it has been marked for deletion (a value of 'true') or not ('false')
	 * @return whether the records associated with the uniqueId have been marked for deletion
	 */
	public boolean isMarked (String tableName, int uniqueId, String deleteMarkColumn) {
		
	}
	
	/**Mark a certain id for deletion (but not totally delete the entry containing the uniqueId; non-destructive "deletion")
	 * 
	 * @param tableName The name of the table to be looked up
	 * @param uniqueId The unique id that points to record(s) to be marked for deletion
	 */
	public void markDelete (String tableName, int uniqueId) {
		
	}
	
	/**Really (destructively) delete entries containing a certain unique id in a table; if the entries aren't marked for
	 * deletion, an exception is returned, but if the table with the record to be deleted doesn't even have a "deleted" column,
	 * the operation goes as expected.
	 * 
	 * @param tableName The name of the table to be looked up
	 * @param uniqueId The unique id that points to the record(s) to be deleted permanently
	 * @throws UnmarkedDeleteException thrown when the records you are trying to delete are not marked for deletion; however, absence of a "deleted" field in a table will not be cause for throwing this exception (the operation will proceed)
	 */
	public void delete (String tableName, int uniqueId) throws UnmarkedDeleteException {
		
	}
	
	/**Re-assigns a new HEAD record linked to a unique id
	 * 	
	 * @param tableName the table name to be looked up
	 * @param uniqueId the unique id of the record to be reverted
	 * @param newHeadRevId the EXISTING revision id that the record will be reverted to
	 * @throws NoRevisionExistsException the revision id you put in this method does not exist
	 */
	public void revert (String tableName, int uniqueId, int newHeadRevId) throws NoRevisionExistsException {
		
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
		ResultSet result = query(false, new String[] {idColumn}, tableName, whereCondition, null, null, null);
		if (result.first()) {
			if (result.getInt(idColumn) == uniqueId) {
				return true;
			}
			else {
				return false;
			}
		}
		else {
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
		ResultSet result = request.executeQuery(command);
		if (result.first()) {
			return result.getInt(idColumn);
		}
		else {
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
		String whereCondition = headColumn + " = " + "'" + "true" + "'";
		ResultSet result = this.query(false, new String[] {revIdColumn}, tableName, whereCondition, null, null, null);
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
