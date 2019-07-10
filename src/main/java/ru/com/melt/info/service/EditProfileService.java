package ru.com.melt.info.service;

import org.springframework.web.multipart.MultipartFile;
import ru.com.melt.info.entity.*;
import ru.com.melt.info.form.InfoForm;
import ru.com.melt.info.form.PasswordForm;
import ru.com.melt.info.form.SignUpForm;
import ru.com.melt.info.model.CurrentProfile;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public interface EditProfileService {

    @Nullable
    Profile findProfileById(@Nonnull CurrentProfile currentProfile);

    @Nonnull
    Contacts findContactsById(@Nonnull CurrentProfile currentProfile);

    @Nonnull
    Profile createNewProfile(@Nonnull SignUpForm signUpForm);

    void removeProfile(@Nonnull CurrentProfile currentProfile);

    @Nonnull
    Profile updateProfilePassword(@Nonnull CurrentProfile currentProfile, @Nonnull PasswordForm form);

    void updateProfileData(@Nonnull CurrentProfile currentProfile, @Nonnull Profile data, @Nonnull MultipartFile uploadPhoto);

    void updateContacts(@Nonnull CurrentProfile currentProfile, @Nonnull Contacts data);

    void updateInfo(@Nonnull CurrentProfile currentProfile, @Nonnull InfoForm form);

    @Nonnull
    List<Hobby> listHobbiesWithProfileSelected(@Nonnull CurrentProfile currentProfile);

    void updateHobbies(@Nonnull CurrentProfile currentProfile, @Nonnull List<String> hobbies);

    @Nonnull
    List<Language> listLanguages(@Nonnull CurrentProfile currentProfile);

    void updateLanguages(@Nonnull CurrentProfile currentProfile, @Nonnull List<Language> languages);

    @Nonnull
    List<Skill> listSkills(@Nonnull CurrentProfile currentProfile);

    @Nonnull
    List<SkillCategory> listSkillCategories();

    void updateSkills(@Nonnull CurrentProfile currentProfile, @Nonnull List<Skill> skills);

    @Nonnull
    List<Practic> listPractics(@Nonnull CurrentProfile currentProfile);

    void updatePractics(@Nonnull CurrentProfile currentProfile, @Nonnull List<Practic> practics);

    @Nonnull
    List<Education> listEducations(@Nonnull CurrentProfile currentProfile);

    void updateEducations(@Nonnull CurrentProfile currentProfile, @Nonnull List<Education> educations);

    @Nonnull
    List<Certificate> listCertificates(@Nonnull CurrentProfile currentProfile);

    void updateCertificates(@Nonnull CurrentProfile currentProfile, @Nonnull List<Certificate> certificates);

    @Nonnull
    List<Course> listCourses(@Nonnull CurrentProfile currentProfile);

    void updateCourses(@Nonnull CurrentProfile currentProfile, @Nonnull List<Course> courses);
}
