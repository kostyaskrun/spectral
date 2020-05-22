package com.spectral.demoapplication.validation.validator;

import com.spectral.demoapplication.service.CountryService;
import com.spectral.demoapplication.validation.annotation.CountryExistConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import static java.util.Objects.nonNull;

public class CountryExistValidator implements ConstraintValidator<CountryExistConstraint, String> {

    private String message;
    private CountryService service;

    public CountryExistValidator(CountryService service) {
        this.service = service;
    }

    @Override
    public void initialize(CountryExistConstraint countryExistConstraint) {
        this.message = countryExistConstraint.message();
    }

    /**
     * @param countryName specific country name
     * @param constraintValidatorContext - constraint context
     * @return true if country present, that means, that we can create new user using this country name
     */
    @Override
    public boolean isValid(String countryName, ConstraintValidatorContext constraintValidatorContext) {
        boolean isValid = nonNull(service.getByName(countryName));

        if (isValid) {
            constraintValidatorContext.buildConstraintViolationWithTemplate(message)
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
        }

        return isValid;
    }
}