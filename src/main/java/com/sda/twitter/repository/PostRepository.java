package com.sda.twitter.repository;

import com.sda.twitter.model.activity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findAllByOrderByCreationDateDesc();
}
//  @Query(value="select * from post order by creation_date desc", nativeQuery = true)
//to jest nieużyteczne bo gdybym chciała zmienić baz danych to już nie zadziała