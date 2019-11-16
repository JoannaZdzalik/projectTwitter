package com.sda.twitter.controller;

import com.sda.twitter.model.dto.UserDto;
import com.sda.twitter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Service;

@Controller
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("/adduserform")
    public ModelAndView createNewUser() {
        return new ModelAndView("adduserform", "userToInsert", new UserDto());
    }

    @PostMapping("/adduser")
    public String addNewUser(@ModelAttribute UserDto user) {
        System.out.println("Dodajemy użytkownika: " + user.getName() + " " + user.getSurname() + " " +user.getAge());
        service.addUser(user);
        return "usersaved";
    }

}

