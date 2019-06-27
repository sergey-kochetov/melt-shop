package ru.com.melt.info.service.impl;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import ru.com.melt.info.entity.Profile;
import ru.com.melt.info.entity.Skill;
import ru.com.melt.info.entity.SkillCategory;
import ru.com.melt.info.exception.CantCompleteClientRequestException;
import ru.com.melt.info.form.SignUpForm;
import ru.com.melt.info.repository.storage.ProfileRepository;
import ru.com.melt.info.repository.storage.SkillCategoryRepository;
import ru.com.melt.info.service.EditProfileService;
import ru.com.melt.info.util.DataUtil;

import java.util.List;

public class EditProfileServiceImpl implements EditProfileService {
    private static final Logger LOGGER = LoggerFactory.getLogger(EditProfileServiceImpl.class);

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private SkillCategoryRepository skillCategoryRepository;

    @Value("${generate.uid.alphabet}")
    private String generateUidAlphabet;

    @Value("${generate.uid.suffix.length}")
    private int generateUidSuffixLength;

    @Value("${generate.uid.max.try.count}")
    private int generateUidMaxTryCount;

    @Override
    @Transactional
    public Profile createNewProfile(SignUpForm signUpForm) {
        Profile profile = new Profile();
        profile.setUid(generateProfileUid(signUpForm));
        profile.setFirstName(DataUtil.capitalizeName(signUpForm.getFirstName()));
        profile.setLastName(DataUtil.capitalizeName(signUpForm.getLastName()));
        profile.setPassword(signUpForm.getPassword());
        profile.setCompleted(false);
        profileRepository.save(profile);
        return null;
    }

    @Override
    public List<Skill> listSkills(long idProfile) {
        return profileRepository.findOne(idProfile).getSkills();
    }

    @Override
    public List<SkillCategory> listSkillCategories() {
        return skillCategoryRepository.findAll(new Sort("id"));
    }

    @Override
    @Transactional
    public void updateSkill(long idProfile, List<Skill> updateSkills) {
        Profile profile = profileRepository.findOne(idProfile);
        if (CollectionUtils.isEqualCollection(updateSkills, profile.getSkills())) {
            LOGGER.debug("Profile skills: nothing to update");
            return;
        } else {
            profile.setSkills(updateSkills);
            profileRepository.save(profile);
        }
    }

    private String generateProfileUid(SignUpForm signUpForm) {
        String baseUid = DataUtil.generateProfileUid(signUpForm);
        String uid = baseUid;
        for (int i = 0; profileRepository.countByUid(uid) > 0; i++) {
            uid = DataUtil.regenerateUidWithRandomSuffix(baseUid, generateUidAlphabet, generateUidSuffixLength);
            if (i >= generateUidMaxTryCount) {
                throw new CantCompleteClientRequestException("Can't generate unique uid for profile: " +
                        baseUid + ": generateUidMaxTryCount detected");
            }
        }
        return uid;
    }
}
