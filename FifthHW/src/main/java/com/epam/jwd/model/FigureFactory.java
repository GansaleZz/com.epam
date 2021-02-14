package com.epam.jwd.model;

import com.epam.jwd.exception.FigureNotExistException;
import com.epam.jwd.impl.FigureExistencePostProcessor;
import com.epam.jwd.impl.FigureExistencePreProcessor;

import java.util.Random;

public class FigureFactory extends Figure {

    public static Point[] getDefaultMasP(Figure.FigureType type){
        int i;
        Point[] masP;
        i = switch (type) {
            case POINT -> 1;
            case LINE -> 2;
            case TRIANGLE -> 3;
            case SQUARE -> 4;
            case PENTAGON -> 5;
            case HEXAGON -> 6;
        };
        masP = new Point[i];
        Random rand = new Random();

        for(int j=0;j<i;++j){
            masP[j] = new Point(rand.nextInt(10),rand.nextInt(10));
        }
        return masP;
    }

    public Figure getFigure(Figure.FigureType type, Point[] masP) throws  FigureNotExistException{
        FigureExistencePostProcessor excpost= new FigureExistencePostProcessor();
        FigureExistencePreProcessor excpre = new FigureExistencePreProcessor();
        Figure toReturn = null;
        switch (type) {
            case POINT -> {
                excpre.figurePreProcess(masP, type);
                toReturn = new Point(masP[0].getX(), masP[0].getY());
                (toReturn).setMasP(masP);
            }
            case LINE -> {
                excpre.figurePreProcess(masP, type);
                toReturn = new Line(masP);
            }
            case TRIANGLE -> {
                excpre.figurePreProcess(masP, type);
                toReturn = new Triangle(masP);
            }
            case SQUARE -> {
                excpre.figurePreProcess(masP, type);
                toReturn = new Square(masP);
            }
            case HEXAGON, PENTAGON -> {
                excpre.figurePreProcess(masP, type);
                toReturn = new MultiAngleFigure(type, masP);
            }
            default -> new FigureNotExistException("Wrong figure type " + type + " for this count of points " + masP.length);
        }
        excpost.figurepostprocess(toReturn != null,type,toReturn);
        return toReturn;
    }

    public Figure getFigure(Figure.FigureType type) throws FigureNotExistException {
        Figure toReturn = null;
        FigureExistencePostProcessor excpost= new FigureExistencePostProcessor();
        FigureExistencePreProcessor excpre = new FigureExistencePreProcessor();
        Point[] masP = getDefaultMasP(type);
        switch (type) {
            case POINT -> {
                excpre.figurePreProcess(masP, type);
                toReturn = new Point(masP[0].getX(), masP[0].getY());
                (toReturn).setMasP(masP);
            }
            case LINE -> {
                excpre.figurePreProcess(masP, type);
                toReturn = new Line(masP);
            }
            case TRIANGLE -> {
                excpre.figurePreProcess(masP, type);
                toReturn = new Triangle(masP);
            }
            case SQUARE -> {
                excpre.figurePreProcess(masP, type);
                toReturn = new Square(masP);
            }
            case PENTAGON, HEXAGON -> {
                excpre.figurePreProcess(masP, type);
                toReturn = new MultiAngleFigure(type, masP);
            }
        }
        excpost.figurepostprocess(toReturn != null, type, toReturn);
        return toReturn;
    }


}
