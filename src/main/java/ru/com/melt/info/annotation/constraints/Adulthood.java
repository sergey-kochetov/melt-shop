package ru.com.melt.info.annotation.constraints;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {})
public @interface Adulthood {

    String message() default "Adulthood";

    int adulthoodAge() default 18;

    Class<? extends Payload>[] payload() default {};

    Class<?>[] groups() default {};
}
