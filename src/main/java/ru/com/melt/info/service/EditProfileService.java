package ru.com.melt.info.service;

import ru.com.melt.info.entity.*;
import ru.com.melt.info.form.SignUpForm;

import java.util.List;

public interface EditProfileService {

    Profile createNewProfile(SignUpForm signUpForm);

    List<Skill> listSkills(long idProfile);

    List<SkillCategory> listSkillCategories();

    void updateSkill(long idProfile, List<Skill> skills);

    Contacts findContactsById(long idProfile);

    void updateContacts(long idProfile, Contacts contacts);

    List<Practic> findPracticsById(long idProfile);

    void updatePractics(long idProfile, List<Practic> practics);

    List<Certificate> findCertificatesById(long idProfile);

    void updateCertificates(long idProfile, List<Certificate> certificates);

    List<Course> findCoursesById(long idProfile);

    void updateCourses(long idProfile, List<Course> courses);

    List<Education> findEducationById(long idProfile);

    void updateEducation(long idProfile, List<Education> educations);

    List<Language> findLanguageById(long idProfile);

    void updateLanguage(long idProfile, List<Language> languages);

    List<Hobby> findHobbiesWithProfileSelected(long idProfile);

    void updateHobbies(long idProfile, List<String> hobbies);

    String findInfoById(long idProfile);

    void updateInfo(long idProfile, String info);

    Profile findProfileById(long idProfile);
}
