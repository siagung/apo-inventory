package com.apo.operator.exception;

public class NoRevisionExistsException extends Exception {
	public NoRevisionExistsException () {
		super ("The revision you are trying to access does not exist.");
	}
}
