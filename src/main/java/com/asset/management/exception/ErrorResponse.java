
package com.asset.management.exception;

import lombok.Getter;

@Getter
public class ErrorResponse {

	private ErrorCode errorCode;
	private String message;
	private String detail;
	private String exception;
	private String path;
	private String localizedMessage;

	// getter and setters
	// Builder
	public static final class ErrorResponseBuilder {
		private ErrorCode errorCode;
		private String message;
		private String detail;
		private String exception;
		private String path;
		private String localizedMessage;

		public static ErrorResponseBuilder anErrorResponse() {
			return new ErrorResponseBuilder();
		}

		public ErrorResponseBuilder withException(final String exception) {
			this.exception = exception;
			return this;
		}

		public ErrorResponseBuilder withErrorCode(final ErrorCode errorCode) {
			this.errorCode = errorCode;
			return this;
		}

		public ErrorResponseBuilder withMessage(final String message) {
			this.message = message;
			return this;
		}

		public ErrorResponseBuilder withDetail(final String detail) {
			this.detail = detail;
			return this;
		}

		public ErrorResponseBuilder withPath(final String path) {
			this.path = path;
			return this;
		}

		public ErrorResponseBuilder withLocalizedMessage(final String localizedMessage) {
			this.localizedMessage = localizedMessage;
			return this;
		}

		public ErrorResponse build() {
			final ErrorResponse errorResponse = new ErrorResponse();
			errorResponse.errorCode = this.errorCode;
			errorResponse.detail = this.detail;
			errorResponse.message = this.message;
			errorResponse.exception = this.exception;
			errorResponse.path = this.path;
			errorResponse.localizedMessage = localizedMessage;
			return errorResponse;
		}
	}
}

