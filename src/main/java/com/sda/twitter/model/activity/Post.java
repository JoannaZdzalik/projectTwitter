package com.sda.twitter.model.activity;

import com.sda.twitter.model.entity.User;

import javax.persistence.*;
import java.util.List;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    private String message;

    @OneToMany(mappedBy = "post")
    private List<Comment> comments;
}
