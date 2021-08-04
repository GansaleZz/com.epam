package com.epam.criteria.impl;

import com.epam.criteria.BaseCriteria;
import com.epam.entity.AccountStatus;
import com.epam.entity.User;
import com.epam.entity.UserRole;
import com.epam.entity.UserStatus;

public class UserCriteria extends BaseCriteria<User> {
    private String name = null;
    private String login = null;
    private String email = null;
    private String password = null;
    private UserRole role = null;
    private UserStatus status = null;
    private AccountStatus accountStatus = null;

    /**
     * Realisation of builder pattern for {@link User}
     */
    public static class Builder extends BaseBuilder<User>{

        public Builder newBuilder(){
            actualClass = new User();
            return this;
        }

        public Builder withId(int id){
            actualClass.setId(id);
            return this;
        }

        public Builder withLogin(String login){
            actualClass.setLogin(login);
            return this;
        }

        public Builder withPassword(String password){
            actualClass.setPassword(password);
            return this;
        }

        public Builder withEmail(String email){
            actualClass.setEmail(email);
            return this;
        }

        public Builder withUserStatus(UserStatus userStatus){
            actualClass.setStatus(userStatus);
            return this;
        }

        public Builder withUserRole(UserRole userRole){
            actualClass.setUserRole(userRole);
            return this;
        }

        public Builder withName(String name){
            actualClass.setName(name);
            return this;
        }

        public Builder withBalance(double balance){
            actualClass.setBalance(balance);
            return this;
        }

        public Builder withAccountStatus(AccountStatus accountStatus){
            actualClass.setAccountStatus(accountStatus);
            return this;
        }

        public Builder withVerifyCode(int verifyCode){
            actualClass.setVerifyCode(verifyCode);
            return this;
        }

        @Override
        protected User getActual() {
            return actualClass;
        }
    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
