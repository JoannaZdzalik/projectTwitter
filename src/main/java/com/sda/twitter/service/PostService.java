package com.sda.twitter.service;

import com.sda.twitter.model.activity.Post;
import com.sda.twitter.model.entity.User;
import com.sda.twitter.repository.PostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ModelMapper mapper;

    public void addNewPost(Post post){
        Post newPost = mapper.map(post, Post.class);
        newPost.setCreationDate(new Date());
        postRepository.save(newPost);
    }

//    public void addNewPost(Post post, User user){
//        Post newPost = mapper.map(post, Post.class);
//        newPost.setCreationDate(new Date());
//        newPost.setUser(user);
//        postRepository.save(newPost);
//    }

    public void deletePost(Post post) {
        postRepository.deleteById(post.getId());
    }

    public List<Post> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        return posts.stream().map(u -> mapper
                .map(u, Post.class))
                .collect(Collectors.toList());

    }
}
