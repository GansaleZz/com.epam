package com.epam.jwd.model;

import org.apache.log4j.Logger;

public class Point extends Figure {
    private int x,y;
    private static final Logger log = Logger.getLogger(Point.class);
    private Point[] masP = new Point[1];


    protected Point(int x,int y){
        this.x = x;
        this.y = y;
    }

    public void setMasP(Point[] masP){
        super.setMasP(masP);
        this.masP = masP;
    }


    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public Point[] masP(){
        return this.masP;
    }

    public void setX(int x){
        this.x = x;
    }

    public void setY(int y){
        this.y = y;
    }

    public double distance(Point a){
        return Math.sqrt((this.x - a.getX())*(this.x -a.getX()) + (this.y - a.getY())*(this.y-a.getY()));
    }

    @Override
    public String toString(){
        log.info("POINT exists! Coordinates (x: "+x+",y: "+y+")");
        return "POINT exists! Coordinates (x: "+x+",y: "+y+")";
    }
}
