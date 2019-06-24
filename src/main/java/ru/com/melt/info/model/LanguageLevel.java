package ru.com.melt.info.model;

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
}
