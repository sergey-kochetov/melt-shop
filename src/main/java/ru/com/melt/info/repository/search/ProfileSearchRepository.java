package ru.com.melt.info.repository.search;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import ru.com.melt.info.entity.Profile;

public interface ProfileSearchRepository extends ElasticsearchRepository<Profile, Long> {

    Page<Profile> findByObjectiveLikeOrSummaryLikeOrInfoLikeOrCertificatesNameLikeOrLanguagesNameLikeOrPracticsCompanyLikeOrPracticsPositionLikeOrPracticsResponsibilitiesLikeOrSkillsValueLike(
            String objective, String info, String summary, String certificateName, String languageName, String practicCompany,
            String practicPosition, String practicResponsibility, String skillValue, Pageable pageable);

}