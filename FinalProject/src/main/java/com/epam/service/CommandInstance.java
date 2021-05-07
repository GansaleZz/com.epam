package com.epam.service;

import java.util.Arrays;

public enum CommandInstance {
    LOGIN(new LogIn()),
    LOGOUT(new LogOut()),
    SIGNUP(new SignUp());

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
