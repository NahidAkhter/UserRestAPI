package com.mobile.app.response.model;

public enum ErrorMessages {

	MISSING_REQUIRED_FIELDS("Missing required field. please check the documentation for the required fields"),
	RECORD_ALREADY_EXITS("Record already exits"),
	INTERNAL_SERVER_ERROR("Internal server error"),
	NO_RECORD_FOUND("Record with provided id is not found"),
	AUTHENTICATION_FAILED("Authentication failed"),
	COULD_NOT_UPDATE_RECORD("could not update record"),
	COULD_NOT_DELETE_RECORD("could not delete record"),
	EMAIL_ADDRESS_NOT_VERIFIED("email address could not be verified");
	
	private String errorMesage;

	private ErrorMessages(String errorMesage) {
		this.errorMesage = errorMesage;
	}

	public String getErrorMesage() {
		return errorMesage;
	}

	public void setErrorMesage(String errorMesage) {
		this.errorMesage = errorMesage;
	}
}
