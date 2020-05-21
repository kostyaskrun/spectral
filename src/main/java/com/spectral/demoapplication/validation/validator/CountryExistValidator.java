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
     * @param email specific user
     * @param constraintValidatorContext - constraint context
     * @return true if email isn't present, that means, that we can create new user using this email
     */
    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        boolean isValid = nonNull(service.getByName(email));

        if (isValid) {
            constraintValidatorContext.buildConstraintViolationWithTemplate(message)
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
        }

        return isValid;
    }
}