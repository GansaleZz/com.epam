package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.context.Application;
import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.criteria.PlanetCriteria;
import com.epam.jwd.core_final.domain.Planet;
import com.epam.jwd.core_final.service.SpacemapService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

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
        List<Planet> spacemap = (List<Planet>) Application.nassaContext.retrieveBaseEntityList(Planet.class);
        Random random = new Random();
        int rand = random.nextInt(spacemap.size());
        return spacemap.get(rand);
    }

    @Override
    public Optional<Planet> findPlanetByCriteria(Criteria<? extends Planet> criteria) {
        List<Planet> spacemap = (List<Planet>) Application.nassaContext.retrieveBaseEntityList(Planet.class);
        Optional<Planet> planet = spacemap.stream()
                .filter(i -> i.getName().equals(((PlanetCriteria) criteria).getName()))
                .findAny();
        return planet;
    }

    @Override
    public int getDistanceBetweenPlanets(Planet first, Planet second) {
        return (int) Math.sqrt((first.getPoint().getX() - second.getPoint().getX()) * (first.getPoint().getX() - second.getPoint().getX()) + (first.getPoint().getY() - second.getPoint().getY()) * (first.getPoint().getY() - second.getPoint().getY()));
    }
}
