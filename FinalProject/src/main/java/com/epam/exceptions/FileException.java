package com.epam.exceptions;



public class FileException extends Exception {
    String str;

    public FileException(String str){
        super(str);
        this.str = str;
    }

    public FileException(){}

    public FileException(String str, Throwable cause){
        super(str,cause);
        this.str = str;
    }

    public FileException(Throwable cause){
        super(cause);
    }

    @Override
    public String getMessage() {
        return "File "+str+" not found.";
    }
}
