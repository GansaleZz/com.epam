package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.context.Application;
import com.epam.jwd.core_final.context.impl.Cache;
import com.epam.jwd.core_final.criteria.*;
import com.epam.jwd.core_final.domain.*;
import com.epam.jwd.core_final.exception.DuplicateException;
import com.epam.jwd.core_final.exception.InvalidStateException;
import com.epam.jwd.core_final.service.MissionService;

import java.time.LocalDateTime;
import java.util.*;

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
        List<FlightMission> flightMissions = (List<FlightMission>) Application.nassaContext.retrieveBaseEntityList(FlightMission.class);
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
        List<FlightMission> flightMissions = (List<FlightMission>) Application.nassaContext.retrieveBaseEntityList(FlightMission.class);
        Optional<FlightMission> flightMission = flightMissions.stream()
                .filter(i -> i.getName().equals(((FlightMissionCriteria) criteria).getName()))
                .findAny();
        return flightMission;
    }

    @Override
    public FlightMission updateFlightMissionDetails(FlightMission flightMission) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter which detail You want to update: \n" +
                "1 - Planet (to)\n" +
                "2 - Planet (from)");
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
                default: System.out.println("Wrong number! try again...");
            }
        }while(buf > 4 || buf < 1);
        return flightMission;
    }

    @Override
    public FlightMission createMission(FlightMission flightMission) {
        List<FlightMission> flightMissions = (List<FlightMission>) Application.nassaContext.retrieveBaseEntityList(FlightMission.class);
        try {
            Optional<FlightMission> missionCheck = flightMissions.stream()
                    .filter(i -> i.getName().equalsIgnoreCase(flightMission.getName()))
                    .findAny();
            if (missionCheck.isPresent()) throw new DuplicateException(flightMission.getName(),"flightMission");
            else {
                flightMissions.add(flightMission);
                System.out.println("Mission has completely created!");
            }
        }catch(DuplicateException e){
            System.out.println(e.getMessage());
        }
        return flightMission;
    }

    @Override
    public void startMission(FlightMission flightMission) {
            Spaceship spaceship = flightMission.getAssignedSpaceShip();
            if(MissionResult.COMPLETED == flightMission.getMissionResult() || MissionResult.FAILED == flightMission.getMissionResult()){
                System.out.println("Mission "+flightMission.getName()+" is not available!");
            }
            else
                if (spaceship == null) System.out.println("You can not start mission, while spaceship does not assigned!");
                else {
                    if (flightMission.getAssignedCrew().size() == 0)
                        System.out.println("You can not start mission, while crew does not assigned entirely!");
                    else {
                        if(flightMission.getMissionResult() != MissionResult.PLANNED) System.out.println("Mission is not available to start!");
                        else {
                            Map<Role, Short> crew = spaceship.getCrew();
                            short i, j, k, l;
                            i = (short) flightMission.getAssignedCrew().stream()
                                    .filter(crewMember -> crewMember.getRole() == Role.MISSION_SPECIALIST)
                                    .count();
                            if (i != crew.get(Role.MISSION_SPECIALIST))
                                System.out.println("You can not start mission, while crew does not assigned entirely!");
                            else {
                                j = (short) flightMission.getAssignedCrew().stream()
                                        .filter(crewMember -> crewMember.getRole() == Role.FLIGHT_ENGINEER)
                                        .count();
                                if (j != crew.get(Role.FLIGHT_ENGINEER))
                                    System.out.println("You can not start mission, while crew does not assigned entirely!");
                                else {
                                    k = (short) flightMission.getAssignedCrew().stream()
                                            .filter(crewMember -> crewMember.getRole() == Role.PILOT)
                                            .count();
                                    if (k != crew.get(Role.PILOT))
                                        System.out.println("You can not start mission, while crew does not assigned entirely!");
                                    else {
                                        l = (short) flightMission.getAssignedCrew().stream()
                                                .filter(crewMember -> crewMember.getRole() == Role.COMMANDER)
                                                .count();
                                        if (l != crew.get(Role.COMMANDER))
                                            System.out.println("You can not start mission, while crew does not assigned entirely!");
                                        else {
                                            flightMission.setStart(LocalDateTime.now());
                                            flightMission.setEnd(flightMission.getStart().plusSeconds(flightMission.getDistance()));
                                            flightMission.setMissionResult(MissionResult.IN_PROGRESS);
                                            System.out.println("Mission "+flightMission.getName()+" was completely started!");
                                            Cache.addToCache(flightMission);
                                        }
                                    }
                                }
                            }
                        }
                    }
            }
        }

    @Override
    public void stopMission(FlightMission flightMission) {
        if(flightMission.getMissionResult() == MissionResult.IN_PROGRESS){
            flightMission.setMissionResult(MissionResult.CANCELLED);
            flightMission.setEnd(LocalDateTime.now());
            Cache.removeFromCache(flightMission);
            flightMission.getAssignedSpaceShip().setReadyForNextMissions(true);
            flightMission.getAssignedCrew().stream()
                    .forEach(i -> i.setReadyForNexMissions(true));
            flightMission.setAssignedSpaceShip(null);
            flightMission.getAssignedCrew().clear();
            System.out.println("Mission completely stopped!");
        }
        else{
            System.out.println("You did not start mission" + flightMission.getName()+ "!");
        }
    }
}
