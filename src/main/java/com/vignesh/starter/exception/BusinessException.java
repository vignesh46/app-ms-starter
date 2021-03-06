package com.vignesh.starter.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private HttpStatus httpstatus;
	private String errorCode;
	private Exception originalException;
	private String message;

	public BusinessException(String errorCode, HttpStatus httpstatus, String message) {
		super(message);
		this.message = message;
		this.errorCode = errorCode;
		this.httpstatus = httpstatus;
	}

	public BusinessException(String errorCode, HttpStatus httpstatus, String message, Exception originalException) {
		super(message);
		this.message = message;
		this.errorCode = errorCode;
		this.httpstatus = httpstatus;
		this.originalException = originalException;
	}

}
