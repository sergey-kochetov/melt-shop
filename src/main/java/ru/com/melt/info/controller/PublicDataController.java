package ru.com.melt.info.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.com.melt.info.service.NameService;

import java.util.Locale;

@Controller
public class PublicDataController {

    @Autowired
    private NameService nameService;

    @GetMapping(value = "/{uid}")
    public String getProfile(@PathVariable("uid") String uid, Model model, Locale locale) {
        String newFullName = nameService.convertName(uid);
        model.addAttribute("fullName", newFullName);
        return "profile";
    }

    @GetMapping(value = "/error")
    public String getError() {
        return "error";
    }
}
