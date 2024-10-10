package com.interland.training.repository.specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import com.interland.training.entity.EmployeesEntity;

public class EmployeeSpecification {
	private static Logger logger = LogManager.getLogger(EmployeeSpecification.class);

	public static Specification<EmployeesEntity> getStreamBySearchSpec(String searchParam) {
		return new Specification<EmployeesEntity>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@SuppressWarnings("deprecation")
			@Override
			public Predicate toPredicate(Root<EmployeesEntity> root, CriteriaQuery<?> query,
					CriteriaBuilder criteriaBuilder) {
				Predicate finalPredicate = criteriaBuilder.conjunction();
				JSONParser parser = new JSONParser();
				JSONObject searchObject;

				try {
					if (!StringUtils.isEmpty(searchParam)) {
						searchObject = (JSONObject) parser.parse(searchParam);

						String empId = (String) searchObject.get("emp_id");
						String deptId = (String) searchObject.get("dept_id");
						String firstName = (String) searchObject.get("first_name");
						String lastName = (String) searchObject.get("last_name");
						String role = (String) searchObject.get("role");
						String salaryStr = (String) searchObject.get("salary");
						String ageStr = (String) searchObject.get("age");
						Double salary = null;
						Integer age = null;

						if (salaryStr != null) {
							salary = Double.parseDouble(salaryStr);
						}

						if (ageStr != null) {
							age = Integer.parseInt(ageStr);
						}

						if (!StringUtils.isEmpty(empId)) {
							Predicate empIdPredicate = criteriaBuilder.equal(root.get("ids").get("empId"), empId);
							finalPredicate = criteriaBuilder.and(finalPredicate, empIdPredicate);
						}
  
						if (!StringUtils.isEmpty(deptId)) {
							Predicate deptIdPredicate = criteriaBuilder.equal(root.get("ids").get("deptId"), deptId);
							finalPredicate = criteriaBuilder.and(finalPredicate, deptIdPredicate);
						}

						if (!StringUtils.isEmpty(firstName)) {
							Predicate firstNamePredicate = criteriaBuilder.like(root.get("first_name"),
									"%" + firstName + "%");
							finalPredicate = criteriaBuilder.and(finalPredicate, firstNamePredicate);
						}

						if (!StringUtils.isEmpty(lastName)) {
							Predicate lastNamePredicate = criteriaBuilder.like(root.get("last_name"),
									"%" + lastName + "%");
							finalPredicate = criteriaBuilder.and(finalPredicate, lastNamePredicate);
						}

						if (!StringUtils.isEmpty(role)) {
							Predicate rolePredicate = criteriaBuilder.equal(root.get("role"), role);
							finalPredicate = criteriaBuilder.and(finalPredicate, rolePredicate);
						}

						if (salary != null) {
							Predicate salaryPredicate = criteriaBuilder.equal(root.get("salary"), salary);
							finalPredicate = criteriaBuilder.and(finalPredicate, salaryPredicate);
						}

						if (age != null) {
							Predicate agePredicate = criteriaBuilder.equal(root.get("age"), age);
							finalPredicate = criteriaBuilder.and(finalPredicate, agePredicate);
						}

					}
				} catch (ParseException e) {
					logger.error("Error parsing search parameters: " + e.getMessage(), e);
				} catch (NumberFormatException e) {
					logger.error("Error converting salary: " + e.getMessage(), e);
				}

				return finalPredicate;
			}
		};
	}

}
