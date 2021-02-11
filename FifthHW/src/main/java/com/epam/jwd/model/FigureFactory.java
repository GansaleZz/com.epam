package com.epam.jwd.model;

import com.epam.jwd.exception.FigureNotExistException;
import com.epam.jwd.impl.FigureExistencePostProcessor;
import com.epam.jwd.impl.FigureExistencePreProcessor;

import java.util.Random;

public class FigureFactory extends Figure {

    public static Point[] getDefaultMasP(Figure.FigureType type){
        int i=0;
        Point[] masP;
        switch(type){
            case POINT:
                i=1;
                break;
            case LINE:
                i=2;
                break;
            case TRIANGLE:
                i=3;
                break;
            case SQUARE:
                i=4;
                break;
            case PENTAGON:
                i=5;
                break;
            case HEXAGON:
                i=6;
                break;
        }
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
        switch (type){
            case POINT:
                excpre.figurePreProcess(masP,type);
                toReturn = new Point(masP[0].getX(),masP[0].getY());
                ((Point)toReturn).setMasP(masP);
                break;
            case LINE:
                excpre.figurePreProcess(masP,type);
                toReturn = new Line(masP);
                break;
            case TRIANGLE:
                excpre.figurePreProcess(masP,type);
                toReturn = new Triangle(masP);
                break;
            case SQUARE:
                excpre.figurePreProcess(masP,type);
                toReturn = new Square(masP);
                break;
            case HEXAGON:
            case PENTAGON:
                excpre.figurePreProcess(masP,type);
                toReturn = new MultiAngleFigure(type,masP);
                break;
            default: new FigureNotExistException("Wrong figure type "+type+" for this count of points "+masP.length);
        }
        if(toReturn != null)
            excpost.figurepostprocess(true,type,toReturn);
        else
            excpost.figurepostprocess(false,type,toReturn);
        return toReturn;
    }

    public Figure getFigure(Figure.FigureType type)throws  FigureNotExistException{
        Figure toReturn = null;
        FigureExistencePostProcessor excpost= new FigureExistencePostProcessor();
        FigureExistencePreProcessor excpre = new FigureExistencePreProcessor();
        Point[] masP = getDefaultMasP(type);
            switch (type) {
                case POINT:
                    excpre.figurePreProcess(masP,type);
                    toReturn = new Point(masP[0].getX(), masP[0].getY());
                    ((Point)toReturn).setMasP(masP);
                    break;
                case LINE:
                    excpre.figurePreProcess(masP,type);
                    toReturn = new Line(masP);
                    break;
                case TRIANGLE:
                    excpre.figurePreProcess(masP,type);
                    toReturn = new Triangle(masP);
                    break;
                case SQUARE:
                    excpre.figurePreProcess(masP,type);
                    toReturn = new Square(masP);
                    break;
                case PENTAGON:
                case HEXAGON:
                    excpre.figurePreProcess(masP,type);
                    toReturn = new MultiAngleFigure(type, masP);
                    break;
                default: new FigureNotExistException("Wrong figure type "+type+" for this count of points "+masP.length);
            }
        if(toReturn!=null) {
            excpost.figurepostprocess(true, type, toReturn);
        }else {
            excpost.figurepostprocess(false, type, toReturn);
        }
        return toReturn;
    }


}
