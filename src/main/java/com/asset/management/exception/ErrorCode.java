
package com.asset.management.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public enum ErrorCode {

	UNKNOWN_ERROR("Internal server error"), DATA_NOT_FOUND("Unable to locate the entity from db"),
	USER_ERROR("user data error"), RESOURCE_NOT_FOUND("Requested resource not found"),
	QUOTATION_NOT_FOUND("Quotation not found"), JOB_NOT_FOUND("Job not found"),
	NO_ACCEPTED_QUOTATION("No quotation is accepted"), INSTALLER_NOT_FOUND("Installer not found"),
	MORE_THAN_ONE_QUOTAION_IN_ACCEPTED("More than one Quotation is in accepted status"),
	QUOTATION_TOTAL_COST_NEGATIVE("Quotation total cost cannot be less than Zero"),
	QUOTATION_TOTAL_COST_NULL("Quotation total cost passed as null"),
	VAT_AMOUNT_VALUE_MISMATCH("VAT Amount calculated is not correct"),
	TOTAL_COST_NOT_MATCHING("Total cost calculated is not matching"), NO_VALID_CONTACT("Not a valid phone No"),
	NOT_VALID_OPT("Please enter valid OTP"), PHONENO_ALREADY_EXIST("Phone no is already exist"),
	USER_EXISTS_WITH_EMAIL("email is available with different account");

	private ErrorCode(String message) {
		this.message = message;
	}

	private String message;
	private final HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
}
