package com.bumblebee.repairlog.annotation;

import com.bumblebee.repairlog.validator.NotEmptyFileValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

/**
 * @author aothoi
 * @since 02-Apr-25
 */

@Constraint(validatedBy = NotEmptyFileValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface NotEmptyFile {

    String message() default "must not be null";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}