package com.sda.twitter.service;

import com.sda.twitter.model.Status;
import com.sda.twitter.model.dto.UserSecurityDto;
import com.sda.twitter.model.entity.User;
import com.sda.twitter.model.entity.UserSecurity;
import com.sda.twitter.repository.UserSecurityRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private UserSecurityRepository userSecurityRepository;

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    public void addUser(UserSecurityDto userSecurityDto) {
        UserSecurity userSec = mapper.map(userSecurityDto, UserSecurity.class);
        userSec.setPassword(bCryptPasswordEncoder.encode(userSec.getPassword()));
        userSec.setRole("ROLE_USER");
        userSec.setStatus(Status.ACTIVE);
        userSecurityRepository.save(userSec);
    }

    public List<UserSecurityDto> getAllUsers() {
        List<UserSecurity> users = userSecurityRepository.findAll();
        for (UserSecurity u : users) {
            System.out.println("User: "
                    + u.getId() + " "
                    + u.getName() + " "
                    + u.getSurname() + " "
                    + u.getLogin());
        }
        return users.stream().map(u -> mapper
                .map(u, UserSecurityDto.class))
                .collect(Collectors.toList());
    }

    public String getLoggedUserLogin() {
        return ((UserDetails) SecurityContextHolder
                .getContext()
                .getAuthentication().getPrincipal()).getUsername(); //username to mój login
    }

    public User getLoggedUser() {
        return userSecurityRepository.findByLogin(getLoggedUserLogin()).get(); //dodać or else throw
    }

}
