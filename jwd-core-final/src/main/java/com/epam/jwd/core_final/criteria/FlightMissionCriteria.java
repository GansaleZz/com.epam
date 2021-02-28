package com.epam.jwd.core_final.criteria;

import com.epam.jwd.core_final.domain.*;

import java.time.LocalDate;
import java.util.List;

/**
 * Should be a builder for {@link com.epam.jwd.core_final.domain.FlightMission} fields
 */

//OK
public class FlightMissionCriteria extends Criteria<FlightMission> {
    private static int id = 1;

    public static class Builder extends BaseBuilder<FlightMission>{

        public FlightMissionCriteria.Builder withName(String name){
            actualClass = new FlightMission(name,id);
            return this;
        }

        public FlightMissionCriteria.Builder withStart(LocalDate start){
            actualClass.setStart(start);
            return this;
        }

        public FlightMissionCriteria.Builder withEnd(LocalDate end){
            actualClass.setEnd(end);
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

        @Override
        protected FlightMission getActual() {
            return actualClass;
        }
    }
}
