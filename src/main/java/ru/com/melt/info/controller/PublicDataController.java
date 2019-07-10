package ru.com.melt.info.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.com.melt.info.annotation.constraints.FieldMatch;
import ru.com.melt.info.component.FormErrorConverter;
import ru.com.melt.info.entity.Profile;
import ru.com.melt.info.form.SignUpForm;
import ru.com.melt.info.model.CurrentProfile;
import ru.com.melt.info.service.EditProfileService;
import ru.com.melt.info.service.FindProfileService;
import ru.com.melt.info.util.SecurityUtil;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import static ru.com.melt.info.Constants.UI.MAX_PROFILES_PER_PAGE;


@Controller
public class PublicDataController {

    @Autowired
    private FindProfileService findProfileService;

    @Autowired
    private EditProfileService editProfileService;

    @Autowired
    private FormErrorConverter formErrorConverter;

    @RequestMapping(value = "/{uid}")
    public String profile(@PathVariable String uid, Model model) {
        Profile profile = findProfileService.findByUid(uid);
        if (profile == null) {
            return "profile-not-found";
        } else if (!profile.isCompleted()) {
            CurrentProfile currentProfile = SecurityUtil.getCurrentProfile();
            if (currentProfile == null || !currentProfile.getId().equals(profile.getId())) {
                return "profile-not-found";
            } else {
                return "redirect:/edit";
            }
        } else {
            model.addAttribute("profile", profile);
            return "profile";
        }
    }

    @RequestMapping(value = {"/welcome"})
    public String listAll(Model model) {
        Page<Profile> profiles = findProfileService.findAll(new PageRequest(0, MAX_PROFILES_PER_PAGE, new Sort("id")));
        model.addAttribute("profiles", profiles.getContent());
        model.addAttribute("page", profiles);
        return "welcome";
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String searchProfiles(@RequestParam(value = "query", required = false) String query, Model model,
                                 @PageableDefault(size = MAX_PROFILES_PER_PAGE) @SortDefault(sort = "id") Pageable pageable) throws UnsupportedEncodingException {
        if (StringUtils.isBlank(query)) {
            return "redirect:/welcome";
        } else {
            Page<Profile> profiles = findProfileService.findBySearchQuery(query, pageable);
            model.addAttribute("profiles", profiles.getContent());
            model.addAttribute("page", profiles);
            model.addAttribute("query", URLDecoder.decode(query, "UTF-8"));
            return "search-results";
        }
    }

    @RequestMapping(value = "/fragment/more", method = RequestMethod.GET)
    public String moreProfiles(Model model,
                               @RequestParam(value = "query", required = false) String query,
                               @PageableDefault(size = MAX_PROFILES_PER_PAGE) @SortDefault(sort = "id") Pageable pageable) throws UnsupportedEncodingException {
        Page<Profile> profiles = null;
        if (StringUtils.isNotBlank(query)) {
            profiles = findProfileService.findBySearchQuery(query, pageable);
        } else {
            profiles = findProfileService.findAll(pageable);
        }
        model.addAttribute("profiles", profiles.getContent());
        return "fragment/profile-items";
    }

    @RequestMapping(value = "/sign-in")
    public String signIn() {
        return "sign-in";
    }

    @RequestMapping(value = "/sign-up", method = RequestMethod.GET)
    public String signUp(Model model) {
        model.addAttribute("profileForm", new SignUpForm());
        return "sign-up";
    }

    @RequestMapping(value = "/sign-up", method = RequestMethod.POST)
    public String signUp(@Valid @ModelAttribute("profileForm") SignUpForm signUpForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            formErrorConverter.convertFormErrorToFieldError(FieldMatch.class, signUpForm, bindingResult);
            return "sign-up";
        } else {
            Profile profile = editProfileService.createNewProfile(signUpForm);
            SecurityUtil.authentificateWithRememberMe(profile);
            return "redirect:/sign-up/success";
        }
    }

    @RequestMapping(value = "/sign-up/success", method = RequestMethod.GET)
    public String signUpSuccess() {
        return "sign-up-success";
    }

    @RequestMapping(value = "/error")
    public String error() {
        return "error";
    }

    @RequestMapping(value = "/sign-in-failed")
    public String signInFailed(HttpSession session) {
        if (session.getAttribute("SPRING_SECURITY_LAST_EXCEPTION") == null) {
            return "redirect:/sign-in";
        } else {
            return "sign-in";
        }
    }

    @RequestMapping(value = "/restore", method = RequestMethod.GET)
    public String getRestoreAccess() {
        return "restore";
    }

    @RequestMapping(value = "/restore/success", method = RequestMethod.GET)
    public String getRestoreSuccess() {
        return "restore-success";
    }

    @RequestMapping(value = "/restore", method = RequestMethod.POST)
    public String processRestoreAccess(@RequestParam("uid") String anyUnigueId) {
        findProfileService.restoreAccess(anyUnigueId);
        return "redirect:/restore/success";
    }

    @RequestMapping(value = "/restore/{token}", method = RequestMethod.GET)
    public String restoreAccess(@PathVariable("token") String token) {
        Profile profile = findProfileService.findByRestoreToken(token);
        SecurityUtil.authentificate(profile);
        return "redirect:/edit/password";
    }
}
