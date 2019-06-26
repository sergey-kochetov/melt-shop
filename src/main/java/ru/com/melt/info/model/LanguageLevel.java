package ru.com.melt.info.model;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

public enum LanguageLevel {
    BEGINNER,
    ELEMENTARY,
    PRE_INTERMEDIATE,
    INTERMEDIATE,
    UPPER_INTERMEDIATE,
    ADVANCE,
    PROFICIENCY;

    public String getDbValue() {
        return name().toLowerCase();
    }
    public int getSliderIntValue() {
        return ordinal();
    }

    @Converter
    public static class PersistJPAConverter implements AttributeConverter<LanguageLevel, String> {

        @Override
        public String convertToDatabaseColumn(LanguageLevel languageLevel) {
            return languageLevel.getDbValue();
        }

        @Override
        public LanguageLevel convertToEntityAttribute(String dbValue) {
            return LanguageLevel.valueOf(dbValue.toUpperCase());
        }
    }
}
