package com.account.core.exception;

/**
 * Created by Summer.Xia on 10/8/2015.
 */
public class DateParseException extends Exception{
	private static final long serialVersionUID = -3979208212549921738L;

	public DateParseException() {
	}

	public DateParseException(String message) {
		super(message);
	}

	public DateParseException(String message, Throwable cause) {
		super(message, cause);
	}

	public DateParseException(Throwable cause) {
		super(cause);
	}
}
