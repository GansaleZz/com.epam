package com.epam.jwd.model;


import com.epam.jwd.exception.FigureNotExistException;

public class Figure {
    private static int id = 0;
    private FigureType type;
    private Point[] masP;


    public void incrementID(){
        ++id;
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



    public Point[] getMasP() {
        return masP;
    }
    
    public void setMasP(Point[] masP){
        this.masP = masP;
    }

    public FigureType getType(){
        return this.type;
    }
    
    public interface figurePropertiesStrategy{
         boolean oppToFindProperties() throws FigureNotExistException; //opportunity
         void Property() throws FigureNotExistException;
    }


    @Override
    public String toString(){
        return "id = "+id;
    }
}
