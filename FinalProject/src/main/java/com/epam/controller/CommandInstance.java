package com.epam.controller;

import com.epam.controller.Acts.AddNewRequest;
import com.epam.controller.Acts.AddNewRoom;
import com.epam.controller.Acts.ChangeUsersRS;
import com.epam.controller.Acts.LogIn;
import com.epam.controller.Acts.LogOut;
import com.epam.controller.Acts.PayForRequest;
import com.epam.controller.Acts.RealiseDeposite;
import com.epam.controller.Acts.SignUp;
import com.epam.controller.Acts.UpdateProfile;
import com.epam.controller.Acts.UpdateRequest;
import com.epam.controller.Acts.UpdateRoom;
import com.epam.controller.ShowPage.NewDepositPage;
import com.epam.controller.ShowPage.NewRoomPage;
import com.epam.controller.ShowPage.ShowAuth;
import com.epam.controller.ShowPage.ShowBan;
import com.epam.controller.ShowPage.ShowCreateRequest;
import com.epam.controller.ShowPage.ShowErrorPage;
import com.epam.controller.ShowPage.ShowHome;
import com.epam.controller.ShowPage.ShowLogIn;
import com.epam.controller.ShowPage.ShowLogInError;
import com.epam.controller.ShowPage.ShowProfile;
import com.epam.controller.ShowPage.ShowRequests;
import com.epam.controller.ShowPage.ShowRooms;
import com.epam.controller.ShowPage.ShowSignUp;
import com.epam.controller.ShowPage.ShowSignUpError;
import com.epam.controller.ShowPage.ShowSignUpSucc;
import com.epam.controller.ShowPage.ShowUsers;

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
