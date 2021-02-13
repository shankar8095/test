package com.example.EmployeeRest.exceptions;

public class NoDataFoundException extends Exception{
	
	public NoDataFoundException(String msg) {
		
		super(msg);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
}
