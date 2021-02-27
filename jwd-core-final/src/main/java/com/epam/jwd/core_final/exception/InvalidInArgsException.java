package com.epam.jwd.core_final.exception;

public class InvalidInArgsException extends RuntimeException{
    private final String args;

    public InvalidInArgsException(Object... args){
        super();
        String temp = "";
        for(Object arg : args){
            temp += arg + " ";
        }
        this.args = temp;
    }

    @Override
    public String getMessage() {
        return "Invalid input args :"+args;
    }
}
