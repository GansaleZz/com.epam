package com.epam.controller;


/**
 * Enum of all names of pages and their destinations
 */
public enum ServletDestination implements Destination{
    ADMIN_HOME_PAGE("/WEB-INF/jsp/usersView/admin/home.jsp"),
    ADMIN_PROFILE_PAGE("/WEB-INF/jsp/usersView/admin/profile.jsp"),
    ADMIN_REQUESTS_PAGE("/WEB-INF/jsp/usersView/admin/requests.jsp"),
    ADMIN_USERS_PAGE("/WEB-INF/jsp/usersView/admin/users.jsp"),
    ADMIN_ROOMS_PAGE("/WEB-INF/jsp/usersView/admin/rooms.jsp"),
    ADMIN_NEW_ROOM_PAGE("/WEB-INF/jsp/usersView/admin/newRoom.jsp"),
    ADMIN_APPROVE_REQUEST_PAGE("/WEB-INF/jsp/usersView/admin/approveRequest.jsp"),
    MODERATOR_HOME_PAGE("/WEB-INF/jsp/usersView/moderator/home.jsp"),
    MODERATOR_PROFILE_PAGE("/WEB-INF/jsp/usersView/moderator/profile.jsp"),
    MODERATOR_REQUESTS_PAGE("/WEB-INF/jsp/usersView/moderator/requests.jsp"),
    MODERATOR_ROOMS_PAGE("/WEB-INF/jsp/usersView/moderator/rooms.jsp"),
    MODERATOR_USERS_PAGE("/WEB-INF/jsp/usersView/moderator/users.jsp"),
    MODERATOR_NEW_ROOM_PAGE("/WEB-INF/jsp/usersView/moderator/newRoom.jsp"),
    MODERATOR_APPROVE_REQUEST_PAGE("/WEB-INF/jsp/usersView/moderator/approveRequest.jsp"),
    CLIENT_HOME_PAGE("/WEB-INF/jsp/usersView/client/home.jsp"),
    CLIENT_PROFILE_PAGE("/WEB-INF/jsp/usersView/client/profile.jsp"),
    CLIENT_REQUESTS_PAGE("/WEB-INF/jsp/usersView/client/requests.jsp"),
    CLIENT_ROOMS_PAGE("/WEB-INF/jsp/usersView/client/rooms.jsp"),
    CLIENT_NEW_DEPOSIT_PAGE("/WEB-INF/jsp/usersView/client/newDeposit.jsp"),
    CLIENT_NEW_REQUEST_PAGE("/WEB-INF/jsp/usersView/client/newRequest.jsp"),
    CLIENT_BAD_BALANCE_PAGE("/WEB-INF/jsp/usersView/client/badBalance.jsp"),
    CLIENT_ROOMS_LIST_PAGE("/WEB-INF/jsp/usersView/client/roomsList.jsp"),
    SIGNUP_ERROR("/WEB-INF/jsp/auth/signUpError.jsp"),
    SIGNUP_SUCC("/WEB-INF/jsp/auth/signUpSucc.jsp"),
    SIGNUP_PAGE("/WEB-INF/jsp/auth/signUpPage.jsp"),
    LOGIN_ERROR("/WEB-INF/jsp/auth/logInError.jsp"),
    LOGIN_PAGE("/WEB-INF/jsp/auth/logInPage.jsp"),
    AUTH_PAGE("/WEB-INF/jsp/auth/authPage.jsp"),
    BAN_PAGE("/WEB-INF/jsp/auth/banPage.jsp"),
    ERROR_PAGE("/WEB-INF/jsp/usersView/errorPage.jsp"),
    BAD_ROOM_NUMBER_PAGE("/WEB-INF/jsp/usersView/badRoomNumber.jsp"),
    VERIFY_PAGE("/WEB-INF/jsp/auth/verifyPage.jsp");

    private final String PATH;

    ServletDestination(String PATH){
        this.PATH = PATH;
    }

    @Override
    public String getPath() {
        return PATH;
    }
}
