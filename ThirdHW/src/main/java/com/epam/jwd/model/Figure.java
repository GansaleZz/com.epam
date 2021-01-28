package com.epam.jwd.model;


import org.apache.log4j.Logger;

public class Figure {
    private static int id = 0;

    public void incrementID(){
        ++this.id;
    }

    public int getId(){
        return this.id;
    }

    public enum FigureType{
        LINE,
        TRIANGLE,
        SQUARE;
    }


    public interface figurePropertiesStrategy{
         boolean oppToFindProperties(); //opportunity
         void Property();
    }

    @Override
    public String toString(){
        return "id = "+id;
    }
}
