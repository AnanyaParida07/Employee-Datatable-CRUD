package com.interland.training.entity.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CustomNumberSizeValidator implements ConstraintValidator<CustomNumberSize, Integer> {

	private int maxKey;
	private int minKey;

	@Override
	public void initialize(CustomNumberSize customSize) {
		this.maxKey = customSize.maxKey();
		this.minKey = customSize.minKey();
	}

	@Override
	public boolean isValid(Integer value, ConstraintValidatorContext context) {

		if (value == null) {
			return false;
		} else if (value < minKey || value > maxKey) {
			return false;
		}
		return true;
	}

}
