package com.sda.twitter.model.activity;

import com.sda.twitter.model.entity.User;

import javax.persistence.*;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;

    @ManyToOne
    @JoinColumn(name = "commentUserId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "postId")
    private Post post;

}
