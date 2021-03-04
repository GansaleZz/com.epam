package com.epam.jwd.core_final.exception;

public class FullAssignedException extends RuntimeException{
    String name;

    public FullAssignedException(String name){
        super();
        this.name = name;
    }

    @Override
    public String getMessage() {
        return "Mission "+name+" has full crew! You can not add crew members with this role more...";
    }
}
