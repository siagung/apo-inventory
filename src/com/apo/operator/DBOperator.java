package com.apo.operator;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import com.apo.mysql.Server;
import com.apo.mysql.exception.DatabaseNotFoundException;

public class DBOperator {
	
	/**Server object that the operator will use to execute CRUD actions**/
	protected Server db;
	
	/**Initializes the server alongside the operator of the server
	 * 
	 * @param username Username used to connect to the server/database
	 * @param password Password associated with the username
	 * @param url The custom url used to connect to a remote database/server; can be null to indicate localhost
	 * @param dbName The name of the database to be connected to; can be null if only server will be used for connection
	 * @throws DatabaseNotFoundException The database could not be found (bad dbName spelling?)
	 * @throws SQLException The operation could not be completed (wrong username/password?)
	 */
	public DBOperator (String username, String password, String url, String dbName) throws DatabaseNotFoundException, SQLException {
		this.db = new Server (username, password, url, dbName);
	}
	
	/**Initializes the server alongside the operator of the server
	 * 
	 * @param username Username used to connect to the server/database
	 * @param password Char array representing the password associated with the user name
	 * @param url Customer url used to connect to a remote database/server; can be null to indicate localhost
	 * @param dbName The name of the database to be connected to; can be null if only server will be used for connection
	 * @throws DatabaseNotFoundException The database could not be found (bad dbName spelling?)
	 */
	public DBOperator (String username, char[] password, String url, String dbName) throws DatabaseNotFoundException {
		this.db = new Server (username, password, url, dbName);
	}
	/**Initializes the server alongside the operator of the server
	 * 
	 * @param db The database connection to the server
	 */
	public DBOperator (Server db) {
		this.db = db;
	}
	
	/*****************ALWAYS PUT QUOTATION MARKS (' ') ON STRING VALUES*****************************/

    /**Convenience method used to update a record in a table
     *
     * @param fromTable The table in the database where a record to be updated is found
     * @param values The values to be updated in a certain record; this is a key-value pair where the key corresponds to the actual row name; REMEMBER: String values must contain quotation marks!
     * @param where Limits the query to the specified WHERE constraint
     * @return Returns whether the executed statement was successful (true) or not (false)
     * @throws SQLException If there is a syntax error, etc.
     */
    public boolean update (String fromTable, Map values, String where) throws SQLException {
        Statement updateStatement = this.db.getConnection().createStatement();
        Map valuesCopy = values;
        StringBuilder updateSQL = new StringBuilder();
        updateSQL.append("UPDATE ").append(fromTable).append(" SET ");
        Iterator keyIterator = valuesCopy.keySet().iterator();
        while (keyIterator.hasNext()) {
            String key = (String)keyIterator.next();
            updateSQL.append(key).append("=");
            updateSQL.append(values.get(key));
            if (keyIterator.hasNext()) {
                updateSQL.append(", ");
            }
        }
        if (where!=null) {
            updateSQL.append(" WHERE ").append(where).append(";");
        }
        else {
            throw new SQLException();
        }
        System.out.println(updateSQL.toString());
        return updateStatement.execute(updateSQL.toString());
    }

    /**Deletes a certain record from a table
     * 
     * @param fromTable The table where a record is found
     * @param where The constraint that lets MySQL determine the records to be deleted
     * @return Returns whether the executed statement was successful (true) or not (false)
     * @throws SQLException If there is a syntax error, etc.
     */
    public boolean delete (String fromTable, String where) throws SQLException {
        Statement deleteStatement = this.db.getConnection().createStatement();
        StringBuilder deleteSQL = new StringBuilder();
        deleteSQL.append("DELETE FROM ").append(fromTable).append(" WHERE ").append(where).append(";");
        System.out.println(deleteSQL.toString());
        return deleteStatement.execute(deleteSQL.toString());
    }

    /**Insert values into a table found in a database
     *
     * @param intoTable The table where you wish to insert a new record
     * @param values The values that are contained within the record; this is a key-value pair where the key is unique; REMEMBER: values that are of String data type must have '' quotation marks
     * @return Returns whether the executed statement was successful (true) or not (false)
     * @throws SQLException If there is a syntax error, etc.
     */
    public boolean insert (String intoTable, Map values) throws SQLException {
        Statement insertStatement = this.db.getConnection().createStatement();
        Map valuesCopy = values;
        ArrayList keys = new ArrayList();
        StringBuilder insertSQL = new StringBuilder();
        insertSQL.append("INSERT INTO ").append(intoTable).append(" (");
        Iterator keyIterator = valuesCopy.keySet().iterator();
        while (keyIterator.hasNext()) {
            String key = (String)keyIterator.next();
            insertSQL.append(key);
            keys.add(key);
            if (keyIterator.hasNext()) {
                insertSQL.append(", ");
            }
        }
        insertSQL.append(") VALUES (");
        Iterator anotherKeyIterator = keys.iterator();
        while (anotherKeyIterator.hasNext()) {
            insertSQL.append(valuesCopy.get(anotherKeyIterator.next()));
            if (anotherKeyIterator.hasNext()) {
                insertSQL.append(", ");
            }
        }
        insertSQL.append(");");
        System.out.println(insertSQL.toString());
        return insertStatement.execute(insertSQL.toString());
    }

    /**Queries a table depending on the conditions and constraints that are passed through the arguments
     *
     * @param distinct Set to true to return distinct column values of the selected columns
     * @param selectColumns An array of columns that will be a constraint to what are returned in the ResultSet
     * @param fromTable The source table where the data can be found
     * @param where The where clause that constrains the results that are returned
     * @param groupBy Aggregate columns with the same values
     * @param orderBy Order columns in ascending or descending (add 'DESC') order
     * @param having Extra constraints that cannot otherwise be done using WHERE
     * @return ResultSet that is a representation of the table returned by a query in the MySQL client
     * @throws SQLException When there is a syntax error, etc.
     */
    public ResultSet query (boolean distinct, String[] selectColumns, String fromTable, String where, String groupBy, String orderBy, String having) throws SQLException {
        Statement queryStatement = this.db.getConnection().createStatement();
        StringBuilder querySQL = new StringBuilder();
        querySQL.append("SELECT ");
        if (distinct) {
            querySQL.append("DISTINCT ");
        }
        if (selectColumns != null) {
            for (int ctr = 0; ctr < selectColumns.length; ctr++) {
                querySQL.append(selectColumns[ctr]);
                if (ctr+1 != selectColumns.length) {
                    querySQL.append(", ");
                    System.out.println("Added comma");
                }
                System.out.println("query selectColumn number: " + Integer.toString(ctr+1) + " out of " + Integer.toString(selectColumns.length));
            }
        }
        else {
            querySQL.append("*");
            System.out.println("All columns");
        }
        querySQL.append(" FROM ");
        if (fromTable == null) {
            throw new SQLException ();
        }
        else {
            querySQL.append(fromTable);
        }
        if (where != null) {
            querySQL.append(" WHERE ").append(where);
        }
        if (groupBy != null) {
            querySQL.append(" GROUP BY ").append(groupBy);
        }
        if (orderBy != null) {
            querySQL.append(" ORDER BY ").append(orderBy);
        }
        if (having != null) {
            querySQL.append(" HAVING ").append(having);
        }
        querySQL.append(";");
        System.out.println(querySQL.toString());
        return queryStatement.executeQuery(querySQL.toString());
    }
}
