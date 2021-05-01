package com.epam.service;

public enum ServletDestination implements Destination{
    HOMEPAGE("/home/home.jsp"),
    SIGNUPERROR("/signUp/signUpError.jsp"),
    SIGNUPSUCC("/signUp/signUpSucc.jsp"),
    LOGINERROR("/logIn/logInError.jsp"),
    LOGINPAGE("/logIn/logInPage.jsp");

    private final String PATH;

    ServletDestination(String PATH){
        this.PATH = PATH;
    }

    @Override
    public String getPath() {
        return PATH;
    }
}
