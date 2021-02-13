package com.example.EmployeeRest.exceptions;

public class EmployeeNotFoundException extends Exception {
	
	public EmployeeNotFoundException(String msg) {
		super(msg);
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
}
