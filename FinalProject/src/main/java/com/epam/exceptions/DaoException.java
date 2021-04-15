package com.epam.exceptions;

import java.sql.SQLException;

public class DaoException extends SQLException {
    private String str;

    public DaoException(String str){
        super();
        this.str = str;
    }

    @Override
    public String getMessage() {
        return str;
    }
}
