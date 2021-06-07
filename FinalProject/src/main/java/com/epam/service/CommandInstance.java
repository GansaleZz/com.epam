package com.epam.service;

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
    ACTCREATEREQUESTPAGE(new CreateRequestPage()),
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
