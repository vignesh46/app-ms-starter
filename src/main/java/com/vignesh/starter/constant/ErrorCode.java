package com.vignesh.starter.constant;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public enum ErrorCode {

	HTTP_SERVER_ERROR("HTTP_SERVER_ERROR", HttpStatus.INTERNAL_SERVER_ERROR),
	HTTP_CLIENT_ERROR("HTTP_CLIENT_ERROR", HttpStatus.BAD_REQUEST),
	HTTP_TIMEOUT_ISSUE("HTTP_TIMEOUT_ISSUE", HttpStatus.INTERNAL_SERVER_ERROR),
	INPUT_VALIDATION_ERROR("INPUT_VALIDATION_ERROR", HttpStatus.BAD_REQUEST),
	HEADER_VALIDATION_ERROR("HEADER_VALIDATION_ERROR", HttpStatus.BAD_REQUEST),
	INTERNAL_ERROR("INTERNAL_ERROR", HttpStatus.INTERNAL_SERVER_ERROR),
	RECORD_NOT_FOUND("RECORD_NOT_FOUND", HttpStatus.BAD_REQUEST), 
	DATA_ISSUE("DATA_ISSUE", HttpStatus.BAD_REQUEST);

	private String code;
	private HttpStatus httpStatus;

	private ErrorCode(String code, HttpStatus httpStatus) {
		this.code = code;
		this.httpStatus = httpStatus;
	}
	
}
