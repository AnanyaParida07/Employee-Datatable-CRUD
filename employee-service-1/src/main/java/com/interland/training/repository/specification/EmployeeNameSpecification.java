package com.interland.training.repository.specification;

import org.springframework.data.jpa.domain.Specification;
import com.interland.training.entity.EmployeesEntity;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class EmployeeNameSpecification {

    public static Specification<EmployeesEntity> getByFirstCharacter(String firstChar) {
        return new Specification<EmployeesEntity>() {
            private static final long serialVersionUID = 1L;

            @Override
            public Predicate toPredicate(Root<EmployeesEntity> root, CriteriaQuery<?> query,
                                         CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like(root.get("firstName"), firstChar.toLowerCase() + "%");
            }
        };   
    }
}
