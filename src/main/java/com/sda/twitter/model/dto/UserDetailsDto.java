package com.sda.twitter.model.dto;

import javax.validation.constraints.Size;

public class UserDetailsDto{

    @Size(min = 3, max = 20, message = "Login lenght should be between 3 and 20 characters.")
    protected String login;
    @Size(min = 4, max = 20, message = "Password lenght should be between 4 and 20 characters.")
    protected String password;
    protected String role;


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
