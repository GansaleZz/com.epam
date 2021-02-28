package com.epam.jwd.core_final.factory.impl;

import com.epam.jwd.core_final.criteria.FlightMissionCriteria;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.Planet;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.exception.InvalidInArgsException;
import com.epam.jwd.core_final.factory.EntityFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.List;

public class FlightMissionFactory implements EntityFactory<FlightMission> {

    @Override
    public FlightMission create(Object... args) throws InvalidInArgsException {
        FlightMission flightMission = null;
        String name = null;
        LocalDate start = null;
        LocalDate end = null;
        long distance = 0;
        Spaceship assignedSpaceShip = null;
        List<CrewMember> assignedCrew = null ;
        Planet to = null;
        Planet from = null;
        Logger logger = LoggerFactory.getLogger(this.getClass());
        if (args.length != 7) {
            logger.error("Invalid input args : " +args);
            throw new InvalidInArgsException(args);
        }
            else{
                for(Object i:args){
                    if (i instanceof String)  name = (String) i;
                        else if(i instanceof Planet && from == null) from = (Planet) i;
                            else if(i instanceof Planet && to ==null) to = (Planet) i;
                                else if(i instanceof LocalDate && start == null) start = (LocalDate) i;
                                     else if(i instanceof LocalDate && end == null) end = (LocalDate) i;
                                        else if(i instanceof Spaceship) assignedSpaceShip = (Spaceship) i;
                                            else if(i instanceof List) assignedCrew = (List<CrewMember>) i;
                }
            }


        if(name == null
                || start == null
                || end == null
                || assignedCrew == null
                || assignedSpaceShip == null
                || to == null
                || from == null){
            logger.error("Invalid input args : " +args);
            throw new InvalidInArgsException(args);
        }else try{
            distance = (long) Math.sqrt((to.getPoint().getX() - from.getPoint().getX())*(to.getPoint().getX() - from.getPoint().getX()) +(to.getPoint().getY() - from.getPoint().getY())*(to.getPoint().getY() - from.getPoint().getY()));
            if (start.isAfter(end)) {
                LocalDate temp = start;
                start = end;
                end = temp;
            }
            flightMission = new FlightMissionCriteria.Builder()
                    .withName(name)
                    .withAssignedCrew(assignedCrew)
                    .withEnd(end)
                    .withStart(start)
                    .withDistance(distance)
                    .withAssignedSpaceShip(assignedSpaceShip)
                    .withFrom(from)
                    .withTo(to)
                    .build();
            logger.info("FlighMission was completely created!");
        }catch(InvalidInArgsException e){
            logger.error("Invalid input args : " +args);
            throw new InvalidInArgsException(args);
        }
        return flightMission;
    }
}

