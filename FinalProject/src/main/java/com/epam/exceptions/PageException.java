package com.epam.exceptions;

public class PageException extends Exception {

    public PageException(){}

    public PageException(String str){
        super(str);
    }

    public PageException(String str, Throwable cause){
        super(str,cause);
    }

    public PageException(Throwable cause){
        super(cause);
    }

}