package com.epam.jwd.core_final.criteria;

import com.epam.jwd.core_final.domain.*;

import java.util.Map;

/**
 * Should be a builder for {@link Spaceship} fields
 */

//OK
public class SpaceshipCriteria extends Criteria<Spaceship> {
    private static int id = 1;
    private String name;
    private Map<Role, Short> crew;
    private boolean isReadyForNextMissions = Boolean.parseBoolean(null);
    private long flightDistance =0;


    public static class Builder extends BaseBuilder<Spaceship>{

        public SpaceshipCriteria.Builder withName(String name){
            actualClass = new Spaceship(name,id++);
            return this;
        }

        public SpaceshipCriteria.Builder isReadyForNextMissions(boolean isReadyForNextMissions){
            actualClass.setReadyForNextMissions(isReadyForNextMissions);
            return this;
        }

        public SpaceshipCriteria.Builder withSetCrew(Map<Role, Short> crew){
            actualClass.setCrew(crew);
            return this;
        }

        public SpaceshipCriteria.Builder withFlightDist(long flightDist){
            actualClass.setFlightDist(flightDist);
            return this;
        }

        @Override
        protected Spaceship getActual() {
            return actualClass;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<Role, Short> getCrew() {
        return crew;
    }

    public void setCrew(Map<Role, Short> crew) {
        this.crew = crew;
    }

    public boolean isReadyForNextMissions() {
        return isReadyForNextMissions;
    }

    public void setReadyForNextMissions(boolean readyForNextMissions) {
        isReadyForNextMissions = readyForNextMissions;
    }

    public long getFlightDistance() {
        return flightDistance;
    }

    public void setFlightDistance(long flightDistance) {
        this.flightDistance = flightDistance;
    }


}