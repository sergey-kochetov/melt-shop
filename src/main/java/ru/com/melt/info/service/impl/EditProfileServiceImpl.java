package ru.com.melt.info.service.impl;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import ru.com.melt.info.entity.*;
import ru.com.melt.info.exception.CantCompleteClientRequestException;
import ru.com.melt.info.form.SignUpForm;
import ru.com.melt.info.repository.search.ProfileSearchRepository;
import ru.com.melt.info.repository.storage.ProfileRepository;
import ru.com.melt.info.repository.storage.SkillCategoryRepository;
import ru.com.melt.info.service.EditProfileService;
import ru.com.melt.info.util.DataUtil;
import ru.com.melt.info.util.SecurityUtil;

import java.util.ArrayList;
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

    @Override
    public List<Practic> findPracticsById(long idProfile) {
        return profileRepository.findOne(idProfile).getPractics();
    }

    @Override
    @Transactional
    public void updatePractics(long idProfile, List<Practic> practics) {
        Profile profile = profileRepository.findOne(idProfile);
        if (CollectionUtils.isEqualCollection(practics, profile.getPractics())) {
            LOGGER.debug("Profile practics: nothing to update");
        } else {
            profile.setPractics(practics);
            profileRepository.save(profile);
        }
    }

    @Override
    public List<Certificate> findCertificatesById(long idProfile) {
        return profileRepository.findOne(idProfile).getCertificates();
    }

    @Override
    @Transactional
    public void updateCertificates(long idProfile, List<Certificate> certificates) {
        Profile profile = profileRepository.findOne(idProfile);
        if (CollectionUtils.isEqualCollection(certificates, profile.getCertificates())) {
            LOGGER.debug("Profile certificates: nothing to update");
        } else {
            profile.setCertificates(certificates);
            profileRepository.save(profile);
        }
    }

    @Override
    public List<Course> findCoursesById(long idProfile) {
        return profileRepository.findOne(idProfile).getCourses();
    }

    @Override
    @Transactional
    public void updateCourses(long idProfile, List<Course> courses) {
        Profile profile = profileRepository.findOne(idProfile);
        if (CollectionUtils.isEqualCollection(courses, profile.getCourses())) {
            LOGGER.debug("Profile courses: nothing to update");
        } else {
            profile.setCourses(courses);
            profileRepository.save(profile);
        }
    }

    @Override
    public List<Education> findEducationById(long idProfile) {
        return profileRepository.findOne(idProfile).getEducations();
    }

    @Override
    @Transactional
    public void updateEducation(long idProfile, List<Education> educations) {
        Profile profile = profileRepository.findOne(idProfile);
        if (CollectionUtils.isEqualCollection(educations, profile.getCourses())) {
            LOGGER.debug("Profile educations: nothing to update");
        } else {
            profile.setEducations(educations);
            profileRepository.save(profile);
        }
    }

    @Override
    public List<Language> findLanguageById(long idProfile) {
        return profileRepository.findOne(idProfile).getLanguages();
    }

    @Override
    public void updateLanguage(long idProfile, List<Language> languages) {
        Profile profile = profileRepository.findOne(idProfile);
        if (CollectionUtils.isEqualCollection(languages, profile.getCourses())) {
            LOGGER.debug("Profile languages: nothing to update");
        } else {
            profile.setLanguages(languages);
            profileRepository.save(profile);
        }
    }

    @Override
    public List<Hobby> findHobbiesWithProfileSelected(long idProfile) {
        List<Hobby> profileHobbies = profileRepository.findOne(idProfile).getHobbies();
        List<Hobby> hobbies = new ArrayList<>();
        for (Hobby h : profileRepository.findOne(SecurityUtil.getCurrentIdProfile()).getHobbies()) {
            boolean selected = profileHobbies.contains(h);
            hobbies.add(new Hobby(h.getName(), selected));
        }
        return hobbies;
    }

    @Override
    @Transactional
    public void updateHobbies(long idProfile, List<String> hobbies) {

    }

    @Override
    public String findInfoById(long idProfile) {
        return profileRepository.findOne(idProfile).getInfo();
    }

    @Override
    @Transactional
    public void updateInfo(long idProfile, String info) {
        Profile loadedProfile = profileRepository.findOne(idProfile);
        if (!StringUtils.equals(loadedProfile.getInfo(), info)) {
            loadedProfile.setInfo(info);
            profileRepository.save(loadedProfile);
        } else {
            LOGGER.debug("Profile info not updated");
        }
    }

    @Override
    public Profile findProfileById(long idProfile) {
        return profileRepository.findOne(idProfile);
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
