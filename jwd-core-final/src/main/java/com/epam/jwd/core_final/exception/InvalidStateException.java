package com.epam.jwd.core_final.exception;

public class InvalidStateException extends Exception {
    // todo
    String str;
    public InvalidStateException(String str){
        super();
        this.str = str;
    }

    @Override
    public String getMessage() {
        return "Entity " + str+" does not exist!";
    }
}
