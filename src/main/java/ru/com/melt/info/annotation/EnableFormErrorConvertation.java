package ru.com.melt.info.annotation;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EnableFormErrorConvertation {
    String formName();

    String fieldReference();

    Class<? extends Annotation> validationAnnotationClass();

    @Target({ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface List {
        EnableFormErrorConvertation[] value();
    }
}
