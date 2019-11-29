package com.sda.twitter.repository;

import com.sda.twitter.model.activity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {

  //  Optional<Comment> findById
}
