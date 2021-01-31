package com.epam.jwd.strategy;

import com.epam.jwd.model.*;
import org.apache.log4j.Logger;

public class Properties implements Figure.figurePropertiesStrategy {
    private static final Logger log = Logger.getLogger(Properties.class);
    private static Properties instance;
    private Figure.FigureType type;
    private Point[] masP;

    private Properties(Figure.FigureType type, Point[] masP){ // realized "Lazy" singleton (*)
        this.type = type;
        this.masP = masP;
    }

    public static Properties getInstance(Figure.FigureType type, Point[] masP){ // -> (*)
//        if(instance == null){
            instance = new Properties(type,masP);
        return instance;
    }

    @Override
    public boolean oppToFindProperties(){
        FigureFactory factory = new FigureFactory();
        switch (this.type){
            case TRIANGLE:
                Triangle triangle = (Triangle) factory.getFigure(this.type,this.masP);
                return triangle.checkExistance();
            case SQUARE:
                Square Square = (Square) factory.getFigure(this.type,this.masP);
                return Square.checkExistance();
            default: return false;
        }
    }


    @Override
    public void Property(){
        if(oppToFindProperties()) {
            FigureFactory Factory = new FigureFactory();
            switch (this.type) {
                case TRIANGLE:
                    Triangle triangle = (Triangle) Factory.getFigure(this.type,this.masP);
                    triangle.checkExistance();
                    double p = (triangle.getA() + triangle.getB() + triangle.getC())/2;
                    double S = Math.sqrt(p*(p-triangle.getA())*(p- triangle.getB())*(p- triangle.getC()));
                    log.info("Properties of this triangle : area = "+S+", perimeter = "+(p*2));
                    break;
                case SQUARE:
                    Square square = (Square) Factory.getFigure(this.type,this.masP);
                    square.checkExistance();
                    log.info("Properties of this square: area = "+(square.getLength()*square.getLength())+", perimeter = "+(square.getLength()*4));
                    break;
            }
        }
    }
}
