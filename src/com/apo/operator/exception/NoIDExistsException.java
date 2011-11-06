package com.apo.operator.exception;

public class NoIDExistsException extends Exception {
	public NoIDExistsException () {
		super("The ID being queried for does not exist.");
	}
}
