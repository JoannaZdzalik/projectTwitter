package com.sda.twitter.controller;

import com.sda.twitter.model.activity.Comment;
import com.sda.twitter.model.activity.Post;
import com.sda.twitter.model.entity.User;
import com.sda.twitter.service.PostService;
import com.sda.twitter.service.UserSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PostController {

    @Autowired
    private PostService service;
    @Autowired
    private UserSecurityService userSecurityService;

    @GetMapping("/mainpage")
    public String mainPage(Model model) {
        model.addAttribute("username", userSecurityService.getLoggedUserLogin());
        model.addAttribute("allPosts", service.getAllPosts());
        model.addAttribute("postToAdd", new Post());
        model.addAttribute("allComments", service.getAllComments());
        model.addAttribute("commentToAdd",new Comment());
        return "mainpage";
    }

    @PostMapping("/addpost")
    public String addNewPost(@ModelAttribute("postToAdd") Post post) {
        System.out.println("dodajemy nowy post: " + post.getMessage() + " written by: ");
        service.addNewPost(post);
        return "mainpage";
    }

    @PostMapping("/addcomment")
    public String addNewComment(@ModelAttribute("commentToAdd") Comment comment) {
        System.out.println("dodajemy nowy komentarz: " + comment.getMessage() + " written by: " +comment.getUser() + " to post nr: " + comment.getPost().getId());
        service.addNewComment(comment);
        return "mainpage";
    }

//    @PostMapping("/addpost")
//    public String addNewPost(@ModelAttribute("postToAdd") Post post, User user) {
//        System.out.println("dodajemy nowy post: " + post.getMessage() + "written by: " + post.getUser().getName());
//        service.addNewPost(post, user);
//        return "mainpage";
//    }

    @PostMapping("/deletepost")
    public String deletePost(@ModelAttribute("post") Post post) {
        service.deletePost(post);
        return "mainpage";
    }
}
