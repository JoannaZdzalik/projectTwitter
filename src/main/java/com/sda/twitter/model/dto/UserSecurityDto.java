package com.sda.twitter.model.dto;

import com.sda.twitter.model.Status;

import javax.validation.constraints.Size;
import java.util.Date;

public class UserSecurityDto extends UserDto {

    @Size(min = 3, max = 20, message = "Login length should be between 3 and 20 characters.")
    protected String login;
    @Size(min = 4, max = 20, message = "Password length should be between 4 and 20 characters.")
    protected String password;
    protected String role;
    protected Status status;
    protected Date blockedDate;

    public Date getBlockedDate() {
        return blockedDate;
    }

    public void setBlockedDate(Date blockedDate) {
        this.blockedDate = blockedDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

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
