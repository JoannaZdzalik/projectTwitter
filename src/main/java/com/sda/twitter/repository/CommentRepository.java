package com.sda.twitter.repository;

import com.sda.twitter.model.activity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
