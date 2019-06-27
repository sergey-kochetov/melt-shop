package ru.com.melt.info.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.com.melt.info.form.SkillForm;
import ru.com.melt.info.service.EditProfileService;
import ru.com.melt.info.util.SecurityUtil;

import javax.validation.Valid;

@Controller
public class EditProfileController {

    @Autowired
    private EditProfileService editProfileService;

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String getEditProfile() {
        return "edit";
    }

    @RequestMapping(value = "/edit/skills", method = RequestMethod.GET)
    public String getEditTechSkills(Model model) {
        model.addAttribute("skillForm", new SkillForm(editProfileService
                .listSkills(SecurityUtil.getCurrentIdProfile())));
        return gotoSkillsJSP(model);
    }

    @RequestMapping(value = "/edit/skills", method = RequestMethod.POST)
    public String saveEditTechSkills(@Valid @ModelAttribute("skillForm") SkillForm form, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return gotoSkillsJSP(model);
        }
        editProfileService.updateSkill(SecurityUtil.getCurrentIdProfile(), form.getItems());
        return "redirect:/welcome";
    }

    private String gotoSkillsJSP(Model model) {
        model.addAttribute("skillCategories", editProfileService.listSkillCategories());
        return "edit/skills";
    }
}
