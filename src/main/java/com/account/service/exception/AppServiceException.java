package com.account.service.exception;

/**
 * Created by Summer.Xia on 9/6/2015.
 */
public class AppServiceException extends Exception {
	private static final long serialVersionUID = -3979208212549921738L;

	public AppServiceException() {
	}

	public AppServiceException(String message) {
		super(message);
	}

	public AppServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public AppServiceException(Throwable cause) {
		super(cause);
	}
}
