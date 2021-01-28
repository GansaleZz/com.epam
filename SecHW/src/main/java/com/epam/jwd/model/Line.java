package com.epam.jwd.model;


import com.epam.jwd.model.*;
import org.apache.log4j.Logger;

public class Line extends Figure{
    private Point[] masP = new Point[2];
    private static final Logger log = Logger.getLogger(Line.class);

    public Line(){
        for(int i=0;i<this.masP.length;++i){
            this.masP[i] = new Point();
        }
        IncrementID();
    }

    public Line(Point[] masP){
        this.masP = masP;
        IncrementID();
    }

    public boolean CheckSamePoints(){
        if(masP[0].getX() == masP[1].getX() && masP[0].getY() == masP[1].getY()){
            log.error("Its not figure 'LINE'");
            return true;
        }
        return false;
    }

    public Point[] getLine(){
        return this.masP;
    }

    public void setLine(Point[] masP){
        this.masP = masP;
    }

    @Override
    public String toString(){
        log.info("Line exists! Points: x1:("+masP[0].getX()+","+masP[0].getY()+"), x2:("+masP[1].getX()+","+masP[1].getY()+")");
        return "Line exists! Points: x1:("+masP[0].getX()+","+masP[0].getY()+"), x2:("+masP[1].getX()+","+masP[1].getY()+")";
    }
}
