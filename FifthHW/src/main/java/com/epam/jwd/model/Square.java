package com.epam.jwd.model;

import org.apache.log4j.Logger;

public class Square extends Figure{
    private static final Logger log = Logger.getLogger(Square.class);
    private Point[] masP = new Point[4];
    private Figure.FigureType Type = FigureType.SQUARE;
    private double length;

    protected Square(){
        for(int i=0;i<this.masP.length;++i){
            this.masP[i] = new Point();
        }
        super.setMasP(masP);
        incrementID();
    }

    protected Square(Point[] masP){
        if(masP.length == 4){
            this.masP = masP;
            super.setMasP(masP);
            incrementID();
        }else{
            throw new IllegalArgumentException("Wrong count of points : "+masP.length+", expected 4");
        }
    }

    public void setSquare(Point[] masP){
        this.masP = masP;
    }

    public Point[] getSquare(){
        return this.masP;
    }

    public boolean checkSamePoints(){
        if((masP[0].getX() == masP[1].getX() && masP[0].getY() == masP[1].getY()) || (masP[0].getX() == masP[2].getX() && masP[0].getY() == masP[2].getY()) || (masP[0].getX() == masP[3].getX() && masP[0].getY() == masP[3].getY()) || (masP[1].getX() == masP[2].getX() && masP[1].getY() == masP[2].getY()) || (masP[1].getX() == masP[3].getX() && masP[1].getY() == masP[3].getY()) || (masP[2].getX() == masP[3].getX() && masP[2].getY() == masP[3].getY())){
            log.error("Its not figure "+this.Type);
            System.out.println("Its not figure "+this.Type);
            return true;
        }
        return false;
    }


    public boolean checkExistance(){
            double[] D = new double[6];

            D[0] = masP[0].distance(masP[1]);
            D[1] = masP[0].distance(masP[2]);
            D[2] = masP[0].distance(masP[3]);
            D[3] = masP[1].distance(masP[2]);
            D[4] = masP[1].distance(masP[3]);
            D[5] = masP[2].distance(masP[3]);

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
            length = D[5];
            for(int i = 5 ; i >-1; --i){
                D[i] /= D[0];
            }

            if((Math.abs(D[0]-1)<Math.pow(1,-8)&&
                    Math.abs(D[1]-1)<Math.pow(1,-8)&&
                    Math.abs(D[2]-1)<Math.pow(1,-8)&&
                    Math.abs(D[3]-1)<Math.pow(1,-8)&&
                    Math.abs(D[4]-Math.sqrt(2))<Math.pow(1,-8)&&
                    Math.abs(D[5]-Math.sqrt(2))<Math.pow(1,-8)))
            {
                return true;
            }else{
               return false;
            }

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

    public double getLength(){
        return this.length;
    }

    @Override
    public String toString(){
        log.info(this.Type+" exists! Points:  x1:("+masP[0].getX()+","+masP[0].getY()+"), x2:("+masP[1].getX()+","+masP[1].getY()+"), x3:("+masP[2].getX()+","+masP[2].getY()+"), x4:("+masP[3].getX()+","+masP[3].getY()+")");
        return this.Type+" exists! Points:  x1:("+masP[0].getX()+","+masP[0].getY()+"), x2:("+masP[1].getX()+","+masP[1].getY()+"), x3:("+masP[2].getX()+","+masP[2].getY()+"), x4:("+masP[3].getX()+","+masP[3].getY()+")";
    }
}
