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
            System.out.println("Its not figure 'SQUARE'");
            return true;
        }
        return false;
    }


    public void CheckExistance(){
        boolean bool = false;
            for(int i=1;i< masP.length;++i){
                if(masP[0].getY() == masP[i].getY() && masP[0].getX() != masP[i].getX()){
                    if(i == 1 ){
                        if(masP[2].getX()+masP[3].getX() == masP[0].getX()+masP[1].getX() && masP[2].getY() == masP[3].getY())
                        {
                            log.info("Square exists! Points:  x1:("+masP[0].getX()+","+masP[0].getY()+"), x2:("+masP[1].getX()+","+masP[1].getY()+"), x3:("+masP[2].getX()+","+masP[2].getY()+"), x4:("+masP[3].getX()+","+masP[3].getY()+")");
                            System.out.println("Square exists! Points:  x1:("+masP[0].getX()+","+masP[0].getY()+"), x2:("+masP[1].getX()+","+masP[1].getY()+"), x3:("+masP[2].getX()+","+masP[2].getY()+"), x4:("+masP[3].getX()+","+masP[3].getY()+")");
                        }
                        else{
                            log.error("Square doesnot exist!");
                            System.out.println("Square doesnt exist!");
                        }
                        bool = true;
                        break;
                    } else {
                        if(i == 2 ) {
                            if (masP[1].getX() + masP[3].getX() == masP[0].getX() + masP[2].getX() && masP[1].getY() == masP[3].getY()) {
                                log.info("Square exists! Points:  x1:("+masP[0].getX()+","+masP[0].getY()+"), x2:("+masP[1].getX()+","+masP[1].getY()+"), x3:("+masP[2].getX()+","+masP[2].getY()+"), x4:("+masP[3].getX()+","+masP[3].getY()+")");
                                System.out.println("Square exists! Points:  x1:("+masP[0].getX()+","+masP[0].getY()+"), x2:("+masP[1].getX()+","+masP[1].getY()+"), x3:("+masP[2].getX()+","+masP[2].getY()+"), x4:("+masP[3].getX()+","+masP[3].getY()+")");
                            } else {
                                log.error("Square doesnt exist!");
                                System.out.println("Square doesnt exist!");
                            }
                        }else{
                            if (masP[1].getX() + masP[2].getX() == masP[0].getX() + masP[3].getX() && masP[1].getY() == masP[2].getY()) {
                                log.info("Square exists! Points:  x1:("+masP[0].getX()+","+masP[0].getY()+"), x2:("+masP[1].getX()+","+masP[1].getY()+"), x3:("+masP[2].getX()+","+masP[2].getY()+"), x4:("+masP[3].getX()+","+masP[3].getY()+")");
                                System.out.println("Square exists! Points:  x1:("+masP[0].getX()+","+masP[0].getY()+"), x2:("+masP[1].getX()+","+masP[1].getY()+"), x3:("+masP[2].getX()+","+masP[2].getY()+"), x4:("+masP[3].getX()+","+masP[3].getY()+")");
                            } else {
                                log.error("Square doesnt exist!");
                                System.out.println("Square doesnt exist!");
                            }
                        }
                        bool = true;
                        break;
                    }
                }
            }
            if (!bool){
                log.error("Square doesnt exist!");
                System.out.println("Square doesnt exist!");
            }
    }

    @Override
    public String toString(){
        log.info("Square exists! Points:  x1:("+masP[0].getX()+","+masP[0].getY()+"), x2:("+masP[1].getX()+","+masP[1].getY()+"), x3:("+masP[2].getX()+","+masP[2].getY()+"), x4:("+masP[3].getX()+","+masP[3].getY()+")");
        return "Square exists! Points:  x1:("+masP[0].getX()+","+masP[0].getY()+"), x2:("+masP[1].getX()+","+masP[1].getY()+"), x3:("+masP[2].getX()+","+masP[2].getY()+")";
    }
}
