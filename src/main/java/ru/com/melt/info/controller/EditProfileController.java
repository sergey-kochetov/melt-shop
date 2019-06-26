package ru.com.melt.info.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.com.melt.info.entity.Skill;
import ru.com.melt.info.entity.SkillCategory;
import ru.com.melt.info.form.SkillForm;
import ru.com.melt.info.repository.storage.ProfileRepository;
import ru.com.melt.info.repository.storage.SkillCategoryRepository;

import java.util.List;

@Controller
public class EditProfileController {

//    @Autowired
//    private SkillCategoryRepository skillCategoryRepository;
//    @Autowired
//    private ProfileRepository profileRepository;

    @GetMapping(value="/edit")
    public String getEditProfile(){
        return "edit";
    }

    @GetMapping(value="/edit/skills")
    public String getEditTechSkills(Model model){
        //List<Skill> skills = profileRepository.findById(1L).get().getSkills();
        //model.addAttribute("skillForm", skills);
        return gotoSkillsJSP(model);
    }

    @PostMapping(value = "/edit/skills")
    public String saveEditTechSkills(@ModelAttribute("skillForm") SkillForm form,
                                     BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return gotoSkillsJSP(model);
        }

        return "redirectL:/asdas-dasd";
    }

    private String gotoSkillsJSP(Model model) {
        //List<SkillCategory> l = skillCategoryRepository.findAll(new Sort("id"));
        model.addAttribute("skillCategories", "");
        return "edit/skills";
    }
}