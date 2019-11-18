package com.sda.twitter.controller;

import com.sda.twitter.model.dto.UserDto;
import com.sda.twitter.model.dto.UserSecurityDto;
import com.sda.twitter.model.entity.UserSecurity;
import com.sda.twitter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    @Autowired
    private UserService service;

    @RequestMapping("/users")
    public String userView(Model model) {
        model.addAttribute("allUsers", service.getAllUsers());
        return "users";
    }

    @GetMapping("/adduserform")
    public ModelAndView createNewUser() {
        return new ModelAndView("adduserform", "userToInsert", new UserSecurityDto());
    }

    @PostMapping("/adduser")
    public String addNewUser(@ModelAttribute UserSecurityDto userSecurityDto) {
        System.out.println("Dodajemy użytkownika: " + userSecurityDto.getName() + " " + userSecurityDto.getSurname() + " " +userSecurityDto.getAge());
        service.addUser(userSecurityDto);
        return "usersaved";
    }

}

