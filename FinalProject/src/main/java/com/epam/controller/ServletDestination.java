package com.epam.controller;


/**
 * Enum of all names of pages and their destinations
 */
public enum ServletDestination implements Destination{
    ADMINHOMEPAGE("/WEB-INF/jsp/usersView/admin/home.jsp"),
    ADMINPROFILEPAGE("/WEB-INF/jsp/usersView/admin/profile.jsp"),
    ADMINREQUESTSPAGE("/WEB-INF/jsp/usersView/admin/requests.jsp"),
    ADMINUSERSPAGE("/WEB-INF/jsp/usersView/admin/users.jsp"),
    ADMINROOMSPAGE("/WEB-INF/jsp/usersView/admin/rooms.jsp"),
    ADMINNEWROOMPAGE("/WEB-INF/jsp/usersView/admin/newRoom.jsp"),
    ADMINAPPROVEREQUESTPAGE("/WEB-INF/jsp/usersView/admin/approveRequest.jsp"),
    MODERATORHOMEPAGE("/WEB-INF/jsp/usersView/moderator/home.jsp"),
    MODERATORPROFILEPAGE("/WEB-INF/jsp/usersView/moderator/profile.jsp"),
    MODERATORREQUESTSPAGE("/WEB-INF/jsp/usersView/moderator/requests.jsp"),
    MODERATORROOMSPAGE("/WEB-INF/jsp/usersView/moderator/rooms.jsp"),
    MODERATORUSERSPAGE("/WEB-INF/jsp/usersView/moderator/users.jsp"),
    MODERATORNEWROOMPAGE("/WEB-INF/jsp/usersView/moderator/newRoom.jsp"),
    MODERATORAPPROVEREQUESTPAGE("/WEB-INF/jsp/usersView/moderator/approveRequest.jsp"),
    CLIENTHOMEPAGE("/WEB-INF/jsp/usersView/client/home.jsp"),
    CLIENTPROFILEPAGE("/WEB-INF/jsp/usersView/client/profile.jsp"),
    CLIENTREQEUSTSPAGE("/WEB-INF/jsp/usersView/client/requests.jsp"),
    CLIENTROOMSPAGE("/WEB-INF/jsp/usersView/client/rooms.jsp"),
    CLIENTNEWDEPOSITPAGE("/WEB-INF/jsp/usersView/client/newDeposit.jsp"),
    CLIENTNEWREQUESTPAGE("/WEB-INF/jsp/usersView/client/newRequest.jsp"),
    CLIENTBADBALANCEPAGE("/WEB-INF/jsp/usersView/client/badBalance.jsp"),
    SIGNUPERROR("/WEB-INF/jsp/auth/signUpError.jsp"),
    SIGNUPSUCC("/WEB-INF/jsp/auth/signUpSucc.jsp"),
    SIGNUPPAGE("/WEB-INF/jsp/auth/signUpPage.jsp"),
    LOGINERROR("/WEB-INF/jsp/auth/logInError.jsp"),
    LOGINPAGE("/WEB-INF/jsp/auth/logInPage.jsp"),
    AUTHPAGE("/WEB-INF/jsp/auth/authPage.jsp"),
    BANPAGE("/WEB-INF/jsp/auth/banPage.jsp"),
    ERROPAGE("/WEB-INF/jsp/usersView/errorPage.jsp");

    private final String PATH;

    ServletDestination(String PATH){
        this.PATH = PATH;
    }

    @Override
    public String getPath() {
        return PATH;
    }
}
