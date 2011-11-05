package com.apo.mysql;

import java.sql.DriverManager;
import java.sql.SQLException;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.apo.mysql.exception.NoDatabaseNameException;

/**Establishes a connection to server; doesn't store user name and password, just server URL**/
public class Server {
	private final String TAG = this.getClass().getCanonicalName();
	
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
	 * @param dbName The name of the database to be accessed
	 * @throws NoDatabaseNameException When dbName is null
	 */
	public Server (String username, String password, String url, String dbName) throws NoDatabaseNameException {
		/*if (checkDbExists(url, username, password, dbName)) {
			if (url == null) {
			this.url = getDefaultUrl(dbName);
			}
			else {
				this.url = getCustomUrl(url, dbName);
			}
			
			establishConnection(this.url, username, password);
		}
		
		else {
			throw new DatabaseNotFoundException();
		}*/
		
		if (url == null) {
			this.url = getDefaultUrl(dbName);
		}
		else {
			this.url = getCustomUrl(url, dbName);
		}
		
		establishConnection(this.url, username, password);
	}
	
	/**establishes a connection to the MySQL server
    *
    * @param username The user name associated with the server
    * @param password The char array representation of the password associated with the user name
    * @param url The URL could be over IP, etc. Follow: [host][,failoverhost...][:port]/[database][?propertyName1][=propertyValue1][&propertyName2][=propertyValue2]...; leaving the field null will establish the connection to the localhost
    * @param dbName The name of the database to be accessed
	 * @throws NoDatabaseNameException When dbName is null, this is thrown
    */
   public Server (String username, char[] password, String url, String dbName) throws NoDatabaseNameException {
	   if (url == null) {
			this.url = getDefaultUrl(dbName);
		}
		else {
			this.url = getCustomUrl(url, dbName);
		}
	   
	   establishConnection(this.url, username, parsePassword(password));
   }
   
   /**Checks whether a database exists; uses mysql table of the server; still not tested
    * 
    * @param username The user name associated with the server
    * @param password The password associated with the user name
    * @param dbName The database name to be checked
    * @return true if the database exists
    * @throws NoDatabaseNameException database name is null
    */
   private boolean checkDbExists(String url, String username, String password, String dbName) throws NoDatabaseNameException {
	   if (url!=null) {
		   establishConnection(getCustomUrl(url, "mysql"), username, password);
	   }
	   else {
		   establishConnection(getDefaultUrl("mysql"), username, password);
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
    		System.out.println(TAG + "Class for name successful");
    		serverConnection = DriverManager.getConnection(url, username, password);
    		System.out.println(TAG + "Connection successful");
    		this.connectionStatus = true;
    		System.out.println(TAG + "DB connection established");
    		fireOnConnectedEvent();
    	}
    	catch (SQLException ex) {
    		System.err.println(TAG + "Can't connect");
    		this.connectionStatus = false;
    		ex.printStackTrace();
    	} catch (InstantiationException e) {
    		System.err.println(TAG + "Instantiation exception");
			this.connectionStatus = false;
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			System.err.println(TAG + "Illegal Access");
			this.connectionStatus = false;
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.err.println(TAG + "Class Not Found");
			this.connectionStatus = false;
			e.printStackTrace();
		}
    }
	
	/**Get the url to the default connection to localhost
	 * 
	 * @param dbName The name of the database to be used; leaving this field null only returns the localhost url
	 * @return The url of the connection to localhost + dbName
	 * @throws NoDatabaseNameException When dbName is null
	 */
	private String getDefaultUrl(String dbName) throws NoDatabaseNameException {
		if (dbName == null) {
			throw new NoDatabaseNameException();
		}
		return URL_PREFIX + "localhost/" + dbName;
	}
	
	/**Returns a custom url for use with JDBC connector
	 * 
	 * @param serverUrl The custom URL where the server is
	 * @param dbName The name of the database to be connected; leaving this field null only returns the custom server url
	 * @return custom URL that is re-packaged into a JDBC url
	 * @throws NoDatabaseNameException When dbName is null
	 */
	private String getCustomUrl(String serverUrl, String dbName) throws NoDatabaseNameException {
		if (dbName == null) {
			throw new NoDatabaseNameException();
		}
		return URL_PREFIX + serverUrl + dbName;
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
