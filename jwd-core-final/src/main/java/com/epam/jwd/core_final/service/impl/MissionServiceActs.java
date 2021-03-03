package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.context.Application;
import com.epam.jwd.core_final.criteria.*;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.Planet;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.exception.DuplicateException;
import com.epam.jwd.core_final.exception.InvalidStateException;
import com.epam.jwd.core_final.exception.ReadinessError;
import com.epam.jwd.core_final.service.MissionService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class MissionServiceActs implements MissionService {
    private static MissionServiceActs instance;

    private MissionServiceActs(){}

    public static MissionServiceActs getInstance(){
        if(instance == null){
            instance = new MissionServiceActs();
        }
        return instance;
    }
    @Override
    public List<FlightMission> findAllMissions() {
        return (List<FlightMission>) Application.nassaContext.retrieveBaseEntityList(FlightMission.class);
    }

    @Override
    public List<FlightMission> findAllMissionsByCriteria(Criteria<? extends FlightMission> criteria) {
        List<FlightMission> flightMissions = new ArrayList<>(Application.nassaContext.retrieveBaseEntityList(FlightMission.class));
        List<FlightMission> list = new ArrayList<>();
        if(((FlightMissionCriteria) criteria).getDistance() != 0){
            flightMissions.stream()
                    .filter(i -> i.getDistance() == ((FlightMissionCriteria) criteria).getDistance())
                    .forEach(list::add);
        }else{
                if(((FlightMissionCriteria) criteria).getMissionResult() != null){
                    flightMissions.stream()
                            .filter(i -> i.getMissionResult() == ((FlightMissionCriteria) criteria).getMissionResult())
                            .forEach(list::add);
                }
            }
        return list;
    }

    @Override
    public Optional<FlightMission> findMissionByCriteria(Criteria<? extends FlightMission> criteria) {
        List<FlightMission> flightMissions = new ArrayList<>(Application.nassaContext.retrieveBaseEntityList(FlightMission.class));
        Optional<FlightMission> flightMission = flightMissions.stream()
                .filter(i -> i.getName().equals(((FlightMissionCriteria) criteria).getName()))
                .findAny();
        return flightMission;
    }

    @Override
    public FlightMission updateFlightMissionDetails(FlightMission flightMission) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter which detail You want to update: \n" +
                "Planet (to) - 1\n" +
                "Planet (from) - 2\n" +
                "Assigned crew - 3\n" +
                "Assigned spaceship - 4\n");
        int buf;
        do{
            buf= in.nextInt();
            switch(buf){
                case 1:{
                    System.out.println("Enter name of new (To) planet: ");
                    Scanner str = new Scanner(System.in);
                    String name = str.nextLine();
                    SpacemapServiceActs spacemapServiceActs = SpacemapServiceActs.getInstance();
                    PlanetCriteria planetCriteria = new PlanetCriteria();
                    planetCriteria.setName(name);
                    Planet planet;
                    try {
                        if(!spacemapServiceActs.findPlanetByCriteria(planetCriteria).isPresent()) throw new InvalidStateException("planet");
                        else{
                            planet = spacemapServiceActs.findPlanetByCriteria(planetCriteria).get();
                            flightMission.setTo(planet);
                        }
                    }catch(InvalidStateException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                }
                case 2:{
                    System.out.println("Enter name of new (From) planet: ");
                    Scanner str = new Scanner(System.in);
                    String name = str.nextLine();
                    SpacemapServiceActs spacemapServiceActs = SpacemapServiceActs.getInstance();
                    PlanetCriteria planetCriteria = new PlanetCriteria();
                    planetCriteria.setName(name);
                    Planet planet;
                    try {
                        if(!spacemapServiceActs.findPlanetByCriteria(planetCriteria).isPresent()) throw new InvalidStateException("planet");
                        else {
                            planet = spacemapServiceActs.findPlanetByCriteria(planetCriteria).get();
                            flightMission.setFrom(planet);
                        }
                    }catch(InvalidStateException e){
                        System.out.println(e.getMessage());
                        break;
                    }
                    break;
                }
                case 3:{
                    CrewServiceActs crewServiceActs = CrewServiceActs.getInstance();
                    CrewMemberCriteria crewMemberCriteria = new CrewMemberCriteria();
                    CrewMember crewMember1;
                    CrewMember crewMember2;
                    CrewMember crewMember3;
                    CrewMember crewMember4;
                    Scanner str = new Scanner(System.in);
                    String name;
                    try {
                        System.out.println("Enter name of the first crew member: ");
                         name = str.nextLine();
                        crewMemberCriteria.setName(name);
                        if(!crewServiceActs.findCrewMemberByCriteria(crewMemberCriteria).isPresent()) throw new InvalidStateException("crew");
                        else{
                            crewMember1 = crewServiceActs.findCrewMemberByCriteria(crewMemberCriteria).get();
                            if(crewMember1.isReadyForNexMissions() == false) {
                                throw new ReadinessError(crewMember1.getName());
                            }
                        }
                        System.out.println("Enter name of the second crew member: ");
                        name = str.nextLine();
                        crewMemberCriteria.setName(name);
                        if(!crewServiceActs.findCrewMemberByCriteria(crewMemberCriteria).isPresent()) throw new InvalidStateException("crew");
                        else{
                            crewMember2 = crewServiceActs.findCrewMemberByCriteria(crewMemberCriteria).get();
                            if(crewMember2.isReadyForNexMissions() == false) {
                                throw new ReadinessError(crewMember2.getName());
                            }
                        }
                        System.out.println("Enter name of the third crew member: ");
                        name = str.nextLine();
                        crewMemberCriteria.setName(name);
                        if(!crewServiceActs.findCrewMemberByCriteria(crewMemberCriteria).isPresent()) throw new InvalidStateException("crew");
                        else{
                            crewMember3 = crewServiceActs.findCrewMemberByCriteria(crewMemberCriteria).get();
                            if(crewMember3.isReadyForNexMissions() == false) {
                                throw new ReadinessError(crewMember3.getName());
                            }
                        }
                        System.out.println("Enter name of the fourth crew member: ");
                        name = str.nextLine();
                        crewMemberCriteria.setName(name);
                        if(!crewServiceActs.findCrewMemberByCriteria(crewMemberCriteria).isPresent()) throw new InvalidStateException("crew");
                        else{
                            crewMember4 = crewServiceActs.findCrewMemberByCriteria(crewMemberCriteria).get();
                            if(crewMember4.isReadyForNexMissions() == false) {
                                throw new ReadinessError(crewMember4.getName());
                            }
                        }
                        for(CrewMember i: flightMission.getAssignedCrew()){
                            i.setReadyForNexMissions(true);
                        }
                        List<CrewMember> list = new ArrayList<>();
                        list.add(crewMember1);
                        list.add(crewMember2);
                        list.add(crewMember3);
                        list.add(crewMember4);
                        flightMission.setAssignedCrew(list);
                    }catch(InvalidStateException e){
                        System.out.println(e.getMessage());
                        break;
                    }catch (ReadinessError ex) {
                        System.out.println(ex.getMessage());
                        break;
                    }
                    break;
                }
                case 4:{
                    try {
                        System.out.println("Enter name of spaceship: ");
                        Scanner str = new Scanner(System.in);
                        String name = str.nextLine();
                        SpaceshipCriteria spaceshipCriteria = new SpaceshipCriteria();
                        spaceshipCriteria.setName(name);
                        SpaceshipServiceActs spaceshipServiceActs = SpaceshipServiceActs.getInstance();
                        if (spaceshipServiceActs.findSpaceshipByCriteria(spaceshipCriteria) == null)
                            throw new InvalidStateException("spaceship");
                        else {
                            Spaceship spaceship = spaceshipServiceActs.findSpaceshipByCriteria(spaceshipCriteria).get();
                            if (spaceship.isReadyForNextMissions() == false) throw new ReadinessError(spaceship.getName());
                            else {
                                Spaceship Currentspaceship = flightMission.getAssignedSpaceShip();
                                Currentspaceship.setReadyForNextMissions(true);
                                flightMission.setAssignedSpaceShip(spaceship);
                            }
                        }
                    }
                    catch (ReadinessError ex){
                        System.out.println(ex.getMessage());
                        break;
                    }catch (InvalidStateException s){
                        System.out.println(s.getMessage());
                        break;
                    }
                    break;
                }
                default: System.out.println("Wrong number! try again...");
            }
        }while(buf > 4 || buf < 1);
        return flightMission;
    }

    @Override
    public FlightMission createMission(FlightMission flightMission) {
        List<FlightMission> flightMissions = new ArrayList<>(Application.nassaContext.retrieveBaseEntityList(FlightMission.class));
        try {
            Optional<FlightMission> missionCheck = flightMissions.stream()
                    .filter(i -> i.getName().equals(flightMission.getName()))
                    .findAny();
            if (missionCheck.isPresent()) throw new DuplicateException(flightMission.getName(),"flightMission");
            else flightMissions.add(flightMission);

        }catch(DuplicateException e){
            System.out.println(e.getMessage());
        }
        return flightMission;
    }

    @Override
    public FlightMission startMission(FlightMission flightMission) {
        return null;
    }
}
