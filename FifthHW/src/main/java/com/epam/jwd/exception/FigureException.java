package com.epam.jwd.exception;

public class FigureException extends Exception{
    String str;
    public FigureException(String str) {
        this.str = str;
    }
}
