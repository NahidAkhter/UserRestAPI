package com.mobile.app.exceptions;

public class UserServiceException extends RuntimeException{

	private static final long serialVersionUID = 6868986411095284742L;
	
	public UserServiceException(String message) {
		super(message);
	}

}
