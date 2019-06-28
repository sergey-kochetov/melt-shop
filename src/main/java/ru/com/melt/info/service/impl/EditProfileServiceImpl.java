package ru.com.melt.info.service.impl;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import ru.com.melt.info.entity.Contacts;
import ru.com.melt.info.entity.Profile;
import ru.com.melt.info.entity.Skill;
import ru.com.melt.info.entity.SkillCategory;
import ru.com.melt.info.exception.CantCompleteClientRequestException;
import ru.com.melt.info.form.SignUpForm;
import ru.com.melt.info.repository.search.ProfileSearchRepository;
import ru.com.melt.info.repository.storage.ProfileRepository;
import ru.com.melt.info.repository.storage.SkillCategoryRepository;
import ru.com.melt.info.service.EditProfileService;
import ru.com.melt.info.util.DataUtil;

import java.util.Collections;
import java.util.List;

@Service
public class EditProfileServiceImpl implements EditProfileService {
    private static final Logger LOGGER = LoggerFactory.getLogger(EditProfileServiceImpl.class);

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private SkillCategoryRepository skillCategoryRepository;

    @Autowired
    private ProfileSearchRepository profileSearchRepository;

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
        registerCreateIndexProfileIfTransactionSuccess(profile);
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
            registerUpdateIndexProfileSkillsIfTransactionSuccess(idProfile, updateSkills);
        }
    }

    @Override
    public Contacts findContactsById(long idProfile) {
        return profileRepository.findOne(idProfile).getContacts();
    }

    @Override
    @Transactional
    public void updateContacts(long idProfile, Contacts contacts) {
        Profile profile = profileRepository.findOne(idProfile);
        int copiedFieldsCount = DataUtil.copyFields(contacts, profile.getContacts());
        if (copiedFieldsCount > 0) {
            profileRepository.save(profile);
        } else {
            LOGGER.debug("Profile contacts not updated");
        }

    }

    private void registerUpdateIndexProfileSkillsIfTransactionSuccess(long idProfile, List<Skill> updateSkills) {
        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronizationAdapter() {
            @Override
            public void afterCommit() {
                LOGGER.info("Profile skills updated");
                updateIndexProfileSkills(idProfile, updateSkills);
            }
        });
    }

    private void updateIndexProfileSkills(long idProfile, List<Skill> updateSkills) {
        Profile profile = profileSearchRepository.findOne(idProfile);
        profile.setSkills(updateSkills);
        profileSearchRepository.save(profile);
        LOGGER.info("Profile skills index updated");
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

    private void registerCreateIndexProfileIfTransactionSuccess(Profile profile) {
        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronizationAdapter() {
            @Override
            public void afterCommit() {
                LOGGER.info("New profile created: {}", profile.getUid());
                profile.setCertificates(Collections.EMPTY_LIST);
                profile.setPractics(Collections.EMPTY_LIST);
                profile.setLanguages(Collections.EMPTY_LIST);
                profile.setSkills(Collections.EMPTY_LIST);
                profile.setCourses(Collections.EMPTY_LIST);
                profileSearchRepository.save(profile);
                LOGGER.info("New profile index created: {}", profile.getUid());
            }
        });
    }
}
