package com.sda.twitter.repository;

import com.sda.twitter.model.activity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
