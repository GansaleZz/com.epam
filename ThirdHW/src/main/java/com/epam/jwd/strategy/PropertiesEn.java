package com.epam.jwd.strategy;


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
    public boolean oppToFindProperties(){
        switch (this.type){
            case TRIANGLE:
                Triangle triangletemp = new Triangle(this.masP);
                return triangletemp.checkExistance();
            case SQUARE:
                Square squaretemp = new Square(this.masP);
                return squaretemp.checkExistance();
            default: return false;
        }
    }

    @Override
    public void Property(){
        if(oppToFindProperties()) {
            switch (this.type) {
                case TRIANGLE:
                    Triangle triangle = new Triangle(this.masP);
                    triangle.checkExistance();
                    double p = (triangle.getA() + triangle.getB() + triangle.getC())/2;
                    double S = Math.sqrt(p*(p-triangle.getA())*(p- triangle.getB())*(p- triangle.getC()));
                    log.info("Properties of this triangle : area = "+S+", perimeter = "+(p*2));

                case SQUARE:
                    Square square = new Square(this.masP);
                    square.checkExistance();
                    log.info("Properties of this square: area = "+(square.getLength()*square.getLength())+", perimeter = "+(square.getLength()*4));
            }
        }
    }
}

