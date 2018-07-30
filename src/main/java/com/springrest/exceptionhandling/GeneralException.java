package com.springrest.exceptionhandling;

public class GeneralException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String exceptionMsg;

	public GeneralException(String exceptionMsg) {
		super(exceptionMsg);

	}

	public String getExceptionMsg() {
		return exceptionMsg;
	}

	public void setExceptionMsg(String exceptionMsg) {
		this.exceptionMsg = exceptionMsg;
	}

}
