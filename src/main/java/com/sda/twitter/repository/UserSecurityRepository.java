package com.sda.twitter.repository;

import com.sda.twitter.model.entity.User;
import com.sda.twitter.model.entity.UserSecurity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserSecurityRepository extends JpaRepository<UserSecurity, Long> {
    Long countByLogin(String login);

    Optional<User> findByLogin(String name);
}
