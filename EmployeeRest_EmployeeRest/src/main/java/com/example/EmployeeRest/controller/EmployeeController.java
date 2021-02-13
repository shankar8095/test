package com.example.EmployeeRest.controller;
import com.example.EmployeeRest.exceptions.EmployeeNotFoundException;
import com.example.EmployeeRest.exceptions.NoDataFoundException;
import com.example.EmployeeRest.model.Employee;

import java.util.Optional;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.EmployeeRest.repository.EmployeeRepository;



@CrossOrigin("*")
@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@GetMapping
	public String getEmployee()
	{
		return "Welcome";
	}
	
	//to get all employees
	@GetMapping("/all")
	public List<Employee> getAllEmployees() throws Exception{
		List list=this.employeeRepository.findAll();
		return Optional.ofNullable(list.isEmpty() ? null : list).orElseThrow(()->new NoDataFoundException("No data"));
	}
	
	//to get employee by id
	@GetMapping("/{id}")
	public ResponseEntity<?> getEmployeeById(@PathVariable("id") Integer id) throws EmployeeNotFoundException
	{
		Optional<Employee> optional = employeeRepository.findById(id);
		if(optional.isPresent())
		{
			return ResponseEntity.ok(optional.get());
		}
		else
		{
			throw new EmployeeNotFoundException("Not found");
		}
	}
	
	//to create emp
	@PostMapping("/add")
	public Employee addEmployee(@RequestBody @Valid Employee employee) throws EmployeeNotFoundException
	{
		if(employee.getEmployeeId()==null) {
			throw new EmployeeNotFoundException("cannot add employee");
		}
		return employeeRepository.save(employee);
	}
	
	
	//to delete employee
	@GetMapping("/delete/{id}")
	public String deleteEmployee(@PathVariable("id") Integer id) throws EmployeeNotFoundException{
		Optional<Employee> optional = employeeRepository.findById(id);
		
		if(optional.isPresent()) {
			employeeRepository.deleteById(id);
			return "deleted";
		}
		else {
			throw new EmployeeNotFoundException("Not found");
		}
	}
	
	
	//to update employee details
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateAccount(@PathVariable("id") Integer id, @Valid @RequestBody Employee details) throws EmployeeNotFoundException{
		Employee a=employeeRepository.findById(id).orElseThrow(()-> new EmployeeNotFoundException("Not found"));
		
		a.setFirstName(details.getFirstName());
		a.setLastName(details.getLastName());
		a.setEmail(details.getEmail());
		
		final Employee newDetails=employeeRepository.save(a);
		return ResponseEntity.ok(newDetails);
		
	}
	
	
}
