package ru.com.melt.info.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.com.melt.info.repository.storage.SkillCategoryRepository;
import ru.com.melt.info.repository.storage.SkillCategoryRepository2;

@Controller
public class EditProfileController {

    @Autowired
    private SkillCategoryRepository2 repository;

    @GetMapping(value="/edit")
    public String getEditProfile(){
        return "edit";
    }

    @GetMapping(value="/edit/skills")
    public String getEditSkills(Model model){
        model.addAttribute("skillCategories", repository.findAll(new Sort("id")));
        return "edit-skills";
    }
}