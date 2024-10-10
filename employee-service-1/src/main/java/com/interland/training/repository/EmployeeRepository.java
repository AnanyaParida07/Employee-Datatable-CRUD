package com.interland.training.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.interland.training.entity.EmployeePK;
import com.interland.training.entity.EmployeesEntity;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeesEntity, EmployeePK>,JpaSpecificationExecutor<EmployeesEntity>{

	@Query("select e from EmployeesEntity e where e.role=?1")
	List<EmployeesEntity> findByRole(String role);

	@Query("select e from EmployeesEntity e where e.salary > :salary")
	List<EmployeesEntity> findSalaryGreaterThan(double salary);

}
