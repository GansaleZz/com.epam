package com.epam.jwd.strategy;

import com.epam.jwd.exception.FigureNotExistException;
import com.epam.jwd.model.*;
import org.apache.log4j.Logger;

public class Properties implements Figure.figurePropertiesStrategy {
    private static final Logger log = Logger.getLogger(Properties.class);
    private static Properties instance;
    private Figure.FigureType type;
    private Point[] masP;
    private Figure figure;

    private Properties(Figure.FigureType type, Figure figure){ // realized "Lazy" singleton (*)
        this.type = type;
        this.figure = figure;
    }

    public static Properties getInstance(Figure.FigureType type, Figure figure){ // -> (*)
            instance = new Properties(type,figure);
        return instance;
    }

    @Override
    public boolean oppToFindProperties() throws FigureNotExistException {
        switch (this.type){
            case TRIANGLE:
                Triangle triangle = (Triangle) figure;
                return triangle.checkExistance();
            case SQUARE:
                Square Square = (Square) figure;
                return Square.checkExistance();
            case PENTAGON:
            case HEXAGON:
                MultiAngleFigure Polygon = (MultiAngleFigure) figure;
                return Polygon.checkExistance();
            default: return false;
        }
    }


    @Override
    public void Property() throws FigureNotExistException {
        if(oppToFindProperties()) {
            double S,p,P;
            switch (this.type) {
                case TRIANGLE:{
                    Triangle triangle = (Triangle) figure;
                    p = (triangle.getA() + triangle.getB() + triangle.getC())/2;
                    S = Math.sqrt(p*(p-triangle.getA())*(p- triangle.getB())*(p- triangle.getC()));
                    log.info("Properties of this "+this.type+" : area = "+S+", perimeter = "+(p*2));
                    break;
                }
                case SQUARE: {

                    Square square = (Square) figure;
                    S = (square.getLength() * square.getLength());
                    P = (square.getLength() * 4);
                    log.info("Properties of this " + this.type + ": area = " + S + ", perimeter = " + P);
                    break;
                }
                case HEXAGON:
                case PENTAGON:
                    MultiAngleFigure pentagon = (MultiAngleFigure) figure;
                    S = 0;
                    P = 0;
                    Point[] temp;
                    if(type == Figure.FigureType.PENTAGON){
                        temp = new Point[6];
                    }else{
                        temp = new Point[7];
                    }
                    for(int i=0;i<pentagon.getMultiAngleFigure().length;++i){
                        temp[i] = pentagon.getMultiAngleFigure()[i];
                    }
                    temp[temp.length-1] = temp[0];
                    for(int i=1;i<temp.length;++i){
                        S +=(temp[i-1].getX()*temp[i].getY()-temp[i-1].getY()*temp[i].getX());
                    }
                    for(int i=0;i<temp.length-1;++i){
                        P += Math.sqrt((temp[i].getY()-temp[i+1].getY())*(temp[i].getY()-temp[i+1].getY())+(temp[i].getX()-temp[i+1].getX())*(temp[i].getX()-temp[i+1].getX()));
                    }
                    log.info("Properties of this "+this.type+": area = "+Math.abs(S/2)+", perimeter = "+P);
                    break;
            }
        }
    }
}
