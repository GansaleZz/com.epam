package com.epam.jwd.core_final.Tests;

import com.epam.jwd.core_final.domain.*;
import com.epam.jwd.core_final.factory.impl.CrewMemberFactory;
import com.epam.jwd.core_final.factory.impl.FlightMissionFactory;
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
        LocalDate start = LocalDate.ofYearDay(2042,135);
        LocalDate end = LocalDate.ofYearDay(2022,134);
        String name = "Test Mission";
        long distance = 141;
        SpaceshipFactory spaceshipFactory = new SpaceshipFactory();
        Map<Role,Short> map = new HashMap<>();
        map.put(Role.PILOT,(short) 195);
        Spaceship spaceship = spaceshipFactory.create(map,"Test SpaceSHHIIIP",1901);
        List<CrewMember> list = new ArrayList<>();
        CrewMemberFactory crewMemberFactory = new CrewMemberFactory();
        CrewMember crewMember = crewMemberFactory.create(Rank.TRAINEE,Role.PILOT,"Andrey");
        list.add(crewMember);
        FlightMission flightMission = flightMissionFactory.create(spaceship,list,start,end,name,distance);
        assertEquals(flightMission.getName(),"Test Mission");
        if(!start.isAfter(end)) {
            assertEquals(flightMission.getEnd(), end);
            assertEquals(flightMission.getStart(), start);
        }else{
            assertEquals(flightMission.getEnd(), start);
            assertEquals(flightMission.getStart(), end);
        }
        assertEquals(flightMission.getDistance(),distance);
    }
}