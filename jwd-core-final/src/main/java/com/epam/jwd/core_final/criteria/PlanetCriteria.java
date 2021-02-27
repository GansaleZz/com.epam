package com.epam.jwd.core_final.criteria;

import com.epam.jwd.core_final.domain.Planet;

public class PlanetCriteria extends Criteria<Planet> {
    private Planet planet;
    private static int id =1;

    public PlanetCriteria(String name){
        planet = new Planet(name,id++);
    }

    public PlanetCriteria withX(int x ){
        planet.getPoint().setX(x);
        return this;
    }

    public  PlanetCriteria withY(int y){
        planet.getPoint().setY(y);
        return this;
    }

    public Planet build(){
        return planet;
    }
}
