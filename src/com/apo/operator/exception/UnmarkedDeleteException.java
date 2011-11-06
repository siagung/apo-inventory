package com.apo.operator.exception;

public class UnmarkedDeleteException extends Exception {
	public UnmarkedDeleteException () {
		super("The entries you are trying to delete aren't marked for deletion!");
	}
}
