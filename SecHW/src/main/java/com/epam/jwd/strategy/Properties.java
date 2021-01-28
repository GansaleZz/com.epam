package com.epam.jwd.strategy;

import com.epam.jwd.model.*;
import org.apache.log4j.Logger;

public class Properties implements Figure.figurePropertiesStrategy {
    private static final Logger log = Logger.getLogger(Properties.class);
    private static Properties instance;
    private Figure.FigureType type;
    private Point[] masP;

    private Properties(Figure.FigureType type, Point[] masP){
        this.type = type;
        this.masP = masP;
    }

    public static Properties getInstance(Figure.FigureType type, Point[] masP){
        if(instance == null){
            instance = new Properties(type,masP);
        }
        return instance;
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
