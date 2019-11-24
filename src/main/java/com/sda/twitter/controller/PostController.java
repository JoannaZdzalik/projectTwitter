package com.sda.twitter.controller;

import com.sda.twitter.model.activity.Comment;
import com.sda.twitter.model.activity.Post;
import com.sda.twitter.model.dto.CommentDto;
import com.sda.twitter.model.entity.User;
import com.sda.twitter.service.PostService;
import com.sda.twitter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @GetMapping("/mainpage")
    public String mainPage(Model model) {
        model.addAttribute("allPosts", postService.getAllPosts());
        model.addAttribute("postToAdd", new Post());
        model.addAttribute("allComments", postService.getAllComments());
        model.addAttribute("commentToAdd",new Comment());
        model.addAttribute("username", userService.getLoggedUserLogin());
        model.addAttribute("user", userService.getLoggedUser());
        return "mainpage";
    }

    @PostMapping("/addpost")
    public String addNewPost(@ModelAttribute("postToAdd") Post post) {
        System.out.println("dodajemy nowy post: " + post.getMessage() + " written by: ");
        postService.addNewPost(post);
        return "redirect:mainpage";
    }

    @PostMapping("/addcomment")
    public String addNewComment(@ModelAttribute("commentToAdd") CommentDto comment) {
        System.out.println("dodajemy nowy komentarz: " + comment.getMessage());
        postService.addNewComment(comment);
        return "redirect:mainpage";
    }


    @PostMapping("/deletepost")
    public String deletePost(@ModelAttribute("post") Post post) {
        postService.deletePost(post);
        return "redirect:mainpage";
    }

    @PostMapping("/deletecomment")
    public String deleteComment(@ModelAttribute("comment") Comment comment) {
        postService.deleteComment(comment);
        return "redirect:mainpage";
    }

//    @PostMapping("/editpost")
//    public String editPost(@ModelAttribute("post") Post post) {
//        postService.editPost(post);
//        return "mainpage";
//    }
//
//    @PostMapping("/editcomment")
//    public String editComment(@ModelAttribute("comment") Comment comment) {
//        postService.editComment(comment);
//        return "mainpage";
//    }
}
