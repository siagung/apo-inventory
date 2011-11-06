package com.apo.operator.exception;

public class InvalidColumnsException extends Exception {
	public InvalidColumnsException () {
		super("The columns that were included are inappropriate for this operation.");
	}
	
	public InvalidColumnsException (String message) {
		super (message);
	}
}
