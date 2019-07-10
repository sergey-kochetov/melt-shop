package ru.com.melt.info.exception;

import org.springframework.dao.DataAccessException;
import org.springframework.validation.FieldError;
import ru.com.melt.info.Constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FormValidationException extends DataAccessException {
    private static final long serialVersionUID = 1L;
    private String fieldName;
    private Object rejectedValue;
    private String errorCode;

    public FormValidationException(String fieldName, Object rejectedValue, String errorCode) {
        super("");
        this.fieldName = fieldName;
        this.rejectedValue = rejectedValue;
        this.errorCode = errorCode;
    }

    public FormValidationException(String fieldName, Object rejectedValue) {
        this(fieldName, rejectedValue, null);
    }

    public FormValidationException(String fieldName) {
        this(fieldName, null);
    }

    public FieldError buildFieldError(String formName) {
        List<String> codes = new ArrayList<>(3);
        if (errorCode != null) {
            codes.add(errorCode);
        }
        codes.addAll(Arrays.asList(new String[]{
                FormValidationException.class.getSimpleName() + "." + formName + "." + fieldName,
                formName + "." + fieldName,
                fieldName}));
        return new FieldError(formName, fieldName, rejectedValue, false, codes.toArray(Constants.EMPTY_ARRAY), Constants.EMPTY_ARRAY, toString());
    }

    @Override
    public String toString() {
        return String.format("FormValidationException [fieldName=%s, rejectedValue=%s, errorCode=%s]", fieldName, rejectedValue, errorCode);
    }
}
