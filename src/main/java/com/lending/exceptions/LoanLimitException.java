package com.lending.exceptions;

public class LoanLimitException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public LoanLimitException(String message) {
		super(message);
	} 
}