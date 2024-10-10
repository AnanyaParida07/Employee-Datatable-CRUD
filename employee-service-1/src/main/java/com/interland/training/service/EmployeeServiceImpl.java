package com.interland.training.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.interland.training.dto.EmployeeDto;
import com.interland.training.dto.ServiceResponse;
import com.interland.training.entity.EmployeePK;
import com.interland.training.entity.EmployeesEntity;
import com.interland.training.exception.EmployeeNotFoundException;
import com.interland.training.repository.EmployeeRepository;

import jakarta.validation.ValidationException;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	private static final Logger logger = LogManager.getLogger(EmployeeServiceImpl.class);
	@Autowired
	EmployeeRepository repo;

	@Override
	public ServiceResponse createUser(EmployeeDto entity) {
		try {
			EmployeePK id = new EmployeePK(entity.getEmpId(), entity.getDeptId());

			if (repo.existsById(id)) {
				logger.warn("USER ALREADY EXISTS");
				return new ServiceResponse("Failed", "ALREADY EXISTS", null);
			} else {
				EmployeesEntity newEntity = new EmployeesEntity();
				newEntity.setIds(id);
				BeanUtils.copyProperties(entity, newEntity);
				repo.save(newEntity);
				logger.info("ADDED SUCCESSFULLY");
				return new ServiceResponse("Success", "USER ADDED", null);
			}
		} catch (Exception e) {
			logger.error("Error Occurred: " + e.getMessage());
			return new ServiceResponse("Failed", "ERROR OCCURRED", null);
		}
	}

	@Override
	public ServiceResponse remove(int empId, int deptId) {
		try {
			EmployeePK id = new EmployeePK(empId, deptId);
			if (repo.existsById(id)) {
				repo.deleteById(id);
				logger.info("DELETED SUCESSFULLY");
				return new ServiceResponse("Success", "USER DELETED", null);
			} else {
				logger.warn("ID DOES NOT EXIST");
				return new ServiceResponse("Failed", "ID DOES NOT EXISTS", null);
			}
		} catch (Exception e) {
			logger.error("Error Occurred: " + e.getMessage());
			return new ServiceResponse("Failed", "ERROR OCCURRED", null);
		}
	}

	@Override
	public ServiceResponse updateUser(int empId, int deptId, EmployeesEntity entity) {
		try {
			EmployeePK id = new EmployeePK(empId, deptId);
			Optional<EmployeesEntity> emp = repo.findById(id);
			if (emp.isPresent()) {
				EmployeesEntity employee = emp.get();

				if (StringUtils.hasLength(entity.getFirstName())) {
					employee.setFirstName(entity.getFirstName());
				}
				if (StringUtils.hasLength(entity.getLastName())) {
					employee.setLastName(entity.getLastName());
				}
				if (StringUtils.hasLength(entity.getEmailId())) {
					employee.setEmailId(entity.getEmailId());
				}
				if (entity.getAge() != 0) {
					employee.setAge(entity.getAge());
				}

				if (entity.getSalary() != 0) {
					employee.setSalary(entity.getSalary());
				}
				if (StringUtils.hasLength(entity.getRole())) {
					employee.setRole(entity.getRole());
				}

				repo.save(employee);
				logger.info("DATA UPDATED SUCESSFULLY");
				return new ServiceResponse("Success", "DATA UPDATED", null);
			} else {
				logger.warn("ID DOES NOT EXIST");
			}
		} catch (ValidationException e) {
			logger.error("Validation failed: " + e.getMessage());
			return new ServiceResponse("Failed", "Validation Error: " + e.getMessage(), null);
		} catch (Exception e) {
			logger.error("Unexpected error occurred: " + e.getMessage());
			return new ServiceResponse("Failed", "Unexpected Error Occurred", null);
		}
		throw new EmployeeNotFoundException("Employee ID does not exist");
	}

//	@Override
//	public EmployeeDto fetchById(int empId, int deptId) {
//		try {
//			EmployeePK emp = new EmployeePK(empId, deptId);
//			if (repo.existsById(emp)) {
//				return repo.findById(emp).get();
//			} else {
//				logger.info("ID Does not exist");
//			}
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
//		throw new EmployeeNotFoundException("Employee ID does not exist");
//	}
	
	@Override
	public EmployeeDto fetchById(int empId, int deptId) {
	    try {
			EmployeePK emp = new EmployeePK(empId, deptId);
	        Optional<EmployeesEntity> projectEntityOptional = repo.findById(emp);
	        if (projectEntityOptional.isPresent()) {
	            EmployeesEntity project = projectEntityOptional.get();
	            logger.info(project);
	            EmployeeDto projectDto = new EmployeeDto(
	            		project.getIds().getEmpId(), project.getIds().getDeptId(),
						project.getFirstName(), project.getLastName(), project.getAge(), project.getEmailId(),
						project.getSalary(), project.getRole()
	            );
	            return projectDto;
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    throw new EmployeeNotFoundException("ID DOESN'T EXIST");
	}

	@Override
	public List<EmployeeDto> fetchAll() {
		try {
			List<EmployeesEntity> projects = repo.findAll();

			return projects.stream()
					.map(project -> new EmployeeDto(project.getIds().getEmpId(), project.getIds().getDeptId(),
							project.getFirstName(), project.getLastName(), project.getAge(), project.getEmailId(),
							project.getSalary(), project.getRole()))
					.collect(Collectors.toList());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return List.of();
	}

}
