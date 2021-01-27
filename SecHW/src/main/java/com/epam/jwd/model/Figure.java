package com.epam.jwd.model;


import java.util.concurrent.atomic.AtomicInteger;
public class Figure {
    private static int id = 0;

    public Figure(){
        ++id;
    }

    private enum FigureType{
        LINE,
        TRIANGLE,
        SQUARE;
    }
}
