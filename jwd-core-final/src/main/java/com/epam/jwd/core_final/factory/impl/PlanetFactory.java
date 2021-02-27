package com.epam.jwd.core_final.factory.impl;

import com.epam.jwd.core_final.criteria.PlanetCriteria;
import com.epam.jwd.core_final.domain.Planet;
import com.epam.jwd.core_final.exception.InvalidInArgsException;
import com.epam.jwd.core_final.factory.EntityFactory;


public class PlanetFactory implements EntityFactory<Planet>{
    @Override
    public Planet create(Object... args) throws InvalidInArgsException {
        Planet planet;
        int x = -1;
        int y = -1;
        String name = null;

        if(args.length != 3) throw new InvalidInArgsException(args);
        else{
            for(Object i : args){
                    if(i instanceof Integer && x == -1) x = (int) i;
                        else if(i instanceof  Integer && y == -1) y = (int) i;
                            else if(i instanceof  String) name = (String) i;
            }
        }
        if(name == null || x == -1 || y == -1) throw new InvalidInArgsException(args);
        else try{
            planet = new PlanetCriteria(name)
                    .withX(x)
                    .withY(y)
                    .build();
        }catch (InvalidInArgsException e){
            throw new InvalidInArgsException(args);
        }
        return planet;
    }
}
