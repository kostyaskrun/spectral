package com.spectral.demoapplication.validation.validator;

import com.spectral.demoapplication.service.UserService;
import com.spectral.demoapplication.validation.annotation.EmailExistConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Represents validator for {@link EmailExistConstraint} implements {@link ConstraintValidator}
 *
 */
public class ExistEmailValidator implements ConstraintValidator<EmailExistConstraint, String> {

    private String message;
    private UserService service;

    public ExistEmailValidator(UserService service) {
        this.service = service;
    }

    @Override
    public void initialize(EmailExistConstraint emailExistConstraint) {
        this.message = emailExistConstraint.message();
    }

    /**
     * @param email specific user
     * @param constraintValidatorContext - constraint context
     * @return true if email isn't present, that means, that we can create new user using this email
     */
    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        boolean isValid = !service.findUserByEmail(email).isPresent();

        if (isValid) {
            constraintValidatorContext.buildConstraintViolationWithTemplate(message)
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
        }

        return isValid;
    }
}