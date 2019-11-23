package com.sda.twitter.model.dto;

import com.sda.twitter.model.activity.Comment;
import com.sda.twitter.model.entity.User;

import java.util.Date;
import java.util.List;

public class PostDto {

    private Long id;
    private User user;

    private String message;

    private List<Comment> comments;

    private Date creationDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
