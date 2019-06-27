package ru.com.melt.info.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ru.com.melt.info.entity.Profile;
import ru.com.melt.info.repository.storage.ProfileRepository;
import ru.com.melt.info.service.FindProfileService;

@Controller
public class PublicDataController {
	
	@Autowired
	private FindProfileService findProfileService;

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
