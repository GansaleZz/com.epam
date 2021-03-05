package com.epam.jwd.core_final.Tests;

import com.epam.jwd.core_final.context.Application;
import com.epam.jwd.core_final.domain.*;
import com.epam.jwd.core_final.exception.InvalidStateException;
import com.epam.jwd.core_final.factory.impl.CrewMemberFactory;
import com.epam.jwd.core_final.factory.impl.FlightMissionFactory;
import com.epam.jwd.core_final.factory.impl.PlanetFactory;
import com.epam.jwd.core_final.factory.impl.SpaceshipFactory;
import com.epam.jwd.core_final.service.impl.CrewServiceActs;
import com.epam.jwd.core_final.service.impl.MissionServiceActs;
import com.epam.jwd.core_final.service.impl.SpaceshipServiceActs;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CrewServiceActsTest {

    @Test
    void assignCrewMemberOnMission() throws InvalidStateException {
        FlightMissionFactory flightMissionFactory = new FlightMissionFactory();
        SpaceshipFactory spaceshipFactory = new SpaceshipFactory();
        CrewMemberFactory crewMemberFactory = new CrewMemberFactory();
        PlanetFactory planetFactory = new PlanetFactory();
        SpaceshipServiceActs spaceshipServiceActs = SpaceshipServiceActs.getInstance();
        CrewServiceActs crewServiceActs = CrewServiceActs.getInstance();

        Planet to = planetFactory.create("Mars",10,15);
        Planet from = planetFactory.create("Yupiter",20,40);
        CrewMember crewMember = crewMemberFactory.create(Rank.FIRST_OFFICER, Role.PILOT,"First Crew");
        Map<Role,Short> crew = new HashMap<>();
        crew.put(Role.COMMANDER, (short) 1);
        crew.put(Role.PILOT,(short) 1);
        crew.put(Role.FLIGHT_ENGINEER,(short) 1);
        crew.put(Role.MISSION_SPECIALIST,(short) 1);
        Spaceship spaceship = spaceshipFactory.create("ship",crew,1234);
        FlightMission flightMission = flightMissionFactory.create(to,from,"Test");
        List<FlightMission> flightMissions = (List<FlightMission>) Application.nassaContext.retrieveBaseEntityList(FlightMission.class);
        flightMissions.add(flightMission);
        crewServiceActs.assignCrewMemberOnMission(crewMember);
        spaceshipServiceActs.assignSpaceshipOnMission(spaceship);
        crewServiceActs.assignCrewMemberOnMission(crewMember);
        flightMission.setAssignedSpaceShip(null);
        System.out.println(flightMissions);
        System.out.println(spaceship);
        spaceshipServiceActs.assignSpaceshipOnMission(spaceship);
        System.out.println(flightMissions);
//        CrewMember crewMember1 = crewMemberFactory.create(Rank.FIRST_OFFICER, Role.PILOT,"First Crew1");
//        crewServiceActs.assignCrewMemberOnMission(crewMember1);
    }

}
