package ru.com.melt.info.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.com.melt.info.entity.Contacts;
import ru.com.melt.info.form.CertificateForm;
import ru.com.melt.info.form.PracticForm;
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
        return "redirect:/edit/contacts";
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

    @RequestMapping(value = "/edit/contacts", method = RequestMethod.GET)
    public String getEditContacts(Model model) {
        model.addAttribute("contactsForm", editProfileService
                .findContactsById(SecurityUtil.getCurrentIdProfile()));
        return "edit/contacts";
    }

    @RequestMapping(value = "/edit/contacts", method = RequestMethod.POST)
    public String saveEditContacts(@Valid @ModelAttribute("contactsForm") Contacts contacts, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "edit/contacts";
        }
        editProfileService.updateContacts(SecurityUtil.getCurrentIdProfile(), contacts);
        return "redirect:/edit/contacts";
    }

    @RequestMapping(value = "/edit/practics", method = RequestMethod.GET)
    public String getEditPractics(Model model) {
        model.addAttribute("contactsForm", editProfileService
                .findPracticsById(SecurityUtil.getCurrentIdProfile()));
        return "edit/practics";
    }

    @RequestMapping(value = "/edit/practics", method = RequestMethod.POST)
    public String saveEditPractics(@Valid @ModelAttribute("contactsForm") PracticForm practicForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "edit/practics";
        }
        editProfileService.updatePractics(SecurityUtil.getCurrentIdProfile(), practicForm.getItems());
        return "redirect:/welcome";
    }

    @RequestMapping(value = "/edit/certificates", method = RequestMethod.GET)
    public String getEditCertificates(Model model) {
        model.addAttribute("contactsForm", editProfileService
                .findCertificatesById(SecurityUtil.getCurrentIdProfile()));
        return "edit/certificates";
    }

    @RequestMapping(value = "/edit/certificates", method = RequestMethod.POST)
    public String saveEditCertificates(@Valid @ModelAttribute("contactsForm") CertificateForm certificateForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "edit/certificates";
        }
        editProfileService.updateCertificates(SecurityUtil.getCurrentIdProfile(), certificateForm.getItems());
        return "redirect:/welcome";
    }

    private String gotoSkillsJSP(Model model) {
        model.addAttribute("skillCategories", editProfileService.listSkillCategories());
        return "edit/skills";
    }
}
