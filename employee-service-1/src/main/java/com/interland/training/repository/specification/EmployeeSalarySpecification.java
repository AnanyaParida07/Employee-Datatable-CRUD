package com.interland.training.repository.specification;

import org.springframework.data.jpa.domain.Specification;

import com.interland.training.entity.EmployeesEntity;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class EmployeeSalarySpecification {

	public static Specification<EmployeesEntity> getEmployeeBySalary(double minSal, double maxSal) {
		return new Specification<EmployeesEntity>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<EmployeesEntity> root, CriteriaQuery<?> query,
					CriteriaBuilder criteriaBuilder) {

				Predicate finalPredicate = criteriaBuilder.between(root.get("salary"), minSal, maxSal);
				return finalPredicate;
			}
		};
	}
}
