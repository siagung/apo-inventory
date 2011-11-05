package com.apo.mysql.exception;

/**When no database name is specified, the connection via JDBC cannot be made**/
public class NoDatabaseNameException extends Exception {

	public NoDatabaseNameException() {
		super("The database name has not been specified, thus the connection cannot be established.");
	}
}
