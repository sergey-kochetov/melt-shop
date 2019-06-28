package ru.com.melt.info.service;

import ru.com.melt.info.entity.Contacts;
import ru.com.melt.info.entity.Profile;
import ru.com.melt.info.entity.Skill;
import ru.com.melt.info.entity.SkillCategory;
import ru.com.melt.info.form.SignUpForm;

import java.util.List;

public interface EditProfileService {

    Profile createNewProfile(SignUpForm signUpForm);

    List<Skill> listSkills(long idProfile);

    List<SkillCategory> listSkillCategories();

    void updateSkill(long idProfile, List<Skill> skills);

    Contacts findContactsById(long idProfile);

    void updateContacts(long idProfile, Contacts contacts);
}
