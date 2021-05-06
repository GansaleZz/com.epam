package com.epam.entity;

import java.util.Objects;

public class User extends BaseEntity {
    private String login;
    private String password;
    private String email;
    private UserStatus status;
    private UserRole userRole;
    private String name;

    public User(){}

    public User(String login, String password, String email,String name,int id , UserStatus status, UserRole role){
        super(id);
        this.login = login;
        this.email = email;
        this.password = password;
        this.userRole = role;
        this.status = status;
        this.name = name;
    }

    public User(String login, String password, String email,String name, UserStatus status, UserRole role){
        this.login = login;
        this.email = email;
        this.password = password;
        this.userRole = role;
        this.status = status;
        this.name = name;
    }

    public User(String login, String password, String email, String name){
        this.login = login;
        this.password = password;
        this.email = email;
        this.name = name;
    }

    public User(String login,String password,String name){
        this.login = login;
        this.password = password;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", status=" + status +
                ", user_role=" + userRole +
                ", name='" + name + '\'' +
                ", id=" + super.getId() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return login.equals(user.login) && password.equals(user.password) && email.equals(user.email) && status == user.status && userRole == user.userRole && name.equals(user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password, email, status, userRole, name);
    }
}
