package com.epam.jwd.core_final.criteria;

import com.epam.jwd.core_final.domain.Planet;

public class PlanetCriteria extends Criteria<Planet> {
    private static int id =1;
    private int x;
    private int y;
    private String name;

    public static class Builder extends BaseBuilder<Planet>{

        public PlanetCriteria.Builder withName(String name){
            actualClass = new Planet(name,id++);
            return this;
        }

        public PlanetCriteria.Builder withX(int x ){
            actualClass.getPoint().setX(x);
            return this;
        }

        public PlanetCriteria.Builder withY(int y){
            actualClass.getPoint().setY(y);
            return this;
        }

        @Override
        protected Planet getActual() {
            return actualClass;
        }
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
