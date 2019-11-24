package com.sda.twitter.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

@Configuration
public class SpringConfig {

    @Bean
    public ModelMapper mapper() {
        return new ModelMapper();
    }

    @Bean
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }
}
