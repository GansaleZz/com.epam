package com.epam.service;

public enum ServletDestination implements Destination{
    ADMINHOMEPAGE("/usersView/admin/home/home.jsp"),
    MODERATORHOMEPAGE("/usersView/moderator/home/home.jsp"),
    CLIENTHOMEPAGE("/usersView/client/home/home.jsp"),
    SIGNUPERROR("/auth/signUpError.jsp"),
    SIGNUPSUCC("/auth/signUpSucc.jsp"),
    SIGNUPPAGE("/auth/signUpPage.jsp"),
    LOGINERROR("/auth/logInError.jsp"),
    LOGINPAGE("/auth/logInPage.jsp"),
    LOGOUT("/auth/logOut.jsp"),
    AUTHPAGE("/auth/authPage.jsp");

    private final String PATH;

    ServletDestination(String PATH){
        this.PATH = PATH;
    }

    @Override
    public String getPath() {
        return PATH;
    }
}
