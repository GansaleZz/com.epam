
package com.epam.jwd.core_final.domain;

/**
 * Expected fields:
 * <p>
 * location could be a simple class Point with 2 coordinates
 */
public class Planet extends AbstractBaseEntity{
    private Point point;

    public Planet(String name, int id) {
        super(name, id);
        point = new Point();
    }

    public final class Point{
        private int x ;
        private int y ;

        public Point(){
        }

        public void setX(int x){
            this.x = x;
        }

        public void setY(int y){
            this.y = y;
        }

        public int getX(){
            return x;
        }

        public int getY(){
            return y;
        }

        @Override
        public String toString() {
            return "{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }

    public Point getPoint(){
        return point;
    }

    @Override
    public String toString() {
        return "Planet{" +
                "point=" + point +
                ",name=" + super.getName() +
                ",id=" + super.getId() +
                '}';
    }
}