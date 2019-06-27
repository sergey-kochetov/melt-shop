package ru.com.melt.info.annotation.constraints;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {})
public @interface MinSpecCharCount {

    String message() default "MinSpecCharCount";

    int value() default 1;

    String specSymbols() default "!@~`#$%^&*()_-+=|\\/{}[].,;:/?";

    Class<? extends Payload>[] payload() default {};

    Class<?>[] groups() default {};
}
