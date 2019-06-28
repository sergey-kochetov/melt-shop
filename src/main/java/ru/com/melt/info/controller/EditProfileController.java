package ru.com.melt.info.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.com.melt.info.entity.Contacts;
import ru.com.melt.info.form.*;
import ru.com.melt.info.service.EditProfileService;
import ru.com.melt.info.util.SecurityUtil;

import javax.validation.Valid;
import java.util.List;

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
        model.addAttribute("practicsForm", editProfileService
                .findPracticsById(SecurityUtil.getCurrentIdProfile()));
        return "edit/practics";
    }

    @RequestMapping(value = "/edit/practics", method = RequestMethod.POST)
    public String saveEditPractics(@Valid @ModelAttribute("practicsForm") PracticForm practicForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "edit/practics";
        }
        editProfileService.updatePractics(SecurityUtil.getCurrentIdProfile(), practicForm.getItems());
        return "redirect:/welcome";
    }

    @RequestMapping(value = "/edit/certificates", method = RequestMethod.GET)
    public String getEditCertificates(Model model) {
        model.addAttribute("certificatesForm", editProfileService
                .findCertificatesById(SecurityUtil.getCurrentIdProfile()));
        return "edit/certificates";
    }

    @RequestMapping(value = "/edit/certificates", method = RequestMethod.POST)
    public String saveEditCertificates(@Valid @ModelAttribute("certificatesForm") CertificateForm certificateForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "edit/certificates";
        }
        editProfileService.updateCertificates(SecurityUtil.getCurrentIdProfile(), certificateForm.getItems());
        return "redirect:/welcome";
    }

    @RequestMapping(value = "/edit/courses", method = RequestMethod.GET)
    public String getEditCourses(Model model) {
        model.addAttribute("coursesForm", editProfileService
                .findCoursesById(SecurityUtil.getCurrentIdProfile()));
        return "edit/courses";
    }

    @RequestMapping(value = "/edit/courses", method = RequestMethod.POST)
    public String saveEditCourses(@Valid @ModelAttribute("coursesForm") CourseForm courseForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "edit/courses";
        }
        editProfileService.updateCourses(SecurityUtil.getCurrentIdProfile(), courseForm.getItems());
        return "redirect:/welcome";
    }

    @RequestMapping(value = "/edit/education", method = RequestMethod.GET)
    public String getEditEducation(Model model) {
        model.addAttribute("educationForm", editProfileService
                .findEducationById(SecurityUtil.getCurrentIdProfile()));
        return "edit/education";
    }

    @RequestMapping(value = "/edit/education", method = RequestMethod.POST)
    public String saveEditEducation(@Valid @ModelAttribute("educationForm") EducationForm educationForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "edit/education";
        }
        editProfileService.updateEducation(SecurityUtil.getCurrentIdProfile(), educationForm.getItems());
        return "redirect:/welcome";
    }

    @RequestMapping(value = "/edit/languages", method = RequestMethod.GET)
    public String getEditLanguage(Model model) {
        model.addAttribute("languageForm", editProfileService
                .findLanguageById(SecurityUtil.getCurrentIdProfile()));
        return "edit/education";
    }

    @RequestMapping(value = "/edit/languages", method = RequestMethod.POST)
    public String saveEditLanguage(@Valid @ModelAttribute("languageForm") LanguageForm languageForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "edit/languages";
        }
        editProfileService.updateLanguage(SecurityUtil.getCurrentIdProfile(), languageForm.getItems());
        return "redirect:/welcome";
    }

    @RequestMapping(value = "/edit/hobbies", method = RequestMethod.GET)
    public String getEditHobbies(Model model) {
        model.addAttribute("hobbies", editProfileService
                .findHobbiesWithProfileSelected(SecurityUtil.getCurrentIdProfile()));
        return "edit/education";
    }

    @RequestMapping(value = "/edit/hobbies", method = RequestMethod.POST)
    public String saveEditHobbies(@Valid @ModelAttribute("hobbies") List<String> hobbies, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "edit/languages";
        }
        editProfileService.updateHobbies(SecurityUtil.getCurrentIdProfile(), hobbies);
        return "redirect:/welcome";
    }

    @RequestMapping(value = "/edit/info", method = RequestMethod.GET)
    public String getEditProfileInfo(Model model) {
        model.addAttribute("infoForm", new InfoForm(editProfileService.
                findProfileById(SecurityUtil.getCurrentIdProfile())));
        return "edit/info";
    }

    @RequestMapping(value = "/edit/info", method = RequestMethod.POST)
    public String saveEditInfo(@Valid @ModelAttribute("infoForm") InfoForm infoForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "edit/info";
        }
        editProfileService.updateInfo(SecurityUtil.getCurrentIdProfile(), infoForm.getInfo());
        return "redirect:/welcome";
    }


    private String gotoSkillsJSP(Model model) {
        model.addAttribute("skillCategories", editProfileService.listSkillCategories());
        return "edit/skills";
    }
}
