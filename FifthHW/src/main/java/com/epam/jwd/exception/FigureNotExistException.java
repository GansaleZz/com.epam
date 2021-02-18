package com.epam.jwd.exception;

public class FigureNotExistException extends FigureException{

    public FigureNotExistException(String str){
        super(str);
    }

    @Override
    public String toString(){
        return "FigureNotExistException: "+str;
    }
}
