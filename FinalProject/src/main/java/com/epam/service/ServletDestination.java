package com.epam.service;

public enum ServletDestination implements Destination{
    HOMEPAGE("/home/home.jsp"),
    SIGNUPERROR("/signUp/signUpError.jsp"),
    SIGNUPSUCC("/signUp/signUpSucc.jsp"),
    SIGNUPPAGE("/signUp/signUpPage.jsp"),
    LOGINERROR("/logIn/logInError.jsp"),
    LOGINPAGE("/logIn/logInPage.jsp"),
    AUTHPAGE("/logIn/authPage.jsp");

    private final String PATH;

    ServletDestination(String PATH){
        this.PATH = PATH;
    }

    @Override
    public String getPath() {
        return PATH;
    }
}
