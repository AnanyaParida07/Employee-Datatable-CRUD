package com.interland.training.repository.specification;

import org.springframework.data.jpa.domain.Specification;

import com.interland.training.entity.EmployeesEntity;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class EmployeeAgeSpecification {

	public static Specification<EmployeesEntity> getEmployeesByAge(int minAge, int maxAge) {
		return new Specification<EmployeesEntity>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<EmployeesEntity> root, CriteriaQuery<?> query,
					CriteriaBuilder criteriaBuilder) {
				Predicate finalPredicate = criteriaBuilder.between(root.get("age"), minAge, maxAge);

				return finalPredicate;
			}
		};
	}

}
