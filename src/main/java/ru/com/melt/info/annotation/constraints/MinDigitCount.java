package ru.com.melt.info.annotation.constraints;

import ru.com.melt.info.validator.MinDigitCountConstraintValidator;

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
@Constraint(validatedBy = {MinDigitCountConstraintValidator.class})
public @interface MinDigitCount {

    String message() default "MinDigitCount";

    int value() default 1;

    Class<? extends Payload>[] payload() default {};

    Class<?>[] groups() default {};
}
