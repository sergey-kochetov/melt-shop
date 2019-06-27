package ru.com.melt.info.validator;

import ru.com.melt.info.annotation.constraints.MinSpecCharCount;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MinSpecCharCountConstraintValidator implements ConstraintValidator<MinSpecCharCount, CharSequence>  {

    private int minValue;
    private String specSymbols;

    @Override
    public void initialize(MinSpecCharCount constraintAnnotation) {
        minValue = constraintAnnotation.value();
        specSymbols = constraintAnnotation.specSymbols();
    }

    @Override
    public boolean isValid(CharSequence value, ConstraintValidatorContext context) {
        int count = 0;
        for(int i=0;i<value.length();i++){
            if(specSymbols.indexOf(value.charAt(i)) != -1) {
                count++;
            }
        }
        return count >= minValue;
    }
}