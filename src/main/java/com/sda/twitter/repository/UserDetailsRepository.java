package com.sda.twitter.repository;

import com.sda.twitter.model.entity.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailsRepository extends JpaRepository<UserDetails, Long> {
    Long countByLogin(String login);
}
