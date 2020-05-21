package com.spectral.demoapplication.validation.validator;

import com.spectral.demoapplication.model.entity.User;
import com.spectral.demoapplication.model.form.UserUpdateForm;
import com.spectral.demoapplication.service.UserService;
import com.spectral.demoapplication.validation.annotation.UserUpdateFormConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

public class UserUpdateFormValidator implements ConstraintValidator<UserUpdateFormConstraint, UserUpdateForm> {

    private String message;
    private UserService service;

    public UserUpdateFormValidator(UserService service) {
        this.service = service;
    }

    @Override
    public void initialize(UserUpdateFormConstraint userUpdateFormConstraint) {
        this.message = userUpdateFormConstraint.message();
    }

    /**
     * @param form                       update form
     * @param constraintValidatorContext constraint context
     * @return true if email hasn't changed and if email isn't present in db, otherwise false
     */
    @Override
    public boolean isValid(UserUpdateForm form, ConstraintValidatorContext constraintValidatorContext) {
        Optional<User> optionalUser = service.findUserByEmail(form.getEmail());
        boolean isValid = true;
        if (optionalUser.isPresent()) {
            isValid = optionalUser.get().getId().equals(form.getUserId());
        }

        if (isValid) {
            constraintValidatorContext.buildConstraintViolationWithTemplate(message)
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
        }

        return isValid;
    }
}