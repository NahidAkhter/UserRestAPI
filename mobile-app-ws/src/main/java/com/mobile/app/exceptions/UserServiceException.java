package com.mobile.app.exceptions;

public class UserServiceException extends RuntimeException{

	private static final long serialVersionUID = -222193434946102419L;
	
	public UserServiceException(String message) {
		super(message);
	}
}