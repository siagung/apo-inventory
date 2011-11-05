package com.apo.mysql.exception;

public class DatabaseNotFoundException extends Exception {

	public DatabaseNotFoundException() {
		super("The database was not found.");
	}

}
