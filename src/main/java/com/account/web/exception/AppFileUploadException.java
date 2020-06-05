package com.account.web.exception;

/**
 * Created by Summer.Xia on 9/1/2015.
 */
public class AppFileUploadException extends Exception {
	private static final long serialVersionUID = -3979208212549921738L;

	public AppFileUploadException() {
	}

	public AppFileUploadException(String message) {
		super(message);
	}

	public AppFileUploadException(String message, Throwable cause) {
		super(message, cause);
	}

	public AppFileUploadException(Throwable cause) {
		super(cause);
	}
}
