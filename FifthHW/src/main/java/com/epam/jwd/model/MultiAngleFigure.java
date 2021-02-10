package com.epam.jwd.model;


import com.epam.jwd.strategy.*;
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

    public boolean checkExistance(){
        int count = this.masP.length - 2;;
        do {
            int i1 = this.masP.length - 2;
            int i2 = this.masP.length - 1;
            int i3 = this.masP.length;
            while(i3-count < masP.length){
                if ((this.masP[i3-count].getX() - this.masP[i1-count].getX()) *(this.masP[i2-count].getY() - this.masP[i1-count].getY()) == (this.masP[i3-count].getY() - this.masP[i1-count].getY())*(this.masP[i2-count].getX() - this.masP[i1-count].getX())) {
                    return false;
                }
                ++i2;
                ++i3;
            }
            --count;
        }while(count!=0);
        return true;
    }

    public void existance(){
        if (checkExistance()){
            System.out.println(toString());
        }else{
            log.error(this.type+" doesnt exist!");
            System.out.println(this.type+" doesnt exist!");
        }
    }

    public boolean checkSamePoints(){
        int i1 = 0;
        do{
            for(int i2=i1+1;i2< this.masP.length;++i2){
                if(this.masP[i1].getX()==this.masP[i2].getX() && this.masP[i1].getY()==this.masP[i2].getY()){
                    log.error("Its not figure "+this.type);
                    return true;
                }
            }
            ++i1;
        }while(i1!=this.masP.length-1);
        return false;
    }

    @Override
    public String toString(){
        switch (this.type){
            case PENTAGON:
                log.info(this.type+" exists! Points:  x1:("+masP[0].getX()+","+masP[0].getY()+"), x2:("+masP[1].getX()+","+masP[1].getY()+"), x3:("+masP[2].getX()+","+masP[2].getY()+"), x4:("+masP[3].getX()+","+masP[3].getY()+"), x5:("+masP[4].getX()+","+masP[4].getY()+")");
                return this.type+" exists! Points:  x1:("+masP[0].getX()+","+masP[0].getY()+"), x2:("+masP[1].getX()+","+masP[1].getY()+"), x3:("+masP[2].getX()+","+masP[2].getY()+"), x4:("+masP[3].getX()+","+masP[3].getY()+"), x5:("+masP[4].getX()+","+masP[4].getY()+")";
            case HEXAGON:
                log.info(this.type+" exists! Points:  x1:("+masP[0].getX()+","+masP[0].getY()+"), x2:("+masP[1].getX()+","+masP[1].getY()+"), x3:("+masP[2].getX()+","+masP[2].getY()+"), x4:("+masP[3].getX()+","+masP[3].getY()+"), x5:("+masP[4].getX()+","+masP[4].getY()+"), x6:("+masP[5].getX()+","+masP[5].getY()+")");
                return this.type+" exists! Points:  x1:("+masP[0].getX()+","+masP[0].getY()+"), x2:("+masP[1].getX()+","+masP[1].getY()+"), x3:("+masP[2].getX()+","+masP[2].getY()+"), x4:("+masP[3].getX()+","+masP[3].getY()+"), x5:("+masP[4].getX()+","+masP[4].getY()+"), x6:("+masP[5].getX()+","+masP[5].getY()+")";
        }
        return "";
    }


}
