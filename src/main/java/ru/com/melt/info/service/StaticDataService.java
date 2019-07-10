package ru.com.melt.info.service;

import ru.com.melt.info.entity.Hobby;
import ru.com.melt.info.model.LanguageLevel;
import ru.com.melt.info.model.LanguageType;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface StaticDataService {

    @Nonnull
    Set<Hobby> listAllHobbies();

    @Nonnull
    List<Hobby> createHobbyEntitiesByNames(@Nonnull List<String> names);

    @Nonnull
    Map<Integer, String> mapMonths();

    @Nonnull
    List<Integer> listPracticsYears();

    @Nonnull
    List<Integer> listCourcesYears();

    @Nonnull
    List<Integer> listEducationYears();

    @Nonnull
    Collection<LanguageType> getAllLanguageTypes();

    @Nonnull
    Collection<LanguageLevel> getAllLanguageLevels();
}
