package com.sda.twitter.controller;

import com.sda.twitter.model.dto.UserSecurityDto;
import com.sda.twitter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/users")
    public String userView(Model model) {
        model.addAttribute("allUsers", userService.getAllUsers());
        return "users";
    }

    @GetMapping("/userverification")
    public String verifyUserAccount() {
       if(userService.checkIsUserBanned()) {
           return "userverificationfailed";
       }
       return "redirect:mainpage";
    }

    @GetMapping("/userverificationfailed")
    public String userIsBlocked() {
        return "userverificationfailed";
    }

    @GetMapping("/adduserform")
    public ModelAndView createNewUser() {
        return new ModelAndView("adduserform", "userToInsert", new UserSecurityDto());
    }

    @PostMapping("/adduser")
    public String addNewUser(@ModelAttribute UserSecurityDto userSecurityDto) {
        userService.addUser(userSecurityDto);
        return "usersaved";
    }

    @PostMapping("/blockuser")
    public String banUser(@ModelAttribute UserSecurityDto userSecurityDto){
        userService.blockUser(userSecurityDto);
        return "redirect:users";
    }

}

