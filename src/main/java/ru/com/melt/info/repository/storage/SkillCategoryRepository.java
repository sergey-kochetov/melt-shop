package ru.com.melt.info.repository.storage;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.com.melt.info.entity.SkillCategory;

import java.util.List;

@Repository
public interface SkillCategoryRepository  extends JpaRepository<SkillCategory, Long> {
    List<SkillCategory> findAll(Sort sort);
}
