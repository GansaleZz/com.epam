package com.epam.jwd.core_final.exception;

public class AssignException extends RuntimeException{
    String entity;

    public AssignException(String entity){
        super();
        this.entity = entity;
    }

    @Override
    public String getMessage() {
        return "Entity "+entity+" was assigned to mission yet or mission on progress now";
    }
}
