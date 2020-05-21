package com.spectral.demoapplication.validation.annotation;

import com.spectral.demoapplication.validation.validator.ExistEmailValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Represents validate for existing e-mail in data base
 *
 */
@Target({FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = ExistEmailValidator.class)
@Documented
public @interface CountryExistConstraint {
    String message() default "This country does not exist";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
