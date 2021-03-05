package com.epam.jwd.core_final.criteria;

import com.epam.jwd.core_final.domain.*;
import java.util.List;

/**
 * Should be a builder for {@link com.epam.jwd.core_final.domain.FlightMission} fields
 */

//OK
public class FlightMissionCriteria extends Criteria<FlightMission> {
    private static int id = 1;
    private long distance = 0;
    private Spaceship assignedSpaceShip;
    private List<CrewMember> assignedCrew;
    private MissionResult missionResult = null;
    private Planet from;
    private Planet to;
    private String name;


    public static class Builder extends BaseBuilder<FlightMission>{

        public FlightMissionCriteria.Builder withName(String name){
            actualClass = new FlightMission(name,id++);
            return this;
        }

        public FlightMissionCriteria.Builder withDistance(long distance){
            actualClass.setDistance(distance);
            return this;
        }

        public FlightMissionCriteria.Builder withAssignedSpaceShip(Spaceship assignedSpaceShip){
            actualClass.setAssignedSpaceShip(assignedSpaceShip);
            return this;
        }


        public FlightMissionCriteria.Builder withAssignedCrew(List<CrewMember> assignedCrew){
            actualClass.setAssignedCrew(assignedCrew);
            return this;
        }

        public FlightMissionCriteria.Builder withFrom(Planet from){
            actualClass.setFrom(from);
            return this;
        }

        public FlightMissionCriteria.Builder withTo(Planet to){
            actualClass.setTo(to);
            return this;
        }

        @Override
        protected FlightMission getActual() {
            return actualClass;
        }
    }


    public long getDistance() {
        return distance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDistance(long distance) {
        this.distance = distance;
    }

    public Spaceship getAssignedSpaceShip() {
        return assignedSpaceShip;
    }

    public void setAssignedSpaceShip(Spaceship assignedSpaceShip) {
        this.assignedSpaceShip = assignedSpaceShip;
    }

    public List<CrewMember> getAssignedCrew() {
        return assignedCrew;
    }

    public void setAssignedCrew(List<CrewMember> assignedCrew) {
        this.assignedCrew = assignedCrew;
    }

    public MissionResult getMissionResult() {
        return missionResult;
    }

    public void setMissionResult(MissionResult missionResult) {
        this.missionResult = missionResult;
    }

    public Planet getFrom() {
        return from;
    }

    public void setFrom(Planet from) {
        this.from = from;
    }

    public Planet getTo() {
        return to;
    }

    public void setTo(Planet to) {
        this.to = to;
    }
}
