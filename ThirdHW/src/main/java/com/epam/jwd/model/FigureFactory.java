package com.epam.jwd.model;

import java.util.Random;
import com.epam.jwd.exception.FigureNotExistException;
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

    public Object getFigure(Figure.FigureType type, Point[] masP) throws  FigureNotExistException{
        Object toReturn = null;
        switch (type){
            case POINT:
                toReturn = new Point(masP[0].getX(),masP[0].getY());
                break;
            case LINE:
                toReturn = new Line(masP);
                break;
            case TRIANGLE:
                toReturn = new Triangle(masP);
                break;
            case SQUARE:
                toReturn = new Square(masP);
                break;
            case PENTAGON:
                toReturn = new MultiAngleFigure(type,masP);
                break;
            case HEXAGON:
                toReturn = new MultiAngleFigure(type,masP);
                break;
            default: new FigureNotExistException("Wrong figure type "+type+" for this count of points "+masP.length);
        }
        return toReturn;
    }

    public Object getFigure(Figure.FigureType type)throws  FigureNotExistException{
        Object toReturn = null;
        Point[] masP = getDefaultMasP(type);
            switch (type) {
                case POINT:
                    toReturn = new Point(masP[0].getX(), masP[0].getY());
                    break;
                case LINE:
                    toReturn = new Line(masP);
                    break;
                case TRIANGLE:
                    toReturn = new Triangle(masP);
                    break;
                case SQUARE:
                    toReturn = new Square(masP);
                    break;
                case PENTAGON:
                case HEXAGON:
                    toReturn = new MultiAngleFigure(type, masP);
                    break;
                default: new FigureNotExistException("Wrong figure type "+type+" for this count of points "+masP.length);
            }
        return toReturn;
    }


}
