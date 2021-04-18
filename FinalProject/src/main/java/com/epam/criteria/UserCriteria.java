package com.epam.criteria;

import com.epam.entity.UserRole;
import com.epam.entity.UserStatus;

public class UserCriteria extends BaseCriteria{
    private String name = null;
    private String login = null;
    private String email = null;
    private UserRole role = null;
    private UserStatus status = null;

    public UserCriteria(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }
}
