package com.epam.jwd.core_final.Tests;

import com.epam.jwd.core_final.domain.*;
import com.epam.jwd.core_final.factory.impl.CrewMemberFactory;
import com.epam.jwd.core_final.factory.impl.FlightMissionFactory;
import com.epam.jwd.core_final.factory.impl.PlanetFactory;
import com.epam.jwd.core_final.factory.impl.SpaceshipFactory;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class FlightMissionFactoryTest {

    @Test
    void create() {
        FlightMissionFactory flightMissionFactory = new FlightMissionFactory();
        String name = "Test Mission";
        long distance ;
        SpaceshipFactory spaceshipFactory = new SpaceshipFactory();
        Map<Role,Short> map = new HashMap<>();
        map.put(Role.PILOT,(short) 195);
        Spaceship spaceship = spaceshipFactory.create(map,"Test SpaceSHHIIIP",1901);
        List<CrewMember> list = new ArrayList<>();
        CrewMemberFactory crewMemberFactory = new CrewMemberFactory();
        CrewMember crewMember = crewMemberFactory.create(Rank.TRAINEE,Role.PILOT,"Andrey");
        list.add(crewMember);

        PlanetFactory planetFactory = new PlanetFactory();
        Planet from = planetFactory.create(10,15,"Yupiter");
        Planet to = planetFactory.create(40,20,"Mars");
        FlightMission flightMission = flightMissionFactory.create(spaceship,list,name,from,to);
        assertEquals(flightMission.getName(),"Test Mission");
        distance = (long) Math.sqrt((10-40)*(10-40) + (15-20)*(15-20));
        assertEquals(flightMission.getDistance(),distance);
    }
}