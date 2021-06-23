package com.epam.controller;

import com.epam.controller.acts.AddNewRequest;
import com.epam.controller.acts.AddNewRoom;
import com.epam.controller.acts.ChangeUsersRS;
import com.epam.controller.acts.LogIn;
import com.epam.controller.acts.LogOut;
import com.epam.controller.acts.PayForRequest;
import com.epam.controller.acts.RealiseDeposite;
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
    ACTSHOWLOGIN(new ShowLogIn()),
    ACTSHOWSIGNUP(new ShowSignUp()),
    ACTSHOWAUTH(new ShowAuth()),
    ACTLOGIN(new LogIn()),
    ACTLOGOUT(new LogOut()),
    ACTSIGNUP(new SignUp()),
    ACTSHOWBAN(new ShowBan()),
    ACTSHOWLOGINERROR(new ShowLogInError()),
    ACTSHOWSIGNUPERROR(new ShowSignUpError()),
    ACTSHOWSIGNUPSUCC(new ShowSignUpSucc()),
    ACTSHOWROOMS(new ShowRooms()),
    ACTSHOWPROFILE(new ShowProfile()),
    ACTSHOWUSERS(new ShowUsers()),
    ACTCHANGEUSERSRS(new ChangeUsersRS()),
    ACTNEWROOMPAGE(new NewRoomPage()),
    ACTADDNEWROOM(new AddNewRoom()),
    ACTUPDATEROOM(new UpdateRoom()),
    ACTUPDATEPROFILE(new UpdateProfile()),
    ACTCREATEREQUESTPAGE(new ShowCreateRequest()),
    ACTNEWREQUEST(new AddNewRequest()),
    ACTNEWDEPOSITPAGE(new NewDepositPage()),
    ACTSHOWHOME(new ShowHome()),
    ACTREALISEDEPOSITE(new RealiseDeposite()),
    ACTSHOWREQUESTS(new ShowRequests()),
    ACTUPDATEREQUEST(new UpdateRequest()),
    ACTPAYFORREQUEST(new PayForRequest()),
    ACTSHOWERROR(new ShowErrorPage());

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
