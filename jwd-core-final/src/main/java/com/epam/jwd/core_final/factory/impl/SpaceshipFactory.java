package com.epam.jwd.core_final.factory.impl;

import com.epam.jwd.core_final.criteria.SpaceshipCriteria;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.exception.InvalidInArgsException;
import com.epam.jwd.core_final.exception.UnknownEntityException;
import com.epam.jwd.core_final.factory.EntityFactory;

import java.util.Map;

public class SpaceshipFactory implements EntityFactory<Spaceship> {
    @Override
    public Spaceship create(Object... args) throws InvalidInArgsException{
        Spaceship spaceship;
        long flightDist = 0;
        Map<Role,Short> crew = null;
        String name = null;

        if(args.length != 3) throw new InvalidInArgsException(args);
            else{
                for(Object i : args){
                    if(i instanceof Map) crew = (Map<Role, Short>) i;
                        else if(i instanceof String) name = (String) i;
                            else if(i instanceof Integer) flightDist = ((Integer) i).longValue();
                                else if(i instanceof Long) flightDist = (Long) i;
                }
            }



        if(crew == null
        || flightDist == 0
        || name == null) {
            throw new InvalidInArgsException(args);
        }else try{
            spaceship = new SpaceshipCriteria(name)
                    .withSetCrew(crew)
                    .withFlightDist(flightDist)
                    .build();
        }catch(InvalidInArgsException e){
            throw new InvalidInArgsException(args);
        }
        return spaceship;
    }
}
