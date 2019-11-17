package com.sda.twitter.service;

import org.springframework.security.core.context.SecurityContextHolder;
import com.sda.twitter.model.dto.UserSecurityDto;
import com.sda.twitter.model.entity.UserSecurity;
import com.sda.twitter.repository.UserSecurityRepository;
import com.sda.twitter.validation.BindingValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Service
public class UserSecurityService {

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserSecurityRepository userDetailsRepository;

    @Autowired
    private ModelMapper mapper;

    public void addUserDetails(UserSecurityDto userSecurityDto, BindingResult result) {
        BindingValidator.validate(result);
        System.out.println("Dodajemy użytkownika: " + userSecurityDto.getLogin() + " "
                + userSecurityDto.getPassword() + " "
                + userSecurityDto.getRole());

        validateLoginUser(userSecurityDto);
        System.out.println("Mogę dodać użytkownika");

        String hash = bCryptPasswordEncoder.encode(userSecurityDto.getPassword());
        System.out.println("Haslo: " + userSecurityDto.getPassword() + " "
                + "Hash: " + hash + " Matches: " + bCryptPasswordEncoder.matches("test", hash));

        UserSecurity userSecurity = mapper.map(userSecurityDto, UserSecurity.class);
        userSecurity.setPassword(hash);
        userDetailsRepository.save(userSecurity);

    }

    public String getLoggedUserLogin() {
        return ((UserDetails)SecurityContextHolder
                .getContext()
                .getAuthentication().getPrincipal()).getUsername();
    }

    private boolean checkAuthorities(UserSecurityDto userSecurityDto) {
        return SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getAuthorities()
                .toArray()[0].toString()
                .equals(userSecurityDto.getRole());
    }

    private void validateLoginUser(UserSecurityDto userSecurityDto) {
        if (loginExists(userSecurityDto)) {
            throw new RuntimeException("This login already exists!");
        }
        if (!checkAuthorities(userSecurityDto)) {
            throw new RuntimeException("Wrong role!");
        }
    }

    private boolean loginExists(UserSecurityDto userSecurityDto) {
        return userDetailsRepository.countByLogin(userSecurityDto.getLogin()) > 0;
    }

}
