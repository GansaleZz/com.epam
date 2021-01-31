package com.epam.jwd.strategy;


import com.epam.jwd.exception.FigureNotExistException;
import com.epam.jwd.model.*;
import org.apache.log4j.Logger;

public enum PropertiesEn implements Figure.figurePropertiesStrategy{ //enum realization
    INSTANCE;

    private static final Logger log = Logger.getLogger(Properties.class);
    private Figure.FigureType type;
    private Point[] masP;

    public void setType(Figure.FigureType type){
        this.type = type;
    }

    public void setMasP(Point[] masP){
        this.masP = masP;
    }

    @Override
    public boolean oppToFindProperties() throws FigureNotExistException {
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
    public void Property() throws FigureNotExistException {
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

