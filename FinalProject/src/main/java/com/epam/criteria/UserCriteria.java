package com.epam.criteria;

import com.epam.entity.UserRole;
import com.epam.entity.UserStatus;

public class UserCriteria extends BaseCriteria{
    private String login = null;
    private String password = null;
    private String email = null;
    private UserStatus userStatus = null;
    private UserRole userRole = null;

    public UserCriteria(){}

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserStatus getStatus() {
        return userStatus;
    }

    public void setStatus(UserStatus status) {
        this.userStatus = status;
    }

    public UserRole getUser_role() {
        return userRole;
    }

    public void setUser_role(UserRole user_role) {
        this.userRole = user_role;
    }
}
