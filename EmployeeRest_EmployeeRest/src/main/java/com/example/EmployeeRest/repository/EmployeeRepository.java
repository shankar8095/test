package com.example.EmployeeRest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.EmployeeRest.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
