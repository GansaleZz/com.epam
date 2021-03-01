
package com.epam.jwd.core_final.service;

import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.Planet;

import java.util.Optional;

public interface SpacemapService {

    Planet getRandomPlanet();

    Optional<Planet> findPlanetByCriteria(Criteria<? extends Planet> criteria);

    // Dijkstra ?
    int getDistanceBetweenPlanets(Planet first, Planet second);
}