package com.spectral.demoapplication.validation.annotation;

import com.spectral.demoapplication.validation.validator.ExistEmailValidator;
import com.spectral.demoapplication.validation.validator.RoleExistValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Represents validate for existing role in data base
 *
 */
@Target({FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = RoleExistValidator.class)
@Documented
public @interface RoleExistConstraint {
    String message() default "This role does not exist";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
