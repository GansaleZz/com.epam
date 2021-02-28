package com.epam.jwd.core_final.criteria;

import com.epam.jwd.core_final.domain.Planet;

public class PlanetCriteria extends Criteria<Planet> {
    private static int id =1;

    public static class Builder extends BaseBuilder<Planet>{

        public PlanetCriteria.Builder withName(String name){
            actualClass = new Planet(name,id);
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
}
