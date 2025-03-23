package com.lending.exceptions;

public class ScoringEngineException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ScoringEngineException(String message) {
		super(message);
	} 
}