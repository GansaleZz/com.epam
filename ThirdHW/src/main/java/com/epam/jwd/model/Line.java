package com.epam.jwd.model;


import com.epam.jwd.model.*;
import org.apache.log4j.Logger;

public class Line extends Figure{
    private Point[] masP = new Point[2];
    private static final Logger log = Logger.getLogger(Line.class);
    private Figure.FigureType Type = FigureType.LINE;

    protected Line(){
        for(int i=0;i<this.masP.length;++i){
            this.masP[i] = new Point();
        }
        incrementID();
    }

    protected Line(Point[] masP){
        if(masP.length == 2 ){
            this.masP = masP;
            incrementID();
        }else{
            throw new IllegalArgumentException("Wrong count of points : "+masP.length+", expected 2");
        }
    }

    public boolean checkSamePoints(){
        if(masP[0].getX() == masP[1].getX() && masP[0].getY() == masP[1].getY()){
            log.error("Its not figure "+this.Type);
            System.out.println("Its not figure "+this.Type);
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

    public Figure.FigureType getType(){
        return this.Type;
    }

    @Override
    public String toString(){
        log.info(this.Type+" exists! Points: x1:("+masP[0].getX()+","+masP[0].getY()+"), x2:("+masP[1].getX()+","+masP[1].getY()+")");
        return this.Type+" exists! Points: x1:("+masP[0].getX()+","+masP[0].getY()+"), x2:("+masP[1].getX()+","+masP[1].getY()+")";
    }
}
