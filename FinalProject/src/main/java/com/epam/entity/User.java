package com.epam.entity;

public class User extends Entity{
    private final String login;
    private String password;
    private String email;
    private UserStatus status = UserStatus.AVAILABLE;
    private UserRole user_role;

    public User(String login, String password, String email){
        this.login = login;
        this.email = email;
        this.password = password;
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
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", status=" + status +
                ", user_role=" + user_role +
                '}';
    }
}
