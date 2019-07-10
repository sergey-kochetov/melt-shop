package ru.com.melt.info.annotation.constraints;

import ru.com.melt.info.validator.FirstFieldLessThanSecondConstraintValidator;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({TYPE, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = {FirstFieldLessThanSecondConstraintValidator.class})
@Documented
public @interface FirstFieldLessThanSecond {
    String message() default "FirstFieldLessThanSecond";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String first();

    String second();

    @Target({TYPE, ANNOTATION_TYPE})
    @Retention(RUNTIME)
    @Documented
    @interface List {
        FirstFieldLessThanSecond[] value();
    }
}