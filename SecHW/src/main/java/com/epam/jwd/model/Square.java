package com.epam.jwd.model;

import org.apache.log4j.Logger;


public class Square extends Figure{
    private static final Logger log = Logger.getLogger(Square.class);
    private Point[] masP = new Point[4];

    public Square(){
        for(int i=0;i<this.masP.length;++i){
            this.masP[i] = new Point();
        }
        IncrementID();
    }

    public Square(Point[] masP){
        this.masP = masP;
        IncrementID();
    }

    public void setSquare(Point[] masP){
        this.masP = masP;
    }

    public Point[] getSquare(){
        return this.masP;
    }

    public boolean CheckSamePoints(){
        if((masP[0].getX() == masP[1].getX() && masP[0].getY() == masP[1].getY()) || (masP[0].getX() == masP[2].getX() && masP[0].getY() == masP[2].getY()) || (masP[0].getX() == masP[3].getX() && masP[0].getY() == masP[3].getY()) || (masP[1].getX() == masP[2].getX() && masP[1].getY() == masP[2].getY()) || (masP[1].getX() == masP[3].getX() && masP[1].getY() == masP[3].getY()) || (masP[2].getX() == masP[3].getX() && masP[2].getY() == masP[3].getY())){
            log.error("Its not figure 'SQUARE'");
            return true;
        }
        return false;
    }


    public void сheckExistance(){
            double[] D = new double[6];

            D[0] = masP[0].Distance(masP[1]);
            D[1] = masP[0].Distance(masP[2]);
            D[2] = masP[0].Distance(masP[3]);
            D[3] = masP[1].Distance(masP[2]);
            D[4] = masP[1].Distance(masP[3]);
            D[5] = masP[2].Distance(masP[3]);

            for(int i=0;i<D.length;++i){
                double temp;
                for(int j=0;j<D.length-1;++j){
                    if(D[j]>D[j+1]){
                        temp = D[j];
                        D[j] = D[j+1];
                        D[j+1] = temp;
                    }
                }
            }

            for(int i = 5 ; i >-1; --i){
                D[i] /= D[0];
            }

            if((Math.abs(D[0]-1)<Math.pow(1,-8)&&
                    Math.abs(D[1]-1)<Math.pow(1,-8)&&
                    Math.abs(D[2]-1)<Math.pow(1,-8)&&
                    Math.abs(D[3]-1)<Math.pow(1,-8)&&
                    Math.abs(D[4]-Math.sqrt(2))<Math.pow(1,-8)&&
                    Math.abs(D[5]-Math.sqrt(2))<Math.pow(1,-8)))
            {System.out.println(toString());}else{
                log.error("Square doesnt exist!");
            }

    }

    @Override
    public String toString(){
        log.info("Square exists! Points:  x1:("+masP[0].getX()+","+masP[0].getY()+"), x2:("+masP[1].getX()+","+masP[1].getY()+"), x3:("+masP[2].getX()+","+masP[2].getY()+"), x4:("+masP[3].getX()+","+masP[3].getY()+")");
        return "Square exists! Points:  x1:("+masP[0].getX()+","+masP[0].getY()+"), x2:("+masP[1].getX()+","+masP[1].getY()+"), x3:("+masP[2].getX()+","+masP[2].getY()+"), x4:("+masP[3].getX()+","+masP[3].getY()+")";
    }
}
