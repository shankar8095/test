package com.example.EmployeeRest.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Component
@Entity(name="Employee")
@Table(name="employees")
public class Employee {
	
	
	@Id
	private Integer employeeId;
	private String firstName;
	private String lastName;
	private String email;

}
