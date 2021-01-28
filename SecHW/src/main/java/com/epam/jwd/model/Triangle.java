package com.epam.jwd.model;

import java.lang.Math;
import org.apache.log4j.Logger;
import com.epam.jwd.model.*;

public class Triangle extends Figure{
    private static final Logger log = Logger.getLogger(Triangle.class);
    private Point[] masP = new Point[3];
    private Figure.FigureType Type = FigureType.TRIANGLE;
    private double a,b,c;

    public Triangle(){
        for(int i=0;i<this.masP.length;++i){
            this.masP[i] = new Point();
        }
        incrementID();
    }

    public Triangle(Point[] masP){
        this.masP = masP;
        incrementID();
    }

    public Point[] getTriangle(){
        return this.masP;
    }

    public void setTriangle(Point[] masP){
        this.masP = masP;
    }

    public boolean checkSamePoints(){
        if((masP[0].getX() == masP[1].getX() && masP[0].getY() == masP[1].getY()) || (masP[0].getX() == masP[2].getX() && masP[0].getY() == masP[2].getY()) || (masP[1].getX() == masP[2].getX() && masP[1].getY() == masP[2].getY())){
            log.error("Its not figure 'TRIANGLE'");
            System.out.println("Its not figure 'TRIANGLE'");
            return true;
        }
        return false;
    }

    public boolean checkExistance(){
        double a,b,c;
        a = Math.pow(Math.pow(this.masP[0].getX() - this.masP[1].getX(),2)+Math.pow(this.masP[0].getY()-this.masP[1].getY(),2),0.5);
        b = Math.pow(Math.pow(this.masP[1].getX() - this.masP[2].getX(),2)+Math.pow(this.masP[1].getY()-this.masP[2].getY(),2),0.5);
        c = Math.pow(Math.pow(this.masP[0].getX() - this.masP[2].getX(),2)+Math.pow(this.masP[0].getY()-this.masP[2].getY(),2),0.5);
        if (a>=b+c || b>=a+c || c>=a+b){
            return false;
        }else{
            return true;
        }

    }

    public void existance(){
        if(checkExistance()){
            System.out.println(toString());
        }else{
            log.error("Triangle doesnt exist!");
            System.out.println("Triangle doesnt exist!");
        }
    }

    public Figure.FigureType getType(){
        return this.Type;
    }

    public double getA(){
        return this.a;
    }

    public double getB(){
        return this.b;
    }

    public double getC(){
        return this.c;
    }

    @Override
    public String toString(){
        log.info("Triangle exists! Points:  x1:("+masP[0].getX()+","+masP[0].getY()+"), x2:("+masP[1].getX()+","+masP[1].getY()+"), x3:("+masP[2].getX()+","+masP[2].getY()+")");
        return "Triangle exists! Points:  x1:("+masP[0].getX()+","+masP[0].getY()+"), x2:("+masP[1].getX()+","+masP[1].getY()+"), x3:("+masP[2].getX()+","+masP[2].getY()+")";
    }
}
