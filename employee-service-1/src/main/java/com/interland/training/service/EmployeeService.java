package com.interland.training.service;

import java.util.List;

import com.interland.training.dto.EmployeeDto;
import com.interland.training.dto.ServiceResponse;
import com.interland.training.entity.EmployeesEntity;




public interface EmployeeService {

	List<EmployeesEntity> fetchAll();

	ServiceResponse createUser(EmployeeDto entity);

	EmployeesEntity fetchById(int empId, int deptId);

	ServiceResponse remove(int empId, int deptId);

	ServiceResponse updateUser(int empId, int deptId, EmployeesEntity entity);

	
	

}
