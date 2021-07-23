package com.epam.controller;

import com.epam.controller.acts.AddNewRequest;
import com.epam.controller.acts.AddNewRoom;
import com.epam.controller.acts.ChangeUsersRS;
import com.epam.controller.acts.LogIn;
import com.epam.controller.acts.LogOut;
import com.epam.controller.acts.PayForRequest;
import com.epam.controller.acts.RealiseDeposit;
import com.epam.controller.acts.SignUp;
import com.epam.controller.acts.UpdateProfile;
import com.epam.controller.acts.UpdateRequest;
import com.epam.controller.acts.UpdateRoom;
import com.epam.controller.showPage.NewDepositPage;
import com.epam.controller.showPage.NewRoomPage;
import com.epam.controller.showPage.ShowAuth;
import com.epam.controller.showPage.ShowBan;
import com.epam.controller.showPage.ShowCreateRequest;
import com.epam.controller.showPage.ShowErrorPage;
import com.epam.controller.showPage.ShowHome;
import com.epam.controller.showPage.ShowLogIn;
import com.epam.controller.showPage.ShowLogInError;
import com.epam.controller.showPage.ShowProfile;
import com.epam.controller.showPage.ShowRequests;
import com.epam.controller.showPage.ShowRooms;
import com.epam.controller.showPage.ShowSignUp;
import com.epam.controller.showPage.ShowSignUpError;
import com.epam.controller.showPage.ShowSignUpSucc;
import com.epam.controller.showPage.ShowUsers;

import java.util.Arrays;

/**
 * Enum of available commands
 *
 */
public enum CommandInstance {
    ACT_SHOW_LOGIN(new ShowLogIn()),
    ACT_SHOW_SIGNUP(new ShowSignUp()),
    ACT_SHOW_AUTH(new ShowAuth()),
    ACT_LOGIN(new LogIn()),
    ACT_LOGOUT(new LogOut()),
    ACT_SIGNUP(new SignUp()),
    ACT_SHOW_BAN(new ShowBan()),
    ACT_SHOW_LOGIN_ERROR(new ShowLogInError()),
    ACT_SHOW_SIGNUP_ERROR(new ShowSignUpError()),
    ACT_SHOW_SIGNUP_SUCC(new ShowSignUpSucc()),
    ACT_SHOW_ROOMS(new ShowRooms()),
    ACT_SHOW_PROFILE(new ShowProfile()),
    ACT_SHOW_USERS(new ShowUsers()),
    ACT_CHANGE_USERS_RS(new ChangeUsersRS()),
    ACT_NEW_ROOM_PAGE(new NewRoomPage()),
    ACT_ADD_NEW_ROOM(new AddNewRoom()),
    ACT_UPDATE_ROOM(new UpdateRoom()),
    ACT_UPDATE_PROFILE(new UpdateProfile()),
    ACT_CREATE_REQUEST_PAGE(new ShowCreateRequest()),
    ACT_NEW_REQUEST(new AddNewRequest()),
    ACT_NEW_DEPOSIT_PAGE(new NewDepositPage()),
    ACT_SHOW_HOME(new ShowHome()),
    ACT_REALISE_DEPOSIT(new RealiseDeposit()),
    ACT_SHOW_REQUESTS(new ShowRequests()),
    ACT_UPDATE_REQUEST(new UpdateRequest()),
    ACT_PAY_FOR_REQUEST(new PayForRequest()),
    ACT_SHOW_ERROR(new ShowErrorPage());

    private final Command command;

    CommandInstance(Command command){
        this.command = command;
    }

    public static Command commandOf(String commandName){
        return Arrays.stream(values())
                .filter(i -> i.name().equalsIgnoreCase(commandName))
                .findAny().get().command;
    }
}
