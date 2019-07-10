package ru.com.melt.info.component;

import org.springframework.validation.BindingResult;
import ru.com.melt.info.annotation.constraints.FirstFieldLessThanSecond;
import ru.com.melt.info.entity.Practic;

import javax.annotation.Nonnull;
import java.lang.annotation.Annotation;
import java.util.List;

public interface FormErrorConverter {
    void convertFormErrorToFieldError(@Nonnull Class<? extends Annotation> validationAnnotationClass,
                             @Nonnull Object formInstance,
                             @Nonnull BindingResult bindingResult);

}