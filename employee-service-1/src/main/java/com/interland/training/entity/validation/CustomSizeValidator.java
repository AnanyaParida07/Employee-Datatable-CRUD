package com.interland.training.entity.validation;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import org.springframework.util.StringUtils;


public class CustomSizeValidator implements ConstraintValidator<CustomSize, String> {

	private String maxKey;
	private String minKey;

	@Override
	public void initialize(CustomSize customSize) {
		this.maxKey = customSize.maxKey();
		this.minKey = customSize.minKey();
	}
	
//	@Override
//	public boolean isValid(String value, ConstraintValidatorContext context) {
//		int maxValue = Integer.parseInt(maxKey);
//		int minValue = Integer.parseInt(minKey);
//		if (StringUtils.hasLength(value) || minValue==0 || value.length() < maxValue) {
//			return true;
//		} 
//		if (StringUtils.hasLength(value) || value.length() < minValue || value.length() > maxValue) {
//			return false;
//		} 
//		return true;
//	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		int maxValue = Integer.parseInt(maxKey);// Integer.parseInt(ApplicationProperties.INSTANCE.getValue(maxKey));
		int minValue = Integer.parseInt(minKey); //Integer.parseInt(ApplicationProperties.INSTANCE.getValue(minKey));
		if (StringUtils.isEmpty(value)) {
			return true;
		} else if (value.length() < minValue || value.length() > maxValue) {
			return false;
		} 
		return true;
	}
}
