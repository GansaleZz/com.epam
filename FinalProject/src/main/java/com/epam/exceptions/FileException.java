package com.epam.exceptions;

import java.io.FileNotFoundException;
import java.io.IOException;

public class FileException extends IOException {
    String str;

    public FileException(String str){
        super();
        this.str = str;
    }

    @Override
    public String getMessage() {
        return "File "+str+" not found.";
    }
}
