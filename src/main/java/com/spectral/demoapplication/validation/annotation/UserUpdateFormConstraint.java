package com.spectral.demoapplication.validation.annotation;

import com.spectral.demoapplication.validation.validator.ExistEmailValidator;
import com.spectral.demoapplication.validation.validator.UserUpdateFormValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Represents validate for user update form
 *
 */
@Target({TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = UserUpdateFormValidator.class)
@Documented
public @interface UserUpdateFormConstraint {
    String message() default "This email is already taken";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
