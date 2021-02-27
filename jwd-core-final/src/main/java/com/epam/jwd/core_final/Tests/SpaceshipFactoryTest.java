package com.epam.jwd.core_final.Tests;

import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.factory.impl.SpaceshipFactory;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class SpaceshipFactoryTest {

    @Test
    void create() {
        SpaceshipFactory spaceshipFactory = new SpaceshipFactory();
        String name = "Test spaceship";
        long flightDistance = 12055;
        Map<Role,Short> map = new HashMap<>();
        map.put(Role.FLIGHT_ENGINEER, (short) 148);
        Spaceship spaceship = spaceshipFactory.create(name,map,flightDistance);
        assertEquals(spaceship.getCrew(),map);
        assertEquals(spaceship.getName(),"Test spaceship");
        assertEquals(spaceship.getFlightDist(),12055);
    }
}