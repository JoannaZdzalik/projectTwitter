package com.sda.twitter.service;

import com.sda.twitter.model.activity.Comment;
import com.sda.twitter.model.activity.Post;
import com.sda.twitter.model.dto.CommentDto;
import com.sda.twitter.model.dto.PostDto;
import com.sda.twitter.model.entity.User;
import com.sda.twitter.model.entity.UserSecurity;
import com.sda.twitter.repository.CommentRepository;
import com.sda.twitter.repository.PostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private UserService userService;

    public void addNewPost(Post post) {
        Post newPost = mapper.map(post, Post.class);
        newPost.setCreationDate(new Date());
        newPost.setUser(userService.getLoggedUser());
        postRepository.save(newPost);
    }

    public void addNewComment(CommentDto comment) {
     //   Post post = postRepository.findById(comment.getPostId()).get();// orElseThrow
        Post post = postRepository.findById(comment.getPostId()).orElseThrow(()->new RuntimeException("Unable to find a post"));
        Comment newComment = mapper.map(comment, Comment.class);
        newComment.setUser(userService.getLoggedUser());
        newComment.setPost(post);
        commentRepository.save(newComment);
    }

    public void deletePost(Post post) {
        postRepository.deleteById(post.getId());
    }

    public void deleteComment(Comment comment) {
        commentRepository.deleteById(comment.getId());
    }

    public List<Post> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        posts.stream().map(u -> mapper
                .map(u, PostDto.class))
                .collect(Collectors.toList());
        Collections.reverse(posts);
        return posts;

    }

    public List<CommentDto> getAllComments() {
        List<Comment> comments = commentRepository.findAll();
        return comments.stream().map(u -> mapper
                .map(u, CommentDto.class))
                .collect(Collectors.toList());
    }
}
