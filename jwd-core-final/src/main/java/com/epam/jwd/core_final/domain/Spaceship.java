package com.epam.jwd.core_final.domain;

import java.util.Map;

/**
 * crew {@link java.util.Map<Role, Short>}
 * flightDistance {@link Long} - total available flight distance
 * isReadyForNextMissions {@link Boolean} - true by default. Set to false, after first failed mission
 */

// OK

public class Spaceship extends AbstractBaseEntity {
    private long flightDist;
    private boolean isReadyForNextMissions = true;
    private Map<Role,Short> crew;

    public Spaceship(String name,int id ){
        super(name,id);
    }

    public long getFlightDist() {
        return flightDist;
    }

    public Map<Role, Short> getCrew() {
        return crew;
    }

    public boolean isReadyForNextMissions() {
        return isReadyForNextMissions;
    }

    public void setFlightDist(long flightDist) {
        this.flightDist = flightDist;
    }

    public void setReadyForNextMissions(boolean readyForNextMissions) {
        isReadyForNextMissions = readyForNextMissions;
    }

    public void setCrew(Map<Role, Short> crew) {
        this.crew = crew;
    }

    @Override
    public String toString() {
        return "Spaceship{" +
                "flightDist=" + flightDist +
                ", isReadyForNextMissions=" + isReadyForNextMissions +
                ", crew=" + crew +
                ", name=" + super.getName() +
                ", id=" + super.getId() +
                '}';
    }
}
