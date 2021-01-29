package com.epam.jwd.model;


import com.epam.jwd.model.*;
import com.epam.jwd.strategy.Properties;
import org.apache.log4j.Logger;

public class MultiAngleFigure extends Figure{
    private static final Logger log = Logger.getLogger(Properties.class);
    private Figure.FigureType type;
    private Point[] masP;

    protected MultiAngleFigure(FigureType type,Point[] masP){
        if((masP.length == 5 && type == FigureType.PENTAGON) || (masP.length == 6 && type == FigureType.HEXAGON)){
        this.type = type;
        this.masP = masP;
        incrementID();
        }else{
            throw new IllegalArgumentException("Wrong input parameteres!");
        }
    }

    public Point[] getMultiAngleFigure(){
        return this.masP;
    }

    public FigureType getType(){
        return this.type;
    }

    @Override
    public String toString(){
        switch (this.type){
            case PENTAGON:
                log.info("Pentagon exists! Points:  x1:("+masP[0].getX()+","+masP[0].getY()+"), x2:("+masP[1].getX()+","+masP[1].getY()+"), x3:("+masP[2].getX()+","+masP[2].getY()+"), x4:("+masP[3].getX()+","+masP[3].getY()+"), x5:("+masP[4].getX()+","+masP[4].getY()+")");
                return "Pentagon exists! Points:  x1:("+masP[0].getX()+","+masP[0].getY()+"), x2:("+masP[1].getX()+","+masP[1].getY()+"), x3:("+masP[2].getX()+","+masP[2].getY()+"), x4:("+masP[3].getX()+","+masP[3].getY()+"), x5:("+masP[4].getX()+","+masP[4].getY()+")";
            case HEXAGON:
                log.info("Hexagon exists! Points:  x1:("+masP[0].getX()+","+masP[0].getY()+"), x2:("+masP[1].getX()+","+masP[1].getY()+"), x3:("+masP[2].getX()+","+masP[2].getY()+"), x4:("+masP[3].getX()+","+masP[3].getY()+"), x5:("+masP[4].getX()+","+masP[4].getY()+"), x6:("+masP[5].getX()+","+masP[5].getY()+")");
                return ("Hexagon exists! Points:  x1:("+masP[0].getX()+","+masP[0].getY()+"), x2:("+masP[1].getX()+","+masP[1].getY()+"), x3:("+masP[2].getX()+","+masP[2].getY()+"), x4:("+masP[3].getX()+","+masP[3].getY()+"), x5:("+masP[4].getX()+","+masP[4].getY()+"), x6:("+masP[5].getX()+","+masP[5].getY()+")");
        }
        return "";
    }


}
