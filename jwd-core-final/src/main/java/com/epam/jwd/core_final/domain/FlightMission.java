package com.epam.jwd.core_final.domain;


import java.time.LocalDateTime;
import java.util.ArrayList;
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
    private LocalDateTime start;
    private LocalDateTime end;
    private long distance = 0;
    private Spaceship assignedSpaceShip;
    private List<CrewMember> assignedCrew = new ArrayList<>();
    private MissionResult missionResult;
    private Planet from ;
    private Planet to;
    public FlightMission(String name,int id ) {
        super(name,id);
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public long getDistance() {
        return distance;
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

    public void addToCrew(CrewMember crewMember){
        assignedCrew.add(crewMember);
    }

    public void setFrom(Planet from) {
        this.from = from;
        if (distance != 0) distance = (long) Math.sqrt((to.getPoint().getX() - from.getPoint().getX()) * (to.getPoint().getX() - from.getPoint().getX()) + (to.getPoint().getY() - from.getPoint().getY()) * (to.getPoint().getY() - from.getPoint().getY()));
    }

    public Planet getTo() {
        return to;
    }

    public void setTo(Planet to) {
        this.to = to;
        if (distance != 0) distance = (long) Math.sqrt((to.getPoint().getX() - from.getPoint().getX()) * (to.getPoint().getX() - from.getPoint().getX()) + (to.getPoint().getY() - from.getPoint().getY()) * (to.getPoint().getY() - from.getPoint().getY()));
    }

    @Override
    public String toString() {
        return "FlightMission{" +
                " start=" + start +
                ", end=" + end +
                ", distance=" + distance +
                ", assignedSpaceShip=" + assignedSpaceShip +
                ", assignedCrew=" + assignedCrew +
                ", missionResult=" + missionResult +
                ", name=" + super.getName() +
                ", id=" + super.getId() +
                '}';
    }
}
