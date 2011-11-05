package com.apo.operator;

import java.sql.SQLException;

import com.apo.mysql.Server;
import com.apo.mysql.exception.DatabaseNotFoundException;

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
}
