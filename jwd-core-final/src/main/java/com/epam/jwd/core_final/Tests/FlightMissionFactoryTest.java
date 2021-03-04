package com.epam.jwd.core_final.Tests;

import com.epam.jwd.core_final.context.impl.Cache;
import com.epam.jwd.core_final.context.impl.JsonUtils;
import com.epam.jwd.core_final.domain.*;
import com.epam.jwd.core_final.factory.impl.CrewMemberFactory;
import com.epam.jwd.core_final.factory.impl.FlightMissionFactory;
import com.epam.jwd.core_final.factory.impl.PlanetFactory;
import com.epam.jwd.core_final.factory.impl.SpaceshipFactory;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class FlightMissionFactoryTest {

    @Test
    void create() throws IOException {
        FlightMissionFactory flightMissionFactory = new FlightMissionFactory();
        String name = "Test Mission";

        PlanetFactory planetFactory = new PlanetFactory();
        Planet from = planetFactory.create(10,15,"Yupiter");
        Planet to = planetFactory.create(40,20,"Mars");
        FlightMission flightMission = flightMissionFactory.create(name,from,to);
//        Cache.addToCache(flightMission);
//        Cache.refreshCache();
        JsonUtils.parseFlightMissionJson(flightMission);
        JsonUtils.parseFlightMissionJson(flightMission);
        assertEquals(flightMission.getName(),"Test Mission");
        long distance = (long) Math.sqrt((10-40)*(10-40) + (15-20)*(15-20));
        assertEquals(flightMission.getDistance(),distance);
    }
}