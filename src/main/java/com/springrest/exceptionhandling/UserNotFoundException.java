package com.springrest.exceptionhandling;

public class UserNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String exceptionMsg;
	
	public UserNotFoundException(String exceptionMsg) {
		super(exceptionMsg);
		this.exceptionMsg=exceptionMsg;
		
	}
	
	public String getExceptionMsg() {
		return exceptionMsg;
	}
	public void setExceptionMsg(String exceptionMsg) {
		this.exceptionMsg = exceptionMsg;
	}
	

}
