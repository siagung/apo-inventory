package com.apo.mysql;

import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.apo.debug.Log;
import com.apo.mysql.exception.DatabaseNotFoundException;

/**Establishes a connection to server; doesn't store user name and password, just server URL**/
public class Server {
	/**TAG used for debugging**/
	protected final String TAG = this.getClass().getCanonicalName();
	
	/**Store the name of the database that the server will connect to**/
	String dbName;
	/**The url where the server is located**/
	String url;
	/**The raw Connection object of the server. Refer to JDBC documentation**/
	Connection serverConnection;
	/**The connection status of the server**/
	boolean connectionStatus;
	/**List of listeners that look for changes in the Connection status**/
    private List listeners = new ArrayList();

	
	private static String URL_PREFIX = "jdbc:mysql://";
	
	/**establishes a connection to the MySQL server
	 * 
	 * @param username The username to the MySQL Server
	 * @param password The password to the MySQL Server
	 * @param url The URL could be over IP, etc. Follow: [host][,failoverhost...][:port]/[database][?propertyName1][=propertyValue1][&propertyName2][=propertyValue2]...; leaving the field null will establish the connection to localhost
	 * @param dbName The name of the database to be accessed; leaving this null only connects to the server
	 * @throws DatabaseNotFoundException the database could not be found
	 * @throws SQLException the database has incorrect data
	 */
	public Server (String username, String password, String url, String dbName) throws DatabaseNotFoundException, SQLException {
		if (dbName == null || (dbName != null || !dbName.equalsIgnoreCase("")) && checkDbExists(url, username, password, dbName)) {
			if (url == null) {
				this.url = getDefaultUrl(dbName);
			}
			else {
				this.url = getCustomUrl(url, dbName);
			}
			
			establishConnection(this.url, username, password);
			Log.debugMsg(TAG, "Connected to a specific database.");
		}
		
		else {
			throw new DatabaseNotFoundException();
		}
		
				
	}
	
	/**establishes a connection to the MySQL server
    *
    * @param username The user name associated with the server
    * @param password The char array representation of the password associated with the user name
    * @param url The URL could be over IP, etc. Follow: [host][,failoverhost...][:port]/[database][?propertyName1][=propertyValue1][&propertyName2][=propertyValue2]...; leaving the field null will establish the connection to the localhost
    * @param dbName The name of the database to be accessed; leaving this null will enable access to server only
	*/
   public Server (String username, char[] password, String url, String dbName) {
	   if (url == null) {
			this.url = getDefaultUrl(dbName);
		}
		else {
			this.url = getCustomUrl(url, dbName);
		}
	   
	   establishConnection(this.url, username, parsePassword(password));
   }
   
   /**Use this method when you've already established a connection to the MySQL Server, but not any specific database
    * 
    * @param dbName The name of the database you wish to use on an active Server connection
    */
   public void useDatabase (String dbName) {
	   try {
		if (serverConnection != null && connectionStatus) {
		   this.serverConnection.createStatement().execute("USE " + dbName);
		}
		else {
			Log.debugMsg(TAG, "Database change operation: Nothing happened.");
			return;
		}
		Log.debugMsg(TAG, "Database switch success!");
	} catch (SQLException e) {
		Log.errorMsg(TAG, "The database name you specified may not exist.");
		e.printStackTrace();
	}
   }
   
   /**Checks whether a database exists; uses mysql table of the server; still not tested
    * 
    * @param username The user name associated with the server
    * @param password The password associated with the user name
    * @param dbName The database name to be checked
    * @return true if the database exists
    * @throws SQLException user name/password/url is/are invalid
    */
   private boolean checkDbExists(String url, String username, String password, String dbName) throws SQLException {
	   ArrayList<String> availableTables = new ArrayList<String>();
	   if (url!=null) {
		   establishConnection(getCustomUrl(url, null), username, password);
	   }
	   else {
		   establishConnection(getDefaultUrl(null), username, password);
	   }
	   DatabaseMetaData dbMeta = this.serverConnection.getMetaData();
	   ResultSet rs = dbMeta.getCatalogs();
	   while (rs.next()) {
		   String listOfDatabases = rs.getString(rs.getString("TABLE_CAT"));
		   availableTables.add(listOfDatabases);
	   }
	   rs.close();
	   serverConnection.close();
	   if (availableTables.contains(dbName)) {
		   Log.debugMsg(TAG, "Found the database");
		   return true;
	   }
	   
	   return false;
   }
	
	/**Opens the connection
	 * 
	 * @param url The url of the server
	 * @param username The username used to connect to the server
	 * @param password The password used to connect to the server
	 */
    private void establishConnection(String url, String username, String password) {
    	try {
    		Class.forName("com.mysql.jdbc.Driver").newInstance();
    		Log.debugMsg(TAG, "Class for name successful");
    		serverConnection = DriverManager.getConnection(url, username, password);
    		Log.debugMsg(TAG, "Connection successful");
    		this.connectionStatus = true;
    		Log.debugMsg(TAG, "DB connection established");
    		fireOnConnectedEvent();
    	}
    	catch (SQLException ex) {
    		Log.errorMsg(TAG, "Can't connect. User name, password, or url may be incorrect.");
    		this.connectionStatus = false;
    		ex.printStackTrace();
    	} catch (InstantiationException e) {
    		Log.errorMsg(TAG, "Instantiation exception");
			this.connectionStatus = false;
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			Log.errorMsg(TAG, "Illegal Access");
			this.connectionStatus = false;
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			Log.errorMsg(TAG, "Class Not Found");
			this.connectionStatus = false;
			e.printStackTrace();
		}
    }
	
	/**Get the url to the default connection to localhost
	 * 
	 * @param dbName The name of the database to be used; leaving this field null only returns the localhost url
	 * @return The url of the connection to localhost + dbName
	 */
	private String getDefaultUrl(String dbName) {
		return URL_PREFIX + "localhost/" + dbName;
	}
	
	/**Returns a custom url for use with JDBC connector
	 * 
	 * @param serverUrl The custom URL where the server is
	 * @param dbName The name of the database to be connected; leaving this field null only returns the custom server url
	 * @return custom URL that is re-packaged into a JDBC url
	 */
	private String getCustomUrl(String serverUrl, String dbName) {
		if (!dbName.endsWith("/")) {
			return URL_PREFIX + serverUrl + "/" + dbName;
		}
		return URL_PREFIX + serverUrl + dbName;
	}
	
	/**Disconnects the underlying Connection object of the Server object
	 * 
	 * @throws SQLException Something went wrong while disconnecting from the SQL Server
	 */
	public void close() throws SQLException {
		if (serverConnection != null) {
			serverConnection.close();
		}
	}
	
	/**Returns the connection that the Server is using to connect to the MySQL server
	 * 
	 * @return the Connection object that the Server is using to connect to the MySQL server
	 */
	public Connection getConnection() {
		return serverConnection;
	}
	
	/**Returns a statement that can be executed on the MySQL server connection
	 * 
	 * @return the Statement object that can be executed on the MySQL Server
	 * @throws SQLException there may be something wrong with the server connection (can't make a statement without a working connection!)
	 */
	public Statement getStatement() throws SQLException {
		return serverConnection.createStatement();
	}
	
	/**Parse passwords from char array to String
    *
    * @param password Character array representation of the password, usually gotten from JPasswordField's getPassword() method
    * @return a String representation of the password
    */
   private String parsePassword (char[] password) {
       StringBuilder stringPassword = new StringBuilder();
       /**StringBuilder object builds a string little by little**/
       for (int ctr = 0; ctr < password.length; ctr++) {
                stringPassword.append(password[ctr]);
                /**Each iteration appends the password character into a string**/
       }
       return stringPassword.toString();
   }
   
   private synchronized void fireOnConnectedEvent () {
       SQLConnectedEvent event = new SQLConnectedEvent(this);
       Iterator i = listeners.iterator();
       while (i.hasNext()) {
           OnConnectedListener listener = (OnConnectedListener)i.next();
           listener.onConnectionConfirmed(event);
       }
   }
	
}
