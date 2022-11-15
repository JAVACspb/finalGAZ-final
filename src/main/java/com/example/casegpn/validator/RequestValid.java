package com.example.casegpn.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = {Validator.class})
@Target({FIELD})
@Retention(RUNTIME)
public @interface RequestValid {
    String message() default "eror";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
