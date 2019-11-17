package com.sda.twitter.controller;

import com.sda.twitter.model.dto.UserSecurityDto;
import com.sda.twitter.service.UserSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class UserSecurityController {

    @Autowired
    private UserSecurityService service;

    @GetMapping("/adduserdetails")
    public ModelAndView getUserDetailsView() {
        return new ModelAndView("adduserdetails", "userDetails", new UserSecurityDto());
    }

    @PostMapping("/adduserdetails")
    public String addNewUserDetails(@ModelAttribute @Valid UserSecurityDto userSecurityDto, BindingResult result) {  //Valid- włączam walidację dla hasła
        service.addUserDetails(userSecurityDto, result);
        return "usersaved";
    }
}

