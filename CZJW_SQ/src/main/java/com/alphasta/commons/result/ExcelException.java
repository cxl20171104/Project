package com.alphasta.commons.result;

public class ExcelException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String exceptionMsg;

	public String getExceptionMsg() {
		return exceptionMsg;
	}

	public void setExceptionMsg(String exceptionMsg) {
		this.exceptionMsg = exceptionMsg;
	}

	public ExcelException(String exceptionMsg) {
		super();
		this.exceptionMsg = exceptionMsg;
	}
}
