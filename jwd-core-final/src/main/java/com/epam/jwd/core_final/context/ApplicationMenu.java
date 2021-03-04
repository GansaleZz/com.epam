package com.epam.jwd.core_final.context;

import com.epam.jwd.core_final.context.impl.Cache;
import com.epam.jwd.core_final.criteria.CrewMemberCriteria;
import com.epam.jwd.core_final.criteria.FlightMissionCriteria;
import com.epam.jwd.core_final.criteria.PlanetCriteria;
import com.epam.jwd.core_final.criteria.SpaceshipCriteria;
import com.epam.jwd.core_final.domain.*;
import com.epam.jwd.core_final.exception.InvalidStateException;
import com.epam.jwd.core_final.factory.impl.CrewMemberFactory;
import com.epam.jwd.core_final.factory.impl.FlightMissionFactory;
import com.epam.jwd.core_final.factory.impl.SpaceshipFactory;
import com.epam.jwd.core_final.service.impl.CrewServiceActs;
import com.epam.jwd.core_final.service.impl.MissionServiceActs;
import com.epam.jwd.core_final.service.impl.SpacemapServiceActs;
import com.epam.jwd.core_final.service.impl.SpaceshipServiceActs;

import java.util.*;

// todo replace Object with your own types
@FunctionalInterface
public interface ApplicationMenu {

    ApplicationContext getApplicationContext();

    CrewServiceActs crewServiceActs = CrewServiceActs.getInstance();
    SpacemapServiceActs spacemapServiceActs = SpacemapServiceActs.getInstance();
    SpaceshipServiceActs spaceshipServiceActs = SpaceshipServiceActs.getInstance();
    MissionServiceActs missionServiceActs = MissionServiceActs.getInstance();


    default void printAvailableOptions(){
        int buf ;
        Timer timer = new Timer();
        timer.schedule(Cache.getInstance(),0,3000);
        Scanner in = new Scanner(System.in);
        do {
            do {
                System.out.println("Choose act: \n" +
                        "1 - Find all crew members  \n" +
                        "2 - Find all crew members by criteria \n" +
                        "3 - Find crew member by criteria \n" +
                        "4 - Update crew member details \n" +
                        "5 - Assign crew member on mission \n" +
                        "6 - Create crew member \n" +
                        "7 - Find all missions \n" +
                        "8 - Find all missions by criteria \n" +
                        "9 - Find mission by criteria \n" +
                        "10 - Update flight mission details \n" +
                        "11 - Create mission \n" +
                        "12 - Get random planet \n" +
                        "13 - Get distance between planets \n" +
                        "14 - Find planet by criteria \n" +
                        "15 - Find all spaceships \n" +
                        "16 - Find all spaceships by criteria \n" +
                        "17 - Find spaceship by criteria \n" +
                        "18 - Update spaceship details \n" +
                        "19 - Assign spaceship on mission \n" +
                        "20 - Create spaceship \n" +
                        "21 - Start mission \n" +
                        "22 - Exit ");
                buf = in.nextInt();
                switch (buf) {
                    case 1: {
                        handleUserInput(crewServiceActs.findAllCrewMembers());
                        break;
                    }
                    case 2: {
                        System.out.println("Enter criteria : \n" +
                                "Rank - 1 \n" +
                                "Role - 2 \n" +
                                "Name - 3 \n" +
                                "Ready for next missions - 4");
                        do {
                            buf = in.nextInt();
                            Rank rank;
                            Role role;
                            switch (buf) {
                                case 1: {
                                    System.out.println("Enter rank: \n" +
                                            "TRAINEE - 1\n" +
                                            "SECOND_OFFICER - 2\n" +
                                            "FIRST_OFFICER - 3\n" +
                                            "CAPTAIN - 4");
                                    do {
                                        buf = in.nextInt();
                                        if (buf > 4 || buf < 1) System.out.println("Wrong number! try again...");
                                    } while (buf > 4 || buf < 1);
                                    rank = Rank.resolveRankById(buf);
                                    CrewMemberCriteria crewMemberCriteria = new CrewMemberCriteria();
                                    crewMemberCriteria.setRank(rank);
                                    handleUserInput(crewServiceActs.findAllCrewMembersByCriteria(crewMemberCriteria));
                                    break;
                                }
                                case 2: {
                                    System.out.println("Enter role: \n" +
                                            "MISSION_SPECIALIST - 1\n" +
                                            "FLIGHT_ENGINEER - 2\n" +
                                            "PILOT - 3\n" +
                                            "COMMANDER - 4");
                                    do {
                                        buf = in.nextInt();
                                        if (buf > 4 || buf < 1) System.out.println("Wrong number! try again...");
                                    } while (buf > 4 || buf < 1);
                                    role = Role.resolveRoleById(buf);
                                    CrewMemberCriteria crewMemberCriteria = new CrewMemberCriteria();
                                    crewMemberCriteria.setRole(role);
                                    handleUserInput(crewServiceActs.findAllCrewMembersByCriteria(crewMemberCriteria));
                                    break;
                                }
                                case 3: {
                                    System.out.println("Enter name of crew:");
                                    Scanner scan = new Scanner(System.in);
                                    String name = scan.nextLine();
                                    scan.close();
                                    CrewMemberCriteria crewMemberCriteria = new CrewMemberCriteria();
                                    crewMemberCriteria.setName(name);
                                    handleUserInput(crewServiceActs.findAllCrewMembersByCriteria(crewMemberCriteria));
                                    break;
                                }
                                case 4: {
                                    System.out.println("ready (true) - 1\n" +
                                            "ready (false) - 2");
                                    do {
                                        buf = in.nextInt();
                                        if (buf > 2 || buf < 1) System.out.println("Wrong number! try again...");
                                        else {
                                            if (buf == 1) {
                                                CrewMemberCriteria crewMemberCriteria = new CrewMemberCriteria();
                                                crewMemberCriteria.setIsReadyForNextMissions(true);
                                                handleUserInput(crewServiceActs.findAllCrewMembersByCriteria(crewMemberCriteria));
                                            } else {
                                                CrewMemberCriteria crewMemberCriteria = new CrewMemberCriteria();
                                                crewMemberCriteria.setIsReadyForNextMissions(false);
                                                handleUserInput(crewServiceActs.findAllCrewMembersByCriteria(crewMemberCriteria));
                                            }
                                        }
                                    } while (buf > 2 || buf < 1);
                                    break;
                                }
                                default:
                                    System.out.println("Wrong number! try again...");
                            }
                        } while (buf > 4 || buf < 1);
                        break;
                    }
                    case 3: {
                        System.out.println("Enter name of crew: ");
                        Scanner str = new Scanner(System.in);
                        String temp = str.nextLine();
                        CrewMemberCriteria crewMemberCriteria = new CrewMemberCriteria();
                        crewMemberCriteria.setName(temp);
                        handleUserInput(crewServiceActs.findCrewMemberByCriteria(crewMemberCriteria));
                        break;
                    }
                    case 4: {
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
                        do {
                            bool = in.nextInt();
                            if (bool < 1 || bool > 2) System.out.println("Wrong number! try again...\n");
                        } while (bool < 1 || bool > 2);
                        CrewMemberCriteria crewMemberCriteria = new CrewMemberCriteria();
                        crewMemberCriteria.setName(name);
                        crewMemberCriteria.setRole(Role.resolveRoleById(role));
                        crewMemberCriteria.setRank(Rank.resolveRankById(rank));
                        crewMemberCriteria.setIsReadyForNextMissions(bool == 1);
                        try {
                            if (!crewServiceActs.findCrewMemberByCriteria(crewMemberCriteria).isPresent())
                                throw new InvalidStateException("crew");
                            else {
                                CrewMember crewMember = crewServiceActs.findCrewMemberByCriteria(crewMemberCriteria).get();
                                handleUserInput(crewServiceActs.updateCrewMemberDetails(crewMember));
                            }
                        } catch (InvalidStateException e) {
                            System.out.println(e.getMessage());
                            break;
                        }
                        break;
                    }
                    case 5: {
                        System.out.println("Enter name of crew member: ");
                        try {
                            Scanner str = new Scanner(System.in);
                            String name = str.nextLine();
                            CrewMemberCriteria crewMemberCriteria = new CrewMemberCriteria();
                            crewMemberCriteria.setName(name);
                            if (!crewServiceActs.findCrewMemberByCriteria(crewMemberCriteria).isPresent())
                                throw new InvalidStateException("crew member");
                            else {
                                crewServiceActs.assignCrewMemberOnMission(crewServiceActs.findCrewMemberByCriteria(crewMemberCriteria).get());
                            }
                        } catch (InvalidStateException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    }
                    case 6: {
                        Scanner str = new Scanner(System.in);
                        System.out.println("Enter name of crew: ");
                        String name = str.nextLine();
                        int rank, role;
                        System.out.println("Enter role: \n" +
                                "MISSION_SPECIALIST - 1\n" +
                                "FLIGHT_ENGINEER - 2\n" +
                                "PILOT - 3\n" +
                                "COMMANDER - 4");
                        do {
                            buf = in.nextInt();
                            if (buf > 4 || buf < 1) System.out.println("Wrong number! try again...");
                        } while (buf > 4 || buf < 1);
                        role = buf;
                        System.out.println("Enter rank: \n" +
                                "TRAINEE - 1\n" +
                                "SECOND_OFFICER - 2\n" +
                                "FIRST_OFFICER - 3\n" +
                                "CAPTAIN - 4");
                        do {
                            buf = in.nextInt();
                            if (buf > 4 || buf < 1) System.out.println("Wrong number! try again...");
                        } while (buf > 4 || buf < 1);
                        rank = buf;
                        CrewMemberFactory crewMemberFactory = new CrewMemberFactory();
                        try {
                            CrewMember crewMember = crewMemberFactory.create(name, Role.resolveRoleById(role), Rank.resolveRankById(rank));
                            if (crewMember == null) throw new InvalidStateException("crew");
                            else handleUserInput(crewServiceActs.createCrewMember(crewMember));
                        } catch (InvalidStateException e) {
                            System.out.println(e.getMessage());
                            break;
                        }
                        break;
                    }
                    case 7: {
                        handleUserInput(missionServiceActs.findAllMissions());
                        break;
                    }
                    case 8: {
                        System.out.println("Enter criteria:\n" +
                                "Distance of mission - 1\n" +
                                "Mission status - 2");
                        do {
                            buf = in.nextInt();
                            switch (buf) {
                                case 1: {
                                    System.out.println("Enter distance of mission: ");
                                    long distance;
                                    distance = in.nextLong();
                                    FlightMissionCriteria flightMissionCriteria = new FlightMissionCriteria();
                                    flightMissionCriteria.setDistance(distance);
                                    handleUserInput(missionServiceActs.findAllMissionsByCriteria(flightMissionCriteria));
                                    break;
                                }
                                case 2: {
                                    System.out.println("Enter status of mission: \n" +
                                            "CANCELLED - 1\n" +
                                            "FAILED - 2\n" +
                                            "PLANNED - 3\n" +
                                            "IN_PROGRESS - 4\n" +
                                            "COMPLETED - 5");
                                    MissionResult missionResult = null;
                                    do {
                                        buf = in.nextInt();
                                        switch (buf) {
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
                                            default: {
                                                System.out.println("Wrong number! try again...");
                                            }
                                        }
                                    } while (buf > 5 || buf < 1);
                                    FlightMissionCriteria flightMissionCriteria = new FlightMissionCriteria();
                                    flightMissionCriteria.setMissionResult(missionResult);
                                    handleUserInput(missionServiceActs.findAllMissionsByCriteria(flightMissionCriteria));
                                    break;
                                }
                                default:
                                    System.out.println("Wrong number! try again...");
                            }
                        } while (buf > 2 || buf < 1);
                        break;
                    }
                    case 9: {
                        System.out.println("Enter name of mission: ");
                        Scanner str = new Scanner(System.in);
                        String name = str.nextLine();
                        FlightMissionCriteria flightMissionCriteria = new FlightMissionCriteria();
                        flightMissionCriteria.setName(name);
                        handleUserInput(missionServiceActs.findMissionByCriteria(flightMissionCriteria));
                        break;
                    }
                    case 10: {
                        String name;
                        FlightMission flightMission;
                        System.out.println("Enter name of mission which details You want to update: ");
                        Scanner str = new Scanner(System.in);
                        name = str.nextLine();
                        FlightMissionCriteria flightMissionCriteria = new FlightMissionCriteria();
                        flightMissionCriteria.setName(name);
                        try {
                            if (missionServiceActs.findMissionByCriteria(flightMissionCriteria) == null)
                                throw new InvalidStateException("flightmission");
                            else {
                                flightMission = missionServiceActs.findMissionByCriteria(flightMissionCriteria).get();
                                if (flightMission.getMissionResult() != null || flightMission.getMissionResult() != MissionResult.PLANNED) {
                                    System.out.println("Flight mission " + flightMission.getName() + " is in the progress now! Tra next time...");
                                } else {
                                    handleUserInput(missionServiceActs.updateFlightMissionDetails(flightMission));
                                }
                            }
                        } catch (InvalidStateException e) {
                            System.out.println(e.getMessage());
                            break;
                        }
                        break;
                    }
                    case 11: {
                        System.out.println("Enter name of mission: ");
                        Scanner str = new Scanner(System.in);
                        String name = str.nextLine();
                        try {
                            Planet to;
                            Planet from;
                            PlanetCriteria planetCriteria = new PlanetCriteria();
                            System.out.println("Enter name of initial planet: ");
                            String first = str.nextLine();
                            planetCriteria.setName(first);
                            if (!spacemapServiceActs.findPlanetByCriteria(planetCriteria).isPresent()) {
                                throw new InvalidStateException("planet");
                            } else {
                                from = spacemapServiceActs.findPlanetByCriteria(planetCriteria).get();
                            }
                            System.out.println("Enter name of ending planet: ");
                            String second;
                            do {
                                second = str.nextLine();
                                if (first.equals(second))
                                    System.out.println("You can not enter the same name both for initial and ending planets! Try again ...");
                            } while (first.equals(second));
                            planetCriteria.setName(second);
                            if (!spacemapServiceActs.findPlanetByCriteria(planetCriteria).isPresent()) {
                                throw new InvalidStateException("planet");
                            } else {
                                to = spacemapServiceActs.findPlanetByCriteria(planetCriteria).get();
                            }
                            FlightMissionFactory flightMissionFactory = new FlightMissionFactory();
                            FlightMission flightMission = flightMissionFactory.create(to, from, name);
                            handleUserInput(missionServiceActs.createMission(flightMission));
                        } catch (InvalidStateException e) {
                            System.out.println(e.getMessage());
                            break;
                        }
                        break;
                    }
                    case 12: {
                        handleUserInput(spacemapServiceActs.getRandomPlanet());
                        break;
                    }
                    case 13: {
                        PlanetCriteria planetCriteria = new PlanetCriteria();
                        Scanner str = new Scanner(System.in);
                        try {
                            System.out.println("Enter name of first planet: ");
                            String name = str.nextLine();
                            planetCriteria.setName(name);
                            if (!spacemapServiceActs.findPlanetByCriteria(planetCriteria).isPresent())
                                throw new InvalidStateException("planet");
                            Planet first = spacemapServiceActs.findPlanetByCriteria(planetCriteria).get();
                            System.out.println("Enter name of second planet: ");
                            name = str.nextLine();
                            planetCriteria.setName(name);
                            if (!spacemapServiceActs.findPlanetByCriteria(planetCriteria).isPresent())
                                throw new InvalidStateException("planet");
                            Planet second = spacemapServiceActs.findPlanetByCriteria(planetCriteria).get();
                            handleUserInput(spacemapServiceActs.getDistanceBetweenPlanets(first, second));
                        } catch (InvalidStateException e) {
                            System.out.println(e.getMessage());
                            break;
                        }
                        break;
                    }
                    case 14: {
                        System.out.println("Enter name of planet: ");
                        Scanner str = new Scanner(System.in);
                        String name = str.nextLine();
                        PlanetCriteria planetCriteria = new PlanetCriteria();
                        planetCriteria.setName(name);
                        handleUserInput(spacemapServiceActs.findPlanetByCriteria(planetCriteria));
                        break;
                    }
                    case 15: {
                        handleUserInput(spaceshipServiceActs.findAllSpaceships());
                        break;
                    }
                    case 16: {
                        System.out.println("Enter criteria : \n" +
                                "Flight distance - 1 \n" +
                                "Ready for next missions - 2");
                        do {
                            buf = in.nextInt();
                            switch (buf) {
                                case 1: {
                                    System.out.println("Enter flight distance: ");
                                    Scanner lon = new Scanner(System.in);
                                    long distance = lon.nextLong();
                                    SpaceshipCriteria spaceshipCriteria = new SpaceshipCriteria();
                                    spaceshipCriteria.setFlightDistance(distance);
                                    handleUserInput(spaceshipServiceActs.findAllSpaceshipsByCriteria(spaceshipCriteria));
                                    break;
                                }
                                case 2: {
                                    System.out.println("Enter preparedness of crew to the mission(true - 1, false - 2: \n");
                                    do {
                                        buf = in.nextInt();
                                        if (buf > 2 || buf < 1) System.out.println("Wrong number! try again...");
                                    } while (buf > 2 || buf < 1);
                                    SpaceshipCriteria spaceshipCriteria = new SpaceshipCriteria();
                                    spaceshipCriteria.setReadyForNextMissions(buf == 1);
                                    handleUserInput(spaceshipServiceActs.findAllSpaceshipsByCriteria(spaceshipCriteria));
                                    break;
                                }
                                default:
                                    System.out.println("Wrong number! try again...");
                            }
                        } while (buf > 2 || buf < 1);
                        break;
                    }
                    case 17: {
                        System.out.println("Enter name of spaceship: ");
                        Scanner str = new Scanner(System.in);
                        String name = str.nextLine();
                        SpaceshipCriteria spaceshipCriteria = new SpaceshipCriteria();
                        spaceshipCriteria.setName(name);
                        handleUserInput(spaceshipServiceActs.findSpaceshipByCriteria(spaceshipCriteria));
                        break;
                    }
                    case 18: {
                        System.out.println("Enter name of spaceship: ");
                        Scanner str = new Scanner(System.in);
                        String name = str.nextLine();
                        try {
                            SpaceshipCriteria spaceshipCriteria = new SpaceshipCriteria();
                            spaceshipCriteria.setName(name);
                            if (!spaceshipServiceActs.findSpaceshipByCriteria(spaceshipCriteria).isPresent())
                                throw new InvalidStateException("spaceship");
                            else
                                handleUserInput(spaceshipServiceActs.updateSpaceshipDetails(spaceshipServiceActs.findSpaceshipByCriteria(spaceshipCriteria).get()));
                        } catch (InvalidStateException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    }
                    case 19: {
                        System.out.println("Enter name of spaceship: ");
                        Scanner str = new Scanner(System.in);
                        String name = str.nextLine();
                        Spaceship spaceship;
                        try {
                            SpaceshipCriteria spaceshipCriteria = new SpaceshipCriteria();
                            spaceshipCriteria.setName(name);
                            if (!spaceshipServiceActs.findSpaceshipByCriteria(spaceshipCriteria).isPresent())
                                throw new InvalidStateException("spaceship");
                            else {
                                spaceship = spaceshipServiceActs.findSpaceshipByCriteria(spaceshipCriteria).get();
                                spaceshipServiceActs.assignSpaceshipOnMission(spaceship);
                            }
                        } catch (InvalidStateException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    }
                    case 20: {
                        System.out.println("Enter flight distance of spaceship: ");
                        long distance = in.nextLong();
                        System.out.println("Enter requirement crew for this spaceship (number of crew members for this role): ");
                        Map<Role, Short> crew = new HashMap<>();
                        System.out.println(Role.MISSION_SPECIALIST);
                        buf = in.nextInt();
                        crew.put(Role.MISSION_SPECIALIST, (short) buf);
                        System.out.println(Role.FLIGHT_ENGINEER);
                        buf = in.nextInt();
                        crew.put(Role.FLIGHT_ENGINEER, (short) buf);
                        System.out.println(Role.PILOT);
                        buf = in.nextInt();
                        crew.put(Role.PILOT, (short) buf);
                        System.out.println(Role.COMMANDER);
                        buf = in.nextInt();
                        crew.put(Role.COMMANDER, (short) buf);
                        Scanner str = new Scanner(System.in);
                        System.out.println("Enter name of spaceship: ");
                        String name = str.nextLine();
                        SpaceshipFactory spaceshipFactory = new SpaceshipFactory();
                        handleUserInput(spaceshipServiceActs.createSpaceship(spaceshipFactory.create(crew, name, distance)));
                        break;
                    }
                    case 21: {
                        System.out.println("Enter name of mission: ");
                        try {
                            FlightMissionCriteria flightMissionCriteria = new FlightMissionCriteria();
                            Scanner str = new Scanner(System.in);
                            String name = str.nextLine();
                            flightMissionCriteria.setName(name);
                            if (!missionServiceActs.findMissionByCriteria(flightMissionCriteria).isPresent())
                                throw new InvalidStateException("flight mission");
                            else {
                                missionServiceActs.startMission(missionServiceActs.findMissionByCriteria(flightMissionCriteria).get());
                            }
                        } catch (InvalidStateException e) {
                            System.out.println(e.getMessage());
                            break;
                        }
                        break;
                    }
                    case 22:
                        System.exit(0);
                    default:
                        System.out.println("Wrong number! try again...\n");
                }
            } while (buf > 20 || buf < 1);
        }while(buf != 22);

    }

    default void handleUserInput(Object o) {
        if(o == null) System.out.println("This object(s) is absent!\n");
        else {
            if(o instanceof List){
                for (Object s: (List) o){
                    System.out.println(s.toString());
                }
            }
            else{
                if(!(o instanceof Optional))
                    System.out.println(o.toString());
                else{
                    if(((Optional<?>) o).isPresent())
                    System.out.println(((Optional<?>) o).get().toString());
                }
            }
        }
    }
}
