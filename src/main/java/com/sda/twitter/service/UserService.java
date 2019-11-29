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
        UserSecurity userSec = mapper.map(userSecurityDto, UserSecurity.class);
        userSec.setPassword(bCryptPasswordEncoder.encode(userSec.getPassword()));
        userSec.setRole("ROLE_USER");
        userSec.setStatus(Status.ACTIVE);
        validateUser(userSecurityDto);
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
                .getAuthentication().getPrincipal()).getUsername();
    }

    public User getLoggedUser() {
        return userSecurityRepository.findByLogin(getLoggedUserLogin()).orElseThrow(()-> new RuntimeException("User not found"));

    }

    public UserSecurity getLoggedUserSecurity() {
        return userSecurityRepository.findDistinctByLogin(getLoggedUserLogin()).orElseThrow(()-> new RuntimeException("User not found"));
    }

    private void validateUser(UserSecurityDto userSecurityDto) {
        if (loginExists(userSecurityDto)) {
            throw new RuntimeException("This login already exists!");
        }
    }

    private boolean loginExists(UserSecurityDto userSecurityDto) {
        return userSecurityRepository.countByLogin(userSecurityDto.getLogin()) > 0;
    }

    public void blockUser(UserSecurityDto userSecurityDto) {
        UserSecurity userSec = userSecurityRepository.findById(userSecurityDto.getId()).orElseThrow(()-> new RuntimeException("User not found")); //or else throw
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
        } else if(new Date().after(user.getBlockedDate()) ){ //&& user.getStatus() ==Status.BANNED jeśli chcę zostawić sobie info że był zablokowany
            user.setStatus(Status.ACTIVE);
            user.setBlockedDate(null);
            userSecurityRepository.save(user);
            return false;
        }
        return new Date().before(user.getBlockedDate());
    }

}
