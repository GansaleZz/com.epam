package com.epam.jwd.core_final.Tests;

import com.epam.jwd.core_final.domain.Planet;
import com.epam.jwd.core_final.factory.impl.PlanetFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlanetFactoryTest {

    @Test
    void create() {
        PlanetFactory planetFactory = new PlanetFactory();
        Planet planet = planetFactory.create(102,101,"test Planet");
        assertEquals(planet.getPoint().getX(),102);
        assertEquals(planet.getPoint().getY(),101);
        assertEquals(planet.getName(),"test Planet");
        assertEquals(planet.getId(),1);
    }
}