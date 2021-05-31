package com.epam.service;

public enum ServletDestination implements Destination{
    ADMINHOMEPAGE("/usersView/admin/home.jsp"),
    ADMINPROFILEPAGE("/usersView/admin/profile.jsp"),
    ADMINREQUESTSPAGE("/usersView/admin/requests.jsp"),
    ADMINUSERSPAGE("/usersView/admin/users.jsp"),
    ADMINROOMSPAGE("/usersView/admin/rooms.jsp"),
    ADMINNEWROOMPAGE("/usersView/admin/newRoom.jsp"),
    ADMINAPPROVEREQUESTPAGE("/usersView/admin/approveRequest.jsp"),
    MODERATORHOMEPAGE("/usersView/moderator/home.jsp"),
    MODERATORPROFILEPAGE("/usersView/moderator/profile.jsp"),
    MODERATORREQUESTSPAGE("/usersView/moderator/requests.jsp"),
    MODERATORROOMSPAGE("/usersView/moderator/rooms.jsp"),
    MODERATORUSERSPAGE("/usersView/moderator/users.jsp"),
    MODERATORNEWROOMPAGE("/usersView/moderator/newRoom.jsp"),
    MODERATORAPPROVEREQUESTPAGE("/usersView/moderator/approveRequest.jsp"),
    CLIENTHOMEPAGE("/usersView/client/home.jsp"),
    CLIENTPROFILEPAGE("/usersView/client/profile.jsp"),
    CLIENTREQEUSTSPAGE("/usersView/client/requests.jsp"),
    CLIENTROOMSPAGE("/usersView/client/rooms.jsp"),
    CLIENTNEWDEPOSITPAGE("/usersView/client/newDeposit.jsp"),
    CLIENTNEWREQUESTPAGE("/usersView/client/newRequest.jsp"),
    CLIENTBADBALANCEPAGE("/usersView/client/badBalance.jsp"),
    SIGNUPERROR("/auth/signUpError.jsp"),
    SIGNUPSUCC("/auth/signUpSucc.jsp"),
    SIGNUPPAGE("/auth/signUpPage.jsp"),
    LOGINERROR("/auth/logInError.jsp"),
    LOGINPAGE("/auth/logInPage.jsp"),
    AUTHPAGE("/auth/authPage.jsp"),
    BANPAGE("/auth/banPage.jsp");

    private final String PATH;

    ServletDestination(String PATH){
        this.PATH = PATH;
    }

    @Override
    public String getPath() {
        return PATH;
    }
}
