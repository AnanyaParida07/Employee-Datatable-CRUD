package com.interland.training.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.interland.training.dto.EmployeeDto;
import com.interland.training.dto.ServiceResponse;
import com.interland.training.entity.EmployeesEntity;
import com.interland.training.service.EmployeeService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/employee")
@CrossOrigin(origins = "http://localhost:4200")
public class EmployeeController {

	@Autowired
	EmployeeService service;

	@PostMapping("/add")
	public ResponseEntity<ServiceResponse> adduser(@Valid @RequestBody EmployeeDto entity) {
		return new ResponseEntity<ServiceResponse>(service.createUser(entity), HttpStatus.OK);
	}

	@DeleteMapping("/delete/{empId}/{deptId}")
	public ResponseEntity<ServiceResponse> deleteUser(@PathVariable int empId, @PathVariable int deptId) {
		return new ResponseEntity<ServiceResponse>(service.remove(empId, deptId), HttpStatus.OK);
	}
   
	@PutMapping("update/{empId}/{deptId}")
	public ResponseEntity<ServiceResponse> putMethodName(@PathVariable int empId, @PathVariable int deptId,
			@Valid @RequestBody EmployeesEntity entity) {
		return new ResponseEntity<ServiceResponse>(service.updateUser(empId, deptId, entity), HttpStatus.OK);
	}

	@GetMapping("/get/{empId}/{deptId}")
	public EmployeesEntity getMethodName(@PathVariable int empId, @PathVariable int deptId) {
		return service.fetchById(empId, deptId); 
	}

	@GetMapping("/getAll")
	public List<EmployeesEntity> getAll() {
		return service.fetchAll();
	}

}
