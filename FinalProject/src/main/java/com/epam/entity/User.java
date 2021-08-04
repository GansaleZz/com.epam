package com.epam.entity;

import java.io.Serializable;
import java.util.Objects;

public class User extends BaseEntity implements Serializable {
    private String login;
    private String password;
    private String email;
    private UserStatus status;
    private UserRole userRole;
    private String name;
    private double balance;
    private AccountStatus accountStatus;

    public User(){}

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

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", status=" + status +
                ", userRole=" + userRole +
                ", name='" + name + '\'' +
                ", balance=" + balance +
                ", id=" + super.getId()+
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Double.compare(user.balance, balance) == 0 && Objects.equals(login, user.login) && Objects.equals(password, user.password) && Objects.equals(email, user.email) && status == user.status && userRole == user.userRole && Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password, email, status, userRole, name, balance);
    }
}
