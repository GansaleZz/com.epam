package com.epam.jwd.core_final.context;

import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.criteria.CrewMemberCriteria;
import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.criteria.FlightMissionCriteria;
import com.epam.jwd.core_final.criteria.PlanetCriteria;
import com.epam.jwd.core_final.domain.*;
import com.epam.jwd.core_final.exception.InvalidInArgsException;
import com.epam.jwd.core_final.exception.InvalidStateException;
import com.epam.jwd.core_final.factory.impl.CrewMemberFactory;
import com.epam.jwd.core_final.factory.impl.FlightMissionFactory;
import com.epam.jwd.core_final.factory.impl.PlanetFactory;
import com.epam.jwd.core_final.factory.impl.SpaceshipFactory;
import com.epam.jwd.core_final.service.impl.CrewServiceActs;
import com.epam.jwd.core_final.service.impl.MissionServiceActs;
import com.epam.jwd.core_final.service.impl.SpacemapServiceActs;
import com.epam.jwd.core_final.service.impl.SpaceshipServiceActs;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

// todo replace Object with your own types
@FunctionalInterface
public interface ApplicationMenu {

    ApplicationContext getApplicationContext();

    CrewServiceActs crewServiceActs = new CrewServiceActs();
    SpacemapServiceActs spacemapServiceActs = new SpacemapServiceActs();
    SpaceshipServiceActs spaceshipServiceActs = new SpaceshipServiceActs();
    MissionServiceActs missionServiceActs = new MissionServiceActs();


    default Object printAvailableOptions() throws InvalidStateException {
        System.out.println("Find all crew members - 1 \n" +
                "Find all crew members by criteria - 2\n" +
                "Find crew member by criteria - 3\n" +
                "Update crew member details - 4\n" +
                "Assign crew member on mission - 5\n" +
                "Create crew member - 6\n" +
                "Find all missions - 7\n" +
                "Find all missions by criteria - 8\n" +
                "Find mission by criteria - 9\n" +
                "Update flight mission details - 10\n" +
                "Create mission - 11\n" +
                "Get random planet - 12\n" +
                "Get distance between planets - 13\n" +
                "Find planet by criteria - 14\n" +
                "Find all spaceships - 15\n" +
                "Find all spaceships by criteria - 16\n" +
                "Find spaceship by criteria - 17\n" +
                "Update spaceship details - 18\n" +
                "Assign spaceship on mission - 19\n" +
                "Create spaceship - 20\n" +
                "Start mission - 21\n" +
                "Exit - 22");
        Scanner in = new Scanner(System.in);
        int buf;
        do {
            buf = in.nextInt();
            switch (buf){
                case 1: handleUserInput(crewServiceActs.findAllCrewMembers());
                case 2: {
                    System.out.println("Enter criteria : \n" +
                            "Rank - 1 \n" +
                            "Role - 2 \n" +
                            "Name - 3 \n" +
                            "Ready for next missions - 4");
                            do{
                                buf = in.nextInt();
                                Rank rank;
                                Role role;
                                switch (buf){
                                    case 1:{
                                        System.out.println("Enter rank: \n" +
                                                "TRAINEE - 1\n" +
                                                "SECOND_OFFICER - 2\n" +
                                                "FIRST_OFFICER - 3\n" +
                                                "CAPTAIN - 4");
                                        do {
                                            buf = in.nextInt();
                                            if(buf>4 || buf <1) System.out.println("Wrong number! try again...");
                                        }while(buf > 4 || buf < 1);
                                        rank = Rank.resolveRankById(buf);
                                        CrewMemberCriteria crewMemberCriteria = new CrewMemberCriteria();
                                        crewMemberCriteria.setRank(rank);
                                        handleUserInput(crewServiceActs.findAllCrewMembersByCriteria(crewMemberCriteria));
                                    }
                                    case 2:{
                                        System.out.println("Enter role: \n" +
                                                "MISSION_SPECIALIST - 1\n" +
                                                "FLIGHT_ENGINEER - 2\n" +
                                                "PILOT - 3\n" +
                                                "COMMANDER - 4");
                                        do {
                                            buf = in.nextInt();
                                            if(buf>4 || buf <1) System.out.println("Wrong number! try again...");
                                        }while(buf > 4 || buf < 1);
                                        role = Role.resolveRoleById(buf);
                                        CrewMemberCriteria crewMemberCriteria = new CrewMemberCriteria();
                                        crewMemberCriteria.setRole(role);
                                        handleUserInput(crewServiceActs.findAllCrewMembersByCriteria(crewMemberCriteria));
                                    }
                                    case 3:{
                                        System.out.println("Enter name of crew:");
                                        Scanner scan = new Scanner(System.in);
                                        String name= scan.nextLine();
                                        CrewMemberCriteria crewMemberCriteria = new CrewMemberCriteria();
                                        crewMemberCriteria.setName(name);
                                        handleUserInput(crewServiceActs.findAllCrewMembersByCriteria(crewMemberCriteria));
                                    }
                                    case 4:{
                                        System.out.println("ready (true) - 1\n" +
                                                "ready (false) - 2");
                                        do{
                                            buf = in.nextInt();
                                            if(buf>2 || buf <1) System.out.println("Wrong number! try again...");
                                            else {
                                                if(buf == 1){
                                                    CrewMemberCriteria crewMemberCriteria = new CrewMemberCriteria();
                                                    crewMemberCriteria.setIsReadyForNextMissions(true);
                                                    handleUserInput(crewServiceActs.findAllCrewMembersByCriteria(crewMemberCriteria));
                                                }else{
                                                    CrewMemberCriteria crewMemberCriteria = new CrewMemberCriteria();
                                                    crewMemberCriteria.setIsReadyForNextMissions(false);
                                                    handleUserInput(crewServiceActs.findAllCrewMembersByCriteria(crewMemberCriteria));
                                                }
                                            }
                                        }while(buf>2 || buf <1);
                                    }
                                    default:System.out.println("Wrong number! try again...");
                                }
                            }while(buf>4 || buf<1);
                            break;
                }
                case 3:{
                    System.out.println("Enter name of crew: ");
                    Scanner str = new Scanner(System.in);
                    String temp = str.nextLine();
                    CrewMemberCriteria crewMemberCriteria = new CrewMemberCriteria();
                    crewMemberCriteria.setName(temp);
                    handleUserInput(crewServiceActs.findCrewMemberByCriteria(crewMemberCriteria));
                    break;
                }
                case 4:{
                    System.out.println("Enter name of crew:\n");
                    Scanner str = new Scanner(System.in);
                    String name = str.nextLine();
                    System.out.println("Enter rank: \n" +
                            "TRAINEE - 1\n" +
                            "SECOND_OFFICER - 2\n" +
                            "FIRST_OFFICER - 3\n" +
                            "CAPTAIN - 4");
                    int rank = in.nextInt();
                    System.out.println("Enter role: \n" +
                            "MISSION_SPECIALIST - 1\n" +
                            "FLIGHT_ENGINEER - 2\n" +
                            "PILOT - 3\n" +
                            "COMMANDER - 4");
                    int role = in.nextInt();
                    System.out.println("Enter preparedness of crew to the mission(true - 1, false - 2: \n");
                    int bool;
                    do{
                        bool = in.nextInt();
                        if(bool < 1 || bool > 2) System.out.println("Wrong number! try again...\n");
                    }while(bool < 1 || bool > 2);
                    CrewMemberCriteria crewMemberCriteria = new CrewMemberCriteria();
                    crewMemberCriteria.setName(name);
                    crewMemberCriteria.setRole(Role.resolveRoleById(role));
                    crewMemberCriteria.setRank(Rank.resolveRankById(rank));
                    crewMemberCriteria.setIsReadyForNextMissions(bool == 1);
                    try {
                        CrewMember crewMember = crewServiceActs.findCrewMemberByCriteria(crewMemberCriteria).get();
                        if(crewMember == null) throw new InvalidStateException("crew");
                        else handleUserInput(crewServiceActs.updateCrewMemberDetails(crewMember));
                    }catch(InvalidStateException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                }
                case 5:{
                    break;
                }
                case 6:{
                    Scanner str = new Scanner(System.in);
                    System.out.println("Enter name of crew: ");
                    String name = str.nextLine();
                    int rank,role;
                    System.out.println("Enter role: \n" +
                            "MISSION_SPECIALIST - 1\n" +
                            "FLIGHT_ENGINEER - 2\n" +
                            "PILOT - 3\n" +
                            "COMMANDER - 4");
                    do {
                        buf = in.nextInt();
                        if(buf>4 || buf <1) System.out.println("Wrong number! try again...");
                    }while(buf > 4 || buf < 1);
                    role = buf;
                    System.out.println("Enter rank: \n" +
                            "TRAINEE - 1\n" +
                            "SECOND_OFFICER - 2\n" +
                            "FIRST_OFFICER - 3\n" +
                            "CAPTAIN - 4");
                    do {
                        buf = in.nextInt();
                        if(buf>4 || buf <1) System.out.println("Wrong number! try again...");
                    }while(buf > 4 || buf < 1);
                    rank = buf;
                    CrewMemberFactory crewMemberFactory = new CrewMemberFactory();
                    try{
                        CrewMember crewMember = crewMemberFactory.create(name,Role.resolveRoleById(role),Rank.resolveRankById(rank));
                        if(crewMember == null) throw new InvalidStateException("crew");
                        else handleUserInput(crewServiceActs.createCrewMember(crewMember));
                    }catch(InvalidStateException e){
                        System.out.println(e.getMessage());
                    }
                    break;

                }
                case 7:{
                    handleUserInput(missionServiceActs.findAllMissions());
                    break;
                }
                case 8:{
                    System.out.println("Enter criteria:\n" +
                            "Name of mission - 1\n" +
                            "Distance of mission - 2\n" +
                            "Mission status - 3");
                    do{
                            buf = in.nextInt();
                        switch (buf){
                            case 1:{
                                System.out.println("Enter name of mission: ");
                                Scanner str = new Scanner(System.in);
                                String name  = str.nextLine();
                                FlightMissionCriteria flightMissionCriteria = new FlightMissionCriteria();
                                flightMissionCriteria.setName(name);
                                handleUserInput(missionServiceActs.findAllMissionsByCriteria(flightMissionCriteria));
                            }
                            case 2:{
                                System.out.println("Enter distance of mission: ");
                                long distance;
                                distance = in.nextLong();
                                FlightMissionCriteria flightMissionCriteria = new FlightMissionCriteria();
                                flightMissionCriteria.setDistance(distance);
                                 handleUserInput(missionServiceActs.findAllMissionsByCriteria(flightMissionCriteria));
                            }
                            case 3:{
                                System.out.println("Enter status of mission: \n" +
                                        "CANCELLED - 1\n" +
                                        "FAILED - 2\n" +
                                        "PLANNED - 3\n" +
                                        "IN_PROGRESS - 4\n" +
                                        "COMPLETED - 5");
                                MissionResult missionResult = null;
                                do{
                                    buf = in.nextInt();
                                    switch (buf){
                                        case 1: {
                                            missionResult = MissionResult.CANCELLED;
                                            break;
                                        }
                                        case 2: {
                                            missionResult = MissionResult.FAILED;
                                            break;
                                        }
                                        case 3: {
                                            missionResult = MissionResult.PLANNED;
                                            break;
                                        }
                                        case 4: {
                                            missionResult = MissionResult.IN_PROGRESS;
                                            break;
                                        }
                                        case 5: {
                                            missionResult = MissionResult.COMPLETED;
                                            break;
                                        }
                                        default : {
                                            System.out.println("Wrong number! try again...");
                                        }
                                    }
                                }while(buf > 5 || buf < 1);
                                FlightMissionCriteria flightMissionCriteria = new FlightMissionCriteria();
                                flightMissionCriteria.setMissionResult(missionResult);
                                handleUserInput(missionServiceActs.findAllMissionsByCriteria(flightMissionCriteria));
                            }
                            default: System.out.println("Wrong number! try again...");
                        }
                    }while(buf >3 || buf < 1);
                    break;
                }
                case 9:{
                    System.out.println("Enter name of mission: ");
                    Scanner str = new Scanner(System.in);
                    String name = str.nextLine();
                    FlightMissionCriteria flightMissionCriteria = new FlightMissionCriteria();
                    flightMissionCriteria.setName(name);
                    handleUserInput(missionServiceActs.findMissionByCriteria(flightMissionCriteria));
                    break;
                }
                case 10:{
                    List<FlightMission> list = (List<FlightMission>) Application.nassaContext.retrieveBaseEntityList(FlightMission.class);
                    FlightMissionFactory flightMissionFactory = new FlightMissionFactory();
                    String name = "First";
                    long distance ;
                    SpaceshipFactory spaceshipFactory = new SpaceshipFactory();
                    Map<Role,Short> map = new HashMap<>();
                    map.put(Role.PILOT,(short) 195);
                    Spaceship spaceship = spaceshipFactory.create(map,"Test SpaceSHHIIIP",1901);
                    List<CrewMember> list1 = new ArrayList<>();
                    CrewMemberFactory crewMemberFactory = new CrewMemberFactory();
                    CrewMemberCriteria crewMemberCriteria = new CrewMemberCriteria();
                    crewMemberCriteria.setName("Fabio Smart");
                    CrewMember crewMember = crewServiceActs.findCrewMemberByCriteria(crewMemberCriteria).get();
                    crewMember.setReadyForNexMissions(false);
                    list1.add(crewMember);

                    PlanetFactory planetFactory = new PlanetFactory();
                    Planet from = planetFactory.create(10,15,"Yupiter");
                    Planet to = planetFactory.create(40,20,"Mars");
                    FlightMission flightMission = flightMissionFactory.create(spaceship,list1,name,from,to);
                    list.add(flightMission);
                    System.out.println("Enter name of mission which details You want to update: ");
                    Scanner str = new Scanner(System.in);
                     name = str.nextLine();
                    FlightMissionCriteria flightMissionCriteria = new FlightMissionCriteria();
                    flightMissionCriteria.setName(name);
                    try {
                        flightMission = missionServiceActs.findMissionByCriteria(flightMissionCriteria).get();
                        if(flightMission ==null) throw new InvalidStateException("flightmission");
                        else handleUserInput(flightMission);
                    }catch(InvalidStateException e){
                        System.out.println(e.getMessage());
                    }catch (NullPointerException ex){
                        System.out.println(ex.getMessage());
                    }
                    System.out.println(flightMission.getName());
                    missionServiceActs.updateFlightMissionDetails(flightMission);
                    break;
                }
                case 11:{

                }
                case 12:{

                }
                case 13:{

                }
                case 14:{

                }
                case 15:{

                }
                case 16:{

                }
                case 17:{

                }
                case 18:{

                }
                case 19:{

                }
                case 20:{

                }
                case 21:{

                }
                case 22: System.exit(0);
                default:
                    System.out.println("Wrong number! try again...\n");
            }
        }while(buf >20 || buf < 1);

        return null;
    }

    default Object handleUserInput(Object o) {
        if(o == null) System.out.println("This object(s) is absent!\n");
       // System.out.println(getApplicationContext().retrieveBaseEntityList(CrewMember.class));
        return null;
    }
}
