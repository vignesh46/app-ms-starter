package com.vignesh.starter.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.vignesh.starter.beans.response.ErrorResponse;
import com.vignesh.starter.constant.ErrorCode;
import com.vignesh.starter.constant.ErrorMessage;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	private static final Logger LOG = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	@ExceptionHandler(MissingRequestHeaderException.class)
	ResponseEntity<ErrorResponse> processMissingRequestHeaderException(MissingRequestHeaderException ex) {
		String errorMessage = generateErrorMessage(ErrorMessage.MISSING_HEADER, ex.getHeaderName());
		return generateResponseEntity(ErrorCode.HEADER_VALIDATION_ERROR, errorMessage, ex);
	}
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	ResponseEntity<ErrorResponse> processMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
		String errorMessage = generateErrorMessage(ErrorMessage.INVALID_ARGUMENT, ex.getName());
		return generateResponseEntity(ErrorCode.INPUT_VALIDATION_ERROR, errorMessage, ex);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	ResponseEntity<ErrorResponse> processMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
		String fieldName = ex.getBindingResult().getFieldErrors().get(0).getField();
		
		String errorMessage = generateErrorMessage(ErrorMessage.INVALID_FIELD_IN_REQUEST_BODY, fieldName);
		return generateResponseEntity(ErrorCode.INPUT_VALIDATION_ERROR, errorMessage, ex);
	}
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	ResponseEntity<ErrorResponse> processHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
		return generateResponseEntity(ErrorCode.INPUT_VALIDATION_ERROR, ErrorMessage.INVALID_REQUEST_BODY, ex);
	}
	
	@ExceptionHandler(MissingServletRequestParameterException.class)
	ResponseEntity<ErrorResponse> processMissingServletRequestParameterException(MissingServletRequestParameterException ex) {
		String errorMessage = generateErrorMessage(ErrorMessage.INVALID_FIELD_IN_REQUEST_PARAM, ex.getParameterName());
		return generateResponseEntity(ErrorCode.INPUT_VALIDATION_ERROR, errorMessage, ex);
	}
	
	@ExceptionHandler(BusinessException.class)
	ResponseEntity<ErrorResponse> processBusinessException(BusinessException ex) {
		ErrorResponse errorResponse = new ErrorResponse(ex.getErrorCode(), ex.getMessage());
		Exception exc = (ex.getOriginalException() != null) ? ex.getOriginalException() : ex;
		return generateResponseEntity(errorResponse, ex.getHttpstatus(), exc);
	}
	
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	ResponseEntity<ErrorResponse> processHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
		return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
	}
	
	@ExceptionHandler(Exception.class)
	ResponseEntity<ErrorResponse> processException(Exception ex) {
		return generateResponseEntity(ErrorCode.INTERNAL_ERROR, ex.getLocalizedMessage(), ex);
	}
	
	private String generateErrorMessage(String coreMessage, String fieldName) {
		StringBuilder errorMessageBuilder = new StringBuilder(coreMessage);
		errorMessageBuilder.append(fieldName);
		return errorMessageBuilder.toString();
	}
	
	private ResponseEntity<ErrorResponse> generateResponseEntity(ErrorCode errorCode, String errorMessage, Exception ex) {
		ErrorResponse errorResponse = new ErrorResponse(errorCode.getCode(), errorMessage);
		return generateResponseEntity(errorResponse, errorCode.getHttpStatus(), ex);
	}
	
	private ResponseEntity<ErrorResponse> generateResponseEntity(ErrorResponse errorResponse, HttpStatus httpStatus, Exception ex) {
		LOG.error("Exception stack trace", ex);
		LOG.error("Exception occured; Returning code={}; message={}; httpStatus={}", errorResponse.getCode(), errorResponse.getMessage(), httpStatus.value());
		return new ResponseEntity<>(errorResponse, httpStatus);
	}

}
