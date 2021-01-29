package com.epam.jwd.model;


import com.epam.jwd.strategy.*;
import org.apache.log4j.Logger;

public class Figure {
    private static int id = 0;
    private FigureType type;
    private Point[] masP;


    public void incrementID(){
        ++this.id;
    }

    public int getId(){
        return this.id;
    }

    public enum FigureType{
        POINT,
        LINE,
        TRIANGLE,
        SQUARE,
        PENTAGON,
        HEXAGON;
    }

    public Figure(){
    }

    public Figure(FigureType type, Point[] masP){
        this.type = type;
        this.masP = masP;
    }


    public Point[] getMasP() {
        return masP;
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
