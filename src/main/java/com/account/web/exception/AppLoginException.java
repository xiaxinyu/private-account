package com.account.web.exception;

/**
 * Created by Summer.Xia on 9/22/2015.
 */
public class AppLoginException extends Exception {
	private static final long serialVersionUID = -3979208212549921738L;

	public AppLoginException() {
	}

	public AppLoginException(String message) {
		super(message);
	}

	public AppLoginException(String message, Throwable cause) {
		super(message, cause);
	}

	public AppLoginException(Throwable cause) {
		super(cause);
	}
}
