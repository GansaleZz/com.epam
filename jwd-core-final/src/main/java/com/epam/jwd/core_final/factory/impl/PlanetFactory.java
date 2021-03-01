package com.epam.jwd.core_final.factory.impl;

import com.epam.jwd.core_final.criteria.PlanetCriteria;
import com.epam.jwd.core_final.domain.Planet;
import com.epam.jwd.core_final.exception.InvalidInArgsException;
import com.epam.jwd.core_final.factory.EntityFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class PlanetFactory implements EntityFactory<Planet>{
    @Override
    public Planet create(Object... args) throws InvalidInArgsException {
        Planet planet = null;
        int x = 0;
        int y = 0;
        String name = null;
        Logger logger = LoggerFactory.getLogger(this.getClass());
        try {
            if (args.length != 3) {
                logger.error("Invalid input args for creating planet!");
                throw new InvalidInArgsException(args);
            } else {
                for (Object i : args) {
                    if (i instanceof Integer && x == 0) x = (int) i;
                    else if (i instanceof Integer && y == 0) y = (int) i;
                    else if (i instanceof String) name = (String) i;
                }
            }
            if (name == null || x == 0 || y == 0) {
                logger.error("Invalid input args for creating planet!");
                throw new InvalidInArgsException(args);
            } else {
                planet = new PlanetCriteria.Builder()
                        .withName(name)
                        .withX(x)
                        .withY(y)
                        .build();
                logger.info("Planet was successfully created!");
            }
        }catch (InvalidInArgsException e){
            logger.error("Invalid input args for creating planet!");
            System.out.println(e.getMessage());
        }
        return planet;
    }
}
