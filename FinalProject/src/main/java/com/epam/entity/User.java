package com.epam.entity;

import java.util.Objects;

public class User extends Entity{
    private final String login;
    private String password;
    private String email;
    private UserStatus status;
    private UserRole user_role;
    private String name;
    private int id;

    public User(String login, String password, String email,String name,int id , UserStatus status, UserRole role){
        this.id = id;
        this.login = login;
        this.email = email;
        this.password = password;
        this.user_role = role;
        this.status = status;
        this.name = name;
    }

    public User(String login, String password, String email,String name, UserStatus status, UserRole role){
        this.login = login;
        this.email = email;
        this.password = password;
        this.user_role = role;
        this.status = status;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRole(UserRole user_role) {
        this.user_role = user_role;
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

    public UserRole getRole() {
        return user_role;
    }

    public String getLogin() {
        return login;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", status=" + status +
                ", user_role=" + user_role +
                ", name='" + name + '\'' +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && login.equals(user.login) && password.equals(user.password) && email.equals(user.email) && status == user.status && user_role == user.user_role && name.equals(user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password, email, status, user_role, name, id);
    }
}
