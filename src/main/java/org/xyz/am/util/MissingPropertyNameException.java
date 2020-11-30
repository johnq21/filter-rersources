package org.xyz.am.util;

public class MissingPropertyNameException extends Exception{
	private static final long serialVersionUID = 1L;

	public MissingPropertyNameException(String message) {
		super(message);
	}
}
