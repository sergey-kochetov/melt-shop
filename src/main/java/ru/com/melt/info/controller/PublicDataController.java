package ru.com.melt.info.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.com.melt.info.entity.Profile;
import ru.com.melt.info.model.CurrentProfile;
import ru.com.melt.info.service.FindProfileService;
import ru.com.melt.info.util.SecurityUtil;

import javax.servlet.http.HttpSession;

@Controller
public class PublicDataController {
	
	private static final int MAX_SIZE_DOWNLOAD = 5;
	@Autowired
	private FindProfileService findProfileService;

	@RequestMapping(value = { "/welcome" }, method=RequestMethod.GET)
	public String listAll(Model model) {
		Page<Profile> profiles = findProfileService.findAll(
		        new PageRequest(0, MAX_SIZE_DOWNLOAD, new Sort("id")));
		model.addAttribute("profiles", profiles.getContent());
		model.addAttribute("page", profiles);
		return "welcome";
	}

	@RequestMapping(value="/{uid}", method=RequestMethod.GET)
	public String getProfile(@PathVariable("uid") String uid, Model model){
		Profile profile = findProfileService.findByUid(uid);
		if(profile == null) {
			return "profile";//"profile-not-found";
		}
		model.addAttribute("profile", profile);
		model.addAttribute("showEdit", true);
		return "profile";
	}
	
	@RequestMapping(value="/error", method=RequestMethod.GET)
	public String getError(){
		return "error";
	}

	@RequestMapping(value = "/fragment/more", method = RequestMethod.GET)
    public String moreProfiles(Model model,
                               @PageableDefault(size = MAX_SIZE_DOWNLOAD) @SortDefault(sort = "id")Pageable pageable) {
	    Page<Profile> profiles = findProfileService.findAll(pageable);
	    model.addAttribute("profiles", profiles.getContent());
	    return "fragment/profile-items";
    }

	@RequestMapping(value = "/sign-in")
	public String signIn() {
		CurrentProfile currentProfile = SecurityUtil.getCurrentProfile();
		if(currentProfile != null) {
			return "redirect:/" + currentProfile.getUsername();
		}
		else{
			return "sign-in";
		}
	}

	@RequestMapping(value = "/sign-in-failed")
	public String signInFailed(HttpSession session) {
		if (session.getAttribute("SPRING_SECURITY_LAST_EXCEPTION") == null) {
			return "redirect:/sign-in";
		}
		return "sign-in";
	}
}
