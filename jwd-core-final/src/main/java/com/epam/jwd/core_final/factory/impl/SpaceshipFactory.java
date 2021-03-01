package com.epam.jwd.core_final.factory.impl;

import com.epam.jwd.core_final.criteria.SpaceshipCriteria;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.exception.InvalidInArgsException;
import com.epam.jwd.core_final.factory.EntityFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class SpaceshipFactory implements EntityFactory<Spaceship> {
    @Override
    public Spaceship create(Object... args) throws InvalidInArgsException{
        Spaceship spaceship = null;
        long flightDist = 0;
        Map<Role,Short> crew = null;
        String name = null;
        Boolean bool = true;
        Logger logger = LoggerFactory.getLogger(this.getClass());
        try {
            if (args.length < 3 || args.length > 4) {
                logger.error("Invalid input args for creating spaceship!");
                throw new InvalidInArgsException(args);
            } else {
                for (Object i : args) {
                    if (i instanceof Map) crew = (Map<Role, Short>) i;
                    else if (i instanceof String) name = (String) i;
                    else if (i instanceof Integer) flightDist = ((Integer) i).longValue();
                    else if (i instanceof Long) flightDist = (Long) i;
                    else if (i instanceof Boolean) bool = (Boolean) i;
                }
            }

            if (crew == null
                    || flightDist == 0
                    || name == null) {
                logger.error("Invalid input args for creating spaceship!");
                throw new InvalidInArgsException(args);
            } else {
                spaceship = new SpaceshipCriteria.Builder()
                        .withName(name)
                        .withSetCrew(crew)
                        .withFlightDist(flightDist)
                        .isReadyForNextMissions(bool)
                        .build();
                logger.info("Spaceship was successfully created!");
            }
        }catch(InvalidInArgsException e){
            logger.error("Invalid input args for creating spaceship!");
            System.out.println(e.getMessage());
        }
        return spaceship;
    }
}
