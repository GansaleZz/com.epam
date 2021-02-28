package com.epam.jwd.core_final.domain;

import java.time.LocalDate;
import java.util.List;

/**
 * Expected fields:
 * <p>
 * missions name {@link String}
 * start date {@link java.time.LocalDate}
 * end date {@link java.time.LocalDate}
 * distance {@link Long} - missions distance
 * assignedSpaceShift {@link Spaceship} - not defined by default
 * assignedCrew {@link java.util.List<CrewMember>} - list of missions members based on ship capacity - not defined by default
 * missionResult {@link MissionResult}
 * from {@link Planet}
 * to {@link Planet}
 */

//OK

public class FlightMission extends AbstractBaseEntity {
    private LocalDate start;
    private LocalDate end;
    private long distance;
    private Spaceship assignedSpaceShip;
    private List<CrewMember> assignedCrew;
    private MissionResult missionResult;
    private Planet from ;
    private Planet to;
    public FlightMission(String name,int id ) {
        super(name,id);
    }


    public void setStart(LocalDate start) {
        this.start = start;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }

    public void setDistance(long distance) {
        this.distance = distance;
    }

    public void setAssignedSpaceShip(Spaceship assignedSpaceShip) {
        this.assignedSpaceShip = assignedSpaceShip;
    }

    public void setAssignedCrew(List<CrewMember> assignedCrew) {
        this.assignedCrew = assignedCrew;
    }

    public void setFrom(Planet planet){
        from = planet;
    }

    public void setTo(Planet planet){
        to = planet;
    }

    public void setMissionResult(MissionResult missionResult) {
        this.missionResult = missionResult;
    }

    public LocalDate getStart() {
        return start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public long getDistance() {
        return distance;
    }

    public Spaceship getAssignedSpaceShip() {
        return assignedSpaceShip;
    }

    public List<CrewMember> getAssignedCrew() {
        return assignedCrew;
    }

    public MissionResult getMissionResult() {
        return missionResult;
    }

    @Override
    public String toString() {
        return "FlightMission{" +
                ", start=" + start +
                ", end=" + end +
                ", distance=" + distance +
                ", assignedSpaceShip=" + assignedSpaceShip +
                ", assignedCrew=" + assignedCrew +
                ", missionResult=" + missionResult +
                '}';
    }
}
