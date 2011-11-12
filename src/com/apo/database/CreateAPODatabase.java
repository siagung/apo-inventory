package com.apo.database;

import java.io.InputStream;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.apo.debug.Log;
import com.apo.mysql.Server;
import com.apo.mysql.exception.DatabaseNotFoundException;
/**Contains static methods that create the database and populates it; do not instantiate
 * 
 * @author Kevin Panuelos
 *
 */
public class CreateAPODatabase {
	/**TAG for class debugging**/
	protected static final String TAG = CreateAPODatabase.class.getCanonicalName();
	/**The server object needed to execute the commands in the script; connects to mysql table**/
	private static Server serv;	
	
	private static String[] fileUrls = {
		"com/apo/database/APO_Database_Script.sql",
		"com/apo/database/privileges.sql",
		"com/apo/database/user_level.sql",
		"com/apo/database/contact_kind.sql",
		"com/apo/database/announcement_type.sql",
		"com/apo/database/product_categories.sql",
		"com/apo/database/secret_questions.sql"
	};
	
	/**Creates the database and populates some of the tables
	 * 
	 * @param username The user name used to connect to the server
	 * @param password The password used to connect with the user name to the sql server
	 * @throws SQLException There may have been something wrong with the user name and password
	 */
	public static void createDatabase(String username, String password) throws SQLException {
		try {
			serv = new Server(username, password, null, "mysql");
		} catch (DatabaseNotFoundException e) {
			Log.errorMsg(TAG, "MySQL table not found, for whatever reason");
			e.printStackTrace();
		}
		executeScripts(fileUrls);
		serv.close();
	}
	
	/**Creates the database and populates some of the tables
	 * 
	 * @param username The user name to connect to the sql server
	 * @param password The password used to connect with the user name to the sql server
	 * @throws SQLException There may have been wrong information in the user name and password fields
	 */
	public static void createDatabase (String username, char[] password) throws SQLException {
		try {
			serv = new Server(username, parsePassword(password), null, "mysql");
		} catch (DatabaseNotFoundException e) {
			Log.errorMsg(TAG, "MySQL table not found, for whatever reason.");
			e.printStackTrace();
		}
		executeScripts(fileUrls);
		serv.close();
	}
	
	/**Executes the script urls you feed to the parameter
	 * 
	 * @param fileUrl may contain one or more file urls
	 */
	private static void executeScripts (String... fileUrl) {
		ClassLoader loader = CreateAPODatabase.class.getClassLoader();
		for (String file : fileUrl) {
			Log.debugMsg(TAG, "Executing " + fileUrl);
			executeScript(loader.getResourceAsStream(file));
		}
	}
	
	/**Executes scripts that are placed in an InputStream
	 * 
	 * @param rawScript The raw input stream from the script files
	 */
	private static void executeScript (InputStream rawScript) {
		Scanner parser = new Scanner(rawScript);
		parser.useDelimiter(";");
		while (parser.hasNext()) {
			String line = parser.nextLine().concat(";");
			try {
				Statement execute = serv.getConnection().createStatement();
				execute.execute(line);
			} catch (SQLException e) {
				Log.errorMsg(TAG, "SQL server may be disconnected, or something.");
				e.printStackTrace();
			}
		}
	}
	
	/**Parse passwords from char array to String
    *
    * @param password Character array representation of the password, usually gotten from JPasswordField's getPassword() method
    * @return a String representation of the password
    */
   private static String parsePassword (char[] password) {
       StringBuilder stringPassword = new StringBuilder();
       /**StringBuilder object builds a string little by little**/
       for (int ctr = 0; ctr < password.length; ctr++) {
                stringPassword.append(password[ctr]);
                /**Each iteration appends the password character into a string**/
       }
       return stringPassword.toString();
   }
		
}
