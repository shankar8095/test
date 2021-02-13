package com.example.EmployeeRest.errorresponse;

import java.io.Serializable;

public class ApiValidationError extends ApiSubError implements Serializable{
	
	
	private static final long serialVersionID=1L;
	private String object;
	private String field;
	private Object rejectedValue;
	private String message;
	
	public ApiValidationError(String object, String message) {
		this.object=object;
		this.message=message;
	}
	
	
	public ApiValidationError(String object, String message,Object rejectedValue, String field) {
		super();
		this.object=object;
		this.message=message;
		this.rejectedValue=rejectedValue;
		this.field=field;
	}
}
