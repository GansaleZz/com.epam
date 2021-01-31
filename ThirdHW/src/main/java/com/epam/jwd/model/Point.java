package com.epam.jwd.model;

import org.apache.log4j.Logger;
import java.lang.Math;

public class Point {
    private int x,y;
    private static final Logger log = Logger.getLogger(Point.class);

    protected Point(){
        x = 3;
        y = 4;
    }

    protected Point(int x,int y){
        this.x = x;
        this.y = y;
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
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
