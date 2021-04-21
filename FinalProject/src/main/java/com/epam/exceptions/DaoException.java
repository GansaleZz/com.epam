package com.epam.exceptions;


public class DaoException extends Exception {

    public DaoException(){}

    public DaoException(String str){
        super(str);
    }

    public DaoException(String str, Throwable cause){
        super(str,cause);
    }

    public DaoException(Throwable cause){
        super(cause);
    }

}
