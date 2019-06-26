package ru.com.melt.info.model;

import javax.persistence.AttributeConverter;

public enum LanguageType {
    ALL,
    SPOKEN,
    WRITING;

    public String getDbValue() {
        return name().toLowerCase();
    }

    public LanguageType getReverseType() {
        if (this == SPOKEN) {
            return WRITING;
        } else if (this == WRITING) {
            return SPOKEN;
        } else {
            throw new IllegalArgumentException(this + " does not have reverse type");
        }
    }

    public class PersistJPAConverter implements AttributeConverter<LanguageType, String> {
        @Override
        public String convertToDatabaseColumn(LanguageType languageType) {
            return languageType.getDbValue();
        }

        @Override
        public LanguageType convertToEntityAttribute(String dbValue) {
            return LanguageType.valueOf(dbValue.toUpperCase());
        }
    }
}
