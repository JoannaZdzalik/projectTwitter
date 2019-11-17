package com.sda.twitter.controller;

import com.sda.twitter.service.UserSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @Autowired
    private UserSecurityService userSecurityService;

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/mainpage")
    public String mainPage(Model model) {
        model.addAttribute("username", userSecurityService.getLoggedUserLogin());
        return "mainpage";
    }
}