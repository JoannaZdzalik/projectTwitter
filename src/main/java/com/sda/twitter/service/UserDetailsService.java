package com.sda.twitter.service;

//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import com.sda.twitter.model.dto.UserDetailsDto;
import com.sda.twitter.model.entity.UserDetails;
import com.sda.twitter.repository.UserDetailsRepository;
import com.sda.twitter.validation.BindingValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Service
public class UserDetailsService {
//    @Autowired
//    private PasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    @Autowired
    private ModelMapper mapper;

    public void addUserDetails(UserDetailsDto userDetailsDto, BindingResult result) {
        BindingValidator.validate(result);
        System.out.println("Dodajemy użytkownika: " + userDetailsDto.getLogin() + " "
                + userDetailsDto.getPassword() + " "
                + userDetailsDto.getRole());

        validateLoginUser(userDetailsDto);
        System.out.println("Mogę dodać użytkownika");

//        String hash = bCryptPasswordEncoder.encode(userDetailsDto.getPassword());
//        System.out.println("Haslo: " + userDetailsDto.getPassword() + " "
//                + "Hash: " + hash + " Matches: " + bCryptPasswordEncoder.matches("test", hash));

        UserDetails userDetails = mapper.map(userDetailsDto, UserDetails.class);
     //   userDetails.setPassword(hash);
        userDetailsRepository.save(userDetails);

    }

//    private boolean checkAuthorities(UserDetailsDto userDetailsDto) {
//        return SecurityContextHolder
//                .getContext()
//                .getAuthentication()
//                .getAuthorities()
//                .toArray()[0].toString()
//                .equals(userDetailsDto.getRole());
//    }

    private void validateLoginUser(UserDetailsDto userDetailsDto) {
        if (loginExists(userDetailsDto)) {
            throw new RuntimeException("This login already exists!");
        }
//        if (!checkAuthorities(userDetailsDto)) {
//            throw new RuntimeException("Wrong role!");
//        }
    }

    private boolean loginExists(UserDetailsDto loginUserDto) {
        return userDetailsRepository.countByLogin(loginUserDto.getLogin()) > 0;
    }

}
