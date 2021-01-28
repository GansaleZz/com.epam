package com.epam.jwd.model;


public class Figure {
    private static int id = 0;

    public void incrementID(){
        ++this.id;
    }

    public int getId(){
        return this.id;
    }

    private enum FigureType{
        LINE,
        TRIANGLE,
        SQUARE;
    }

    @Override
    public String toString(){
        return "id = "+id;
    }
}
