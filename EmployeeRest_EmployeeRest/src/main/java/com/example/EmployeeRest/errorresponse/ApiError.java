package com.example.EmployeeRest.errorresponse;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;

import lombok.Data;
@Data
public class ApiError implements Serializable {
	
	private static final long serialVersionUID=1L;
	
	private HttpStatus status;
	private LocalDateTime timestamp;
	private String message;
	private String debugMessage;
	private List<ApiSubError> subErrors;
	
	private ApiError() {
		timestamp = LocalDateTime.now();
	}
	public ApiError(HttpStatus status) {
		this();
		this.status=status;
	}
	public ApiError(HttpStatus status, Throwable ex) {
		this();
		this.status=status;
		this.message="Unexpected error";
		this.debugMessage=ex.getLocalizedMessage();
	}
	
	public ApiError(HttpStatus status,String message, Throwable ex) {
		this();
		this.status=status;
		this.message=message;
		this.debugMessage=ex.getLocalizedMessage();
	}
	
	private void addSubError(ApiSubError subError) {
		if(subErrors==null) {
			subErrors=new ArrayList<>();
	}
			subErrors.add(subError);
	}
	private void addValidationError(String object,String field, Object rejectedValue, String message) {
		addSubError(new ApiValidationError(object,field,rejectedValue, message));
	}
	public void addValidationError(FieldError fieldError) {
		this.addValidationError(
				fieldError.getObjectName(),
				fieldError.getField(),
				fieldError.getRejectedValue(),
				fieldError.getDefaultMessage());
	}
	
	
	
}
