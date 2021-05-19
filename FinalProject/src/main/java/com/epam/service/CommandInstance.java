package com.epam.service;

import java.util.Arrays;

public enum CommandInstance {
    LOGIN(new LogIn()),
    LOGOUT(new LogOut()),
    SIGNUP(new SignUp()),
    ACTSHOWROOMS(new ShowRooms()),
    ACTSHOWPROFILE(new ShowProfile()),
    ACTSHOWUSERS(new ShowUsers()),
    ACTCHANGEUSERSRS(new ChangeUsersRS()),
    ACTNEWROOMPAGE(new NewRoomPage()),
    ACTADDNEWROOM(new AddNewRoom()),
    ACTUPDATEROOM(new UpdateRoom()),
    ACTUPDATEPROFILE(new UpdateProfile()),
    ACTCREATEREQUEST(new CreateRequestPage());

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
