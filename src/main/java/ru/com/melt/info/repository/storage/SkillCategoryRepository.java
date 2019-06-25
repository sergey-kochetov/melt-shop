package ru.com.melt.info.repository.storage;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.RepositoryDefinition;
import ru.com.melt.info.entity.SkillCategory;

import java.util.List;

@RepositoryDefinition(domainClass = SkillCategory.class, idClass = Long.class)
public interface SkillCategoryRepository {
    List<SkillCategory> findAll(Sort sort);
}
