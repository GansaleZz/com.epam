package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.context.Application;
import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.criteria.PlanetCriteria;
import com.epam.jwd.core_final.domain.Planet;
import com.epam.jwd.core_final.service.SpacemapService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SpacemapServiceActs implements SpacemapService {
    private static SpacemapServiceActs instance;

    private SpacemapServiceActs(){}

    public static SpacemapServiceActs getInstance(){
        if(instance == null){
            instance = new SpacemapServiceActs();
        }
        return instance;
    }

    @Override
    public Planet getRandomPlanet() {
        return null;
    }

    @Override
    public Optional<Planet> findPlanetByCriteria(Criteria<? extends Planet> criteria) {
        List<Planet> spacemap = new ArrayList<>(Application.nassaContext.retrieveBaseEntityList(Planet.class));
        Optional<Planet> planet = spacemap.stream()
                .filter(i -> i.getName().equals(((PlanetCriteria) criteria).getName()))
                .findAny();
        return planet;
    }

    @Override
    public int getDistanceBetweenPlanets(Planet first, Planet second) {
        return 0;
    }
}
