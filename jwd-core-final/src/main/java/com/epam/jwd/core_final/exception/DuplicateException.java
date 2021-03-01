package com.epam.jwd.core_final.exception;

public class DuplicateException extends RuntimeException{
    String name;
    String entity;
    public DuplicateException(String name, String entity){
        super();
        this.name = name;
        this.entity = entity;
    }

    @Override
    public String getMessage() {
        return "Entity " + entity +" with this name "+name+" already exists!";
    }
}
