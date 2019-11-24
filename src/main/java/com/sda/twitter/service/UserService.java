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

import java.util.Calendar;
import java.util.Date;
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
        validateUser(userSecurityDto);
        UserSecurity userSec = mapper.map(userSecurityDto, UserSecurity.class);
        userSec.setPassword(bCryptPasswordEncoder.encode(userSec.getPassword()));
        userSec.setRole("ROLE_USER");
        userSec.setStatus(Status.ACTIVE);
        userSecurityRepository.save(userSec);
    }

    public List<UserSecurityDto> getAllUsers() {
        List<UserSecurity> users = userSecurityRepository.findAll();
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

    private boolean checkAuthorities(UserSecurityDto userSecurityDto) {
        return SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getAuthorities()
                .toArray()[0].toString()
                .equals(userSecurityDto.getRole());
    }

    private void validateUser(UserSecurityDto userSecurityDto) {
        if (loginExists(userSecurityDto)) {
            throw new RuntimeException("This login already exists!");
        }
        if (!checkAuthorities(userSecurityDto)) {
            throw new RuntimeException("Wrong role!");
        }
    }

    private boolean loginExists(UserSecurityDto userSecurityDto) {
        return userSecurityRepository.countByLogin(userSecurityDto.getLogin()) > 0;
    }

    public void blockUser(UserSecurityDto userSecurityDto) {
        UserSecurity userSec = userSecurityRepository.findById(userSecurityDto.getId()).get(); //orElseThrow
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) + 5);
        Date blockedDate = cal.getTime();
        userSec.setBlockedDate(blockedDate);
        userSec.setStatus(Status.BANNED);
        userSecurityRepository.save(userSec);
    }

    public boolean checkIsUserBanned() {
        UserSecurity user = (UserSecurity)getLoggedUser();
        if(user.getBlockedDate() == null) {
            return false;
        }
        return new Date().before(user.getBlockedDate());
    }
    public void checkIsUserBanned(UserSecurityDto userSecurityDto) {
        UserSecurity userSec = mapper.map(userSecurityDto, UserSecurity.class);
        Date currentDate = new Date();
        if (currentDate.compareTo(userSec.getBlockedDate()) >0) {
            userSec.setBlockedDate(null);
            userSec.setStatus(Status.ACTIVE);
        }
        userSecurityRepository.save(userSec);
    }

// if currentdate < blocked date powinno dąć się zalogować
}
