package com.sda.twitter.model.activity;

import com.sda.twitter.model.entity.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @ManyToOne//(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "userId")
    private User user;

    private String message;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "post")
    private List<Comment> comments;

    private Date creationDate;

}
