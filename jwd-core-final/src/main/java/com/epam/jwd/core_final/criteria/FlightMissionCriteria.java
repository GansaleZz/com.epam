package com.epam.jwd.core_final.criteria;

import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.MissionResult;
import com.epam.jwd.core_final.domain.Spaceship;

import java.time.LocalDate;
import java.util.List;

/**
 * Should be a builder for {@link com.epam.jwd.core_final.domain.FlightMission} fields
 */

//OK
public class FlightMissionCriteria extends Criteria<FlightMission> {
    private FlightMission flightMission;
    private static int id = 1;

    public FlightMissionCriteria(String name){
        flightMission = new FlightMission(name,id++);
    }


    public FlightMissionCriteria withStart(LocalDate start){
        flightMission.setStart(start);
        return this;
    }

    public FlightMissionCriteria withEnd(LocalDate end){
        flightMission.setEnd(end);
        return this;
    }

    public FlightMissionCriteria withDistance(long distance){
        flightMission.setDistance(distance);
        return this;
    }

    public FlightMissionCriteria withAssignedSpaceShip(Spaceship assignedSpaceShip){
        flightMission.setAssignedSpaceShip(assignedSpaceShip);
        return this;
    }

    public FlightMissionCriteria withAssignedCrew(List<CrewMember> assignedCrew){
        flightMission.setAssignedCrew(assignedCrew);
        return this;
    }


    public FlightMission build(){
        return flightMission;
    }
}
