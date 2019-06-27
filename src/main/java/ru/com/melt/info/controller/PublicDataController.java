package ru.com.melt.info.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.com.melt.info.entity.Profile;
import ru.com.melt.info.service.FindProfileService;

@Controller
public class PublicDataController {
	
	@Autowired
	private FindProfileService findProfileService;

	@RequestMapping(value = { "/welcome" }, method=RequestMethod.GET)
	public String listAll(Model model) {
		Page<Profile> profiles = findProfileService.findAll(new PageRequest(0, 10, new Sort("id")));
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
}
