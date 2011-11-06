package com.apo.operator;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

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
	 */
	public ResultSet retrieve (String tableName, int uniqueId) throws NoIDExistsException {
		return retrieveHeadInfo (tableName, uniqueId);
	}
	
	/**Retrieves the version of the record where its HEAD field is marked true
	 * 
	 * @param tableName the table name to be looked up
	 * @param uniqueId the unique id attached to the record to be retrieved
	 * @return the entire record in a ResultSet object
	 * @throws NOIDExistsException when the ID does not exist in the table
	 */
	public ResultSet retrieveHeadInfo (String tableName, int uniqueId) throws NoIDExistsException {
		
	}
	
	/**Retrieves a specific version of the record, regardless of whether its HEAD field is true or not
	 * 
	 * @param tableName the table name to be looked up
	 * @param uniqueId the unique id attached to the record to be retrieved
	 * @param revId the revision id attached to the version of a record to be retrieved
	 * @return the entire record in a ResultSet object
	 * @throws NoIDExistsException the ID does not exist in the table
	 * @throws NoRevisionExistsException a revision ID does not exist for a specific ID in the table
	 */
	public ResultSet retrieveRevisionInfo (String tableName, int uniqueId, int revId) throws NoIDExistsException, NoRevisionExistsException {
		
	}
	
	/**Retrieves the revision id with the largest integer value
	 * 
	 * @param tableName The table name to be looked up
	 * @param id the unique id attached to the table that holds a record/multiple records
	 * @param revIdColumn the specific name of the revision id column
	 * @return the maximum integer value of the revision id column specified
	 * @throws NoRevisionExistsException if the revision id column specified does not even exist in the table
	 * @throws NoIDExistsException if the unique id specified does not exist for a table 
	 */
	private int getMaxRevId (String tableName, int id, String revIdColumn) throws NoRevisionExistsException, NoIDExistsException {
		
	}
	
}
