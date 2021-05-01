package com.epam.service;

public enum ServletDestination implements Destination{
    HOMEPAGE("/home/home.jsp"),
    LOGIN("/logIn/logInPage.jsp"),
    SIGNUP("/signUp/signUpPage.jsp");

    private final String PATH;

    ServletDestination(String PATH){
        this.PATH = PATH;
    }

    @Override
    public String getPath() {
        return PATH;
    }
}
