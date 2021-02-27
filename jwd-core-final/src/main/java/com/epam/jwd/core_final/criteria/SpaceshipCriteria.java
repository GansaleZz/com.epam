package com.epam.jwd.core_final.criteria;

import com.epam.jwd.core_final.domain.BaseEntity;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.domain.Spaceship;

import java.util.Map;

/**
 * Should be a builder for {@link Spaceship} fields
 */

//OK
public class SpaceshipCriteria extends Criteria<Spaceship> {
    private Spaceship spaceship;
    private static int id = 1;


    public SpaceshipCriteria(String name){
        spaceship = new Spaceship(name,id);
    }

    public SpaceshipCriteria withFlightDist(long flightDist){
        spaceship.setFlightDist(flightDist);
        return this;
    }

    public SpaceshipCriteria withSetCrew(Map<Role, Short> crew){
        spaceship.setCrew(crew);
        return this;
    }

    public Spaceship build(){
        return spaceship;
    }

}