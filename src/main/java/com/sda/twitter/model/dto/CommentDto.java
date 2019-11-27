package com.sda.twitter.model.dto;

import com.sda.twitter.model.activity.Post;
import com.sda.twitter.model.entity.User;


public class CommentDto {
    private Long id;
    private String message;
    private User user;
   // private Post post;
    private Long postId;

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
