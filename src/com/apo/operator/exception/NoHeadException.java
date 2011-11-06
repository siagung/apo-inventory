package com.apo.operator.exception;

/**When a HEAD revision is not found/designated for an item**/
public class NoHeadException extends Exception {
	public NoHeadException () {
		super ("No HEAD revision was found.");
	}
}
