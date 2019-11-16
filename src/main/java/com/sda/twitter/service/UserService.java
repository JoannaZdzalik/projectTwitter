package com.sda.twitter.service;

import com.sda.twitter.model.dto.UserDto;
import com.sda.twitter.model.entity.User;
import com.sda.twitter.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private UserRepository userRepository;

    public void addUser(UserDto userDto) {
        User user = mapper.map(userDto, User.class);
        System.out.println("Zmapowany user: "
                + user.getId() + " "
                + user.getName() + " "
                + user.getSurname() + " "
                + user.getAge());
        userRepository.save(user);
    }
}
