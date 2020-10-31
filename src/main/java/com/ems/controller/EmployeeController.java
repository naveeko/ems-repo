package com.ems.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ems.config.AppFeaturesEnum;
import com.ems.entity.Employee;
import com.ems.exception.ResourceNotFoundException;
import com.ems.repository.EmployeeRepository;
import com.ems.service.AsyncService;

@RestController
@RequestMapping("/appservice")
public class EmployeeController {

	@Autowired
	EmployeeRepository employeeRepository;

	AsyncService asyncService;

	@GetMapping("/api/v1/employees")
	public List<Employee> getEmployees() throws ResourceNotFoundException {

		if (!AppFeaturesEnum.FEATURE_ONE.isActive()) {
			throw new ResourceNotFoundException("Get employees service is not activated");
		}
		asyncService.asyncLog("Log Message Asynchronously");
		System.out.println("CHANGING");
		System.out.println("CHANGING");
		return employeeRepository.findAll();

	}

	@PostMapping("/api/v1/employee/create")
	public Employee createEmployee(@Valid @RequestBody Employee employee) {
		System.out.println("Test");
		return employeeRepository.save(employee);

	}

	@GetMapping("/api/v1/employee/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") Long employeeId)
			throws ResourceNotFoundException {
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
		return ResponseEntity.ok().body(employee);

	}

	@PutMapping("/api/v1/employee/update")
	public ResponseEntity<Employee> updateEmployee(@Valid @RequestBody Employee employeeDetails)
			throws ResourceNotFoundException {
		Employee emp = employeeRepository.findById(employeeDetails.getId()).orElseThrow(
				() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeDetails.getId()));
		emp.setEmailId(employeeDetails.getEmailId());
		emp.setLastName(employeeDetails.getLastName());
		emp.setFirstName(employeeDetails.getFirstName());
		final Employee updatedEmployee = employeeRepository.save(emp);
		return ResponseEntity.ok(updatedEmployee);

	}

	@DeleteMapping("api/v1/employee/delete/{id}")
	public String deleteEmployee(@PathVariable(value = "id") Long employeeId) throws ResourceNotFoundException {
		employeeRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
		employeeRepository.deleteById(employeeId);

		return "Deleted employeeId " + employeeId;

	}
}
