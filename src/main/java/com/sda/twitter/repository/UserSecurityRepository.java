package com.sda.twitter.repository;

import com.sda.twitter.model.entity.UserSecurity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserSecurityRepository extends JpaRepository<UserSecurity, Long> {
    Long countByLogin(String login);
}
