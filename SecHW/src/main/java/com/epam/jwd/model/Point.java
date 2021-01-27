package com.epam.jwd.model;

import org.apache.log4j.Logger;

public class Point {
    private int x,y;
    private static final Logger log = Logger.getLogger(Point.class);

    public Point(){
        x = 3;
        y = 4;
    }

    public Point(int x,int y){
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

    @Override
    public String toString(){
        log.info("Point exists! Coordinates (x: "+x+",y: "+y+")");
        return "Point exists! Coordinates (x: "+x+",y: "+y+")";
    }
}
