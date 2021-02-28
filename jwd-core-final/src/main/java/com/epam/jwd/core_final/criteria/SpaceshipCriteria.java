package com.epam.jwd.core_final.criteria;

import com.epam.jwd.core_final.domain.*;

import java.util.Map;

/**
 * Should be a builder for {@link Spaceship} fields
 */

//OK
public class SpaceshipCriteria extends Criteria<Spaceship> {
    private static int id = 1;

    public static class Builder extends BaseBuilder<Spaceship>{

        public SpaceshipCriteria.Builder withName(String name){
            actualClass = new Spaceship(name,id);
            return this;
        }

        public SpaceshipCriteria.Builder withSetCrew(Map<Role, Short> crew){
            actualClass.setCrew(crew);
            return this;
        }

        public SpaceshipCriteria.Builder withFlightDist(long flightDist){
            actualClass.setFlightDist(flightDist);
            return this;
        }

        @Override
        protected Spaceship getActual() {
            return actualClass;
        }
    }


}