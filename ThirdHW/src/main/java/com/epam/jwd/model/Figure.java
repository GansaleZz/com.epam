package com.epam.jwd.model;


import com.epam.jwd.strategy.Properties;
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
        LINE,
        TRIANGLE,
        SQUARE;
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

//    public enum Propers{
//        INSTANCE;
//
//
//
//        private FigureType type;
//        private Point[] masP;
//
//        Propers(FigureType type, Point[] masP){
//            this.type = type;
//            this.masP = masP;
//        }
//
//        private Properties figure = Properties.getInstance(this.type,this.masP);
//
//    }

    @Override
    public String toString(){
        return "id = "+id;
    }
}
