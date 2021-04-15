package com.epam.criteria;

import com.epam.entity.UserRole;
import com.epam.entity.UserStatus;

public class UserCriteria extends BaseCriteria{
    private String login = null;
    private String password = null;
    private String email = null;
    private UserStatus status = null;
    private UserRole user_role = null;

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
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public UserRole getUser_role() {
        return user_role;
    }

    public void setUser_role(UserRole user_role) {
        this.user_role = user_role;
    }
}
