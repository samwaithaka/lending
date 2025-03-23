package com.lending.exceptions;

public class ScoreThresholdException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ScoreThresholdException(String message) {
		super(message);
	} 
}