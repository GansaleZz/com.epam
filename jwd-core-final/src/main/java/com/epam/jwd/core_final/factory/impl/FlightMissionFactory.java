package com.epam.jwd.core_final.factory.impl;

import com.epam.jwd.core_final.criteria.FlightMissionCriteria;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.Planet;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.exception.InvalidInArgsException;
import com.epam.jwd.core_final.factory.EntityFactory;


import java.time.LocalDate;
import java.util.List;

public class FlightMissionFactory implements EntityFactory<FlightMission> {
    private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(FlightMissionFactory.class);

    @Override
    public FlightMission create(Object... args) throws InvalidInArgsException {
        FlightMission flightMission = null;
        String name = null;
        long distance = 0;
        Planet to = null;
        Planet from =null ;
        try {
            if (args.length != 3) {
                logger.error("Invalid input args for creating flight mission !");
                throw new InvalidInArgsException(args);
            } else {
                for (Object i : args) {
                    if (i instanceof String) name = (String) i;
                    else if (i instanceof Planet && from == null) from = (Planet) i;
                    else if (i instanceof Planet && to == null) to = (Planet) i;
                }
            }

            if (name == null
                    || to == null
                    || from == null) {
                logger.error("Invalid input args for creating flight mission !");
                throw new InvalidInArgsException(args);
            } else {
                distance = (long) Math.sqrt((to.getPoint().getX() - from.getPoint().getX()) * (to.getPoint().getX() - from.getPoint().getX()) + (to.getPoint().getY() - from.getPoint().getY()) * (to.getPoint().getY() - from.getPoint().getY()));
                flightMission = new FlightMissionCriteria.Builder()
                        .withName(name)
                        .withFrom(from)
                        .withTo(to)
                        .withDistance(distance)
                        .build();
                logger.info("FlightMission was successfully created!");
            }
        }catch(InvalidInArgsException e){
            logger.error("Invalid input args for creating flight mission !");
            System.out.println(e.getMessage());
        }
        return flightMission;
    }
}

