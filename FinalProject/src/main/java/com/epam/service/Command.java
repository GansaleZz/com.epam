package com.epam.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Command {

    String link = "controller?command=";

    void execute(HttpServletRequest request, HttpServletResponse response) throws IOException;

    static Command of(String commandName){
        return CommandInstance.commandOf(commandName);
    }
}
