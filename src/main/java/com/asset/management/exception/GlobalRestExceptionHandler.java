
package com.asset.management.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.asset.management.exception.ErrorResponse.ErrorResponseBuilder;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalRestExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler({ InstallationException.class })
	protected ResponseEntity<Object> handleApiException(final InstallationException ex, final WebRequest request) {
		log.error("Exception in controller", ex);
		log.error(ex.getMessage());
		final ErrorResponse response = ErrorResponseBuilder.anErrorResponse().withErrorCode(ex.getErrorCode())
				.withMessage(ex.getLocalizedMessage()).withDetail(ex.getMessage())
				.withException(ex.getClass().getCanonicalName()).withPath(request.getContextPath())
				.withDetail(ex.getMessage()).build();
		return new ResponseEntity<>(response, HttpStatus.LOCKED);
	}

	@ExceptionHandler({ Exception.class })
	protected ResponseEntity<Object> defaultExceptionHandler(final Exception ex, final WebRequest request) {
		log.error("Exception in controller", ex);
		final ErrorResponse response = ErrorResponseBuilder.anErrorResponse().withErrorCode(ErrorCode.UNKNOWN_ERROR)
				.withMessage(ex.getLocalizedMessage()).withDetail(ex.getMessage())
				.withException(ex.getClass().getCanonicalName()).withPath(request.getContextPath())
				.withDetail(ex.getMessage()).build();
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}

