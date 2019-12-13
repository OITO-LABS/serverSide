
package com.asset.management.exception;

import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpStatus;

public class InstallationException extends RuntimeException {

	private final ErrorCode errorCode;

	private final int statusCode;

	private final Exception exception;

	private final List<String> errorMessages;

	private Object errorObject;

	/**
	 * @param {@link AuthErrorCode} errorCode
	 */
	public InstallationException(final ErrorCode errorCode) {
		super(errorCode.getMessage());
		this.errorCode = errorCode;
		this.errorMessages = Collections.emptyList();
		this.statusCode = HttpStatus.BAD_REQUEST.value();
		this.exception = new RuntimeException(errorCode.getMessage());
	}

	public InstallationException(final ErrorCode errorCode, final List<String> errorMessages) {
		super(errorCode.getMessage());
		if (null == errorMessages) {
			this.errorMessages = Collections.emptyList();
		} else {
			this.errorMessages = errorMessages;
		}
		this.errorCode = errorCode;
		this.statusCode = HttpStatus.BAD_REQUEST.value();
		this.exception = new RuntimeException(errorCode.getMessage());
	}

	public InstallationException(final ErrorCode errorCode, final String message) {
		super(message);
		this.errorCode = errorCode;
		this.errorMessages = Collections.emptyList();
		this.statusCode = HttpStatus.BAD_REQUEST.value();
		this.exception = new RuntimeException(errorCode.getMessage());
	}

	/**
	 * @param {@link ErrorCode} errorCode
	 * @param {@link Throwable} cause
	 */
	public InstallationException(final Exception exception) {
		super(ErrorCode.UNKNOWN_ERROR.getMessage() + exception.getMessage(), exception);
		// log.info("Unknown Error Occured", exception);
		this.errorCode = ErrorCode.UNKNOWN_ERROR;
		this.errorMessages = Collections.emptyList();
		this.statusCode = HttpStatus.BAD_REQUEST.value();
		this.exception = exception;
	}

	/**
	 * @param {@link ErrorCode} errorCode
	 * @param {@link Throwable} cause
	 */
	public InstallationException(final ErrorCode errorCode, final Exception exception) {
		super(errorCode.getMessage() + exception.getMessage(), exception);
		this.errorCode = errorCode;
		this.errorMessages = Collections.emptyList();
		this.statusCode = HttpStatus.BAD_REQUEST.value();
		this.exception = exception;
	}

	public InstallationException(final ErrorCode errorCode, final int statusCode, final Exception exception) {
		super(errorCode.getMessage() + exception.getMessage(), exception);
		this.errorMessages = Collections.emptyList();
		this.errorCode = errorCode;
		this.statusCode = statusCode;
		this.exception = exception;
	}

	public InstallationException(ErrorCode errorCode, Object errorObject) {
		super(errorCode.getMessage());
		this.errorCode = errorCode;
		this.errorMessages = Collections.emptyList();
		this.statusCode = HttpStatus.BAD_REQUEST.value();
		this.exception = new RuntimeException(errorCode.getMessage());
		this.errorObject = errorObject;
	}

	public ErrorCode getErrorCode() {
		// TODO Auto-generated method stub
		return null;
	}

}

