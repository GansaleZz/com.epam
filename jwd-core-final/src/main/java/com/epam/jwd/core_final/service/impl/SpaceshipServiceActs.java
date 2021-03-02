package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.context.Application;
import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.criteria.SpaceshipCriteria;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.service.SpaceshipService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SpaceshipServiceActs implements SpaceshipService {
    private static SpaceshipServiceActs instance;

    private SpaceshipServiceActs(){}

    public static SpaceshipServiceActs getInstance(){
        if(instance == null){
            instance= new SpaceshipServiceActs();
        }
        return instance;
    }

    @Override
    public List<Spaceship> findAllSpaceships() {
        return (List<Spaceship>) Application.nassaContext.retrieveBaseEntityList(Spaceship.class);
    }

    @Override
    public List<Spaceship> findAllSpaceshipsByCriteria(Criteria<? extends Spaceship> criteria) {
        List<Spaceship> spaceships = new ArrayList<>(Application.nassaContext.retrieveBaseEntityList(Spaceship.class));
        List<Spaceship> list = new ArrayList<>();
        if(((SpaceshipCriteria) criteria).getFlightDistance() != 0){
            spaceships.stream()
                    .filter(i -> i.getFlightDist() == ((SpaceshipCriteria) criteria).getFlightDistance())
                    .forEach(i -> list.add(i));
        }else{
            if(((SpaceshipCriteria) criteria).isReadyForNextMissions() != Boolean.parseBoolean(null)){
                spaceships.stream()
                        .filter(i -> i.isReadyForNextMissions() == ((SpaceshipCriteria) criteria).isReadyForNextMissions())
                        .forEach(i -> list.add(i));
            }
        }
        return list;
    }

    @Override
    public Optional<Spaceship> findSpaceshipByCriteria(Criteria<? extends Spaceship> criteria) {
        List<Spaceship> spaceships = new ArrayList<>(Application.nassaContext.retrieveBaseEntityList(Spaceship.class));
        Optional<Spaceship> spaceship = spaceships
                .stream()
                .filter(i -> i.getName().equals(((SpaceshipCriteria) criteria).getName()))
                .findAny();
        return spaceship;
    }

    @Override
    public Spaceship updateSpaceshipDetails(Spaceship spaceship) {
        return null;
    }

    @Override
    public void assignSpaceshipOnMission(Spaceship crewMember) throws RuntimeException {

    }

    @Override
    public Spaceship createSpaceship(Spaceship spaceship) throws RuntimeException {
        return null;
    }
}
