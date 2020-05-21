package com.spectral.demoapplication.validation.validator;

import com.spectral.demoapplication.service.RoleService;
import com.spectral.demoapplication.service.UserService;
import com.spectral.demoapplication.validation.annotation.EmailExistConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class RoleExistValidator implements ConstraintValidator<EmailExistConstraint, List<String>> {

    private String message;
    private RoleService service;

    public RoleExistValidator(RoleService service) {
        this.service = service;
    }

    @Override
    public void initialize(EmailExistConstraint emailExistConstraint) {
        this.message = emailExistConstraint.message();
    }

    /**
     * @param names specific user
     * @param constraintValidatorContext - constraint context
     * @return true if email isn't present, that means, that we can create new user using this email
     */
    @Override
    public boolean isValid(List<String> names, ConstraintValidatorContext constraintValidatorContext) {
        boolean isValid = !service.getRolesByNames(names).isEmpty();

        if (isValid) {
            constraintValidatorContext.buildConstraintViolationWithTemplate(message)
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
        }

        return isValid;
    }
}