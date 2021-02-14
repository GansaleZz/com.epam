package com.epam.jwd.model;

import org.apache.log4j.Logger;

public class Triangle extends Figure{
    private static final Logger log = Logger.getLogger(Triangle.class);
    private Point[] masP;
    private Figure.FigureType Type = FigureType.TRIANGLE;
    private double a,b,c;


    protected Triangle(Point[] masP){
        if(masP.length == 3){
            this.masP = masP;
            super.setMasP(masP);
            incrementID();
        }else{
            throw new IllegalArgumentException("Wrong count of points : "+masP.length+", expected 3");
        }
    }


    public boolean checkSamePoints(){
        if((masP[0].getX() == masP[1].getX() && masP[0].getY() == masP[1].getY()) || (masP[0].getX() == masP[2].getX() && masP[0].getY() == masP[2].getY()) || (masP[1].getX() == masP[2].getX() && masP[1].getY() == masP[2].getY())){
            log.error("Its not figure "+this.Type);
            System.out.println("Its not figure "+this.Type);
            return true;
        }
        return false;
    }

    public boolean checkExistance(){
        this.a = Math.pow(Math.pow(this.masP[0].getX() - this.masP[1].getX(),2)+Math.pow(this.masP[0].getY()-this.masP[1].getY(),2),0.5);
        this.b = Math.pow(Math.pow(this.masP[1].getX() - this.masP[2].getX(),2)+Math.pow(this.masP[1].getY()-this.masP[2].getY(),2),0.5);
        this.c = Math.pow(Math.pow(this.masP[0].getX() - this.masP[2].getX(),2)+Math.pow(this.masP[0].getY()-this.masP[2].getY(),2),0.5);
        return !(a >= b + c) && !(b >= a + c) && !(c >= a + b);

    }

    public void existance(){
        if(checkExistance()){
            System.out.println(toString());
        }else{
            log.error(this.Type+" doesnt exist!");
            System.out.println(this.Type+" doesnt exist!");
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
        log.info(this.Type+" exists! Points:  x1:("+masP[0].getX()+","+masP[0].getY()+"), x2:("+masP[1].getX()+","+masP[1].getY()+"), x3:("+masP[2].getX()+","+masP[2].getY()+")");
        return this.Type+" exists! Points:  x1:("+masP[0].getX()+","+masP[0].getY()+"), x2:("+masP[1].getX()+","+masP[1].getY()+"), x3:("+masP[2].getX()+","+masP[2].getY()+")";
    }
}
