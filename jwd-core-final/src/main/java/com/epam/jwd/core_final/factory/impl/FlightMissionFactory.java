package com.epam.jwd.core_final.factory.impl;

import com.epam.jwd.core_final.criteria.FlightMissionCriteria;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.exception.InvalidInArgsException;
import com.epam.jwd.core_final.factory.EntityFactory;

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

        if (args.length != 6 ) throw new InvalidInArgsException(args);
            else{
                for(Object i:args){
                    if (i instanceof String)  name = (String) i;
                        else if(i instanceof LocalDate && start == null) start = (LocalDate) i;
                             else if(i instanceof LocalDate && end == null) end = (LocalDate) i;
                                else if(i instanceof Spaceship) assignedSpaceShip = (Spaceship) i;
                                    else if(i instanceof List) assignedCrew = (List<CrewMember>) i;
                                        else if(i instanceof Long) distance = (long) i;
                                            else if(i instanceof Integer) distance = ((Integer) i).longValue();
                }
            }


        if(name == null
                || start == null
                || end == null
                || distance == 0
                || assignedCrew == null
                || assignedSpaceShip == null){
            throw new InvalidInArgsException(args);
        }else try{
            if (start.isAfter(end)) {
                LocalDate temp = start;
                start = end;
                end = temp;
            }
            flightMission = new FlightMissionCriteria(name)
                    .withAssignedCrew(assignedCrew)
                    .withEnd(end)
                    .withStart(start)
                    .withDistance(distance)
                    .withAssignedSpaceShip(assignedSpaceShip)
                    .build();
        }catch(InvalidInArgsException e){
            throw new InvalidInArgsException(args);
        }
        return flightMission;
    }
}

