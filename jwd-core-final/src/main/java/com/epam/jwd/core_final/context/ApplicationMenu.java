package com.epam.jwd.core_final.context;

import com.epam.jwd.core_final.criteria.CrewMemberCriteria;
import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.exception.InvalidInArgsException;
import com.epam.jwd.core_final.exception.InvalidStateException;
import com.epam.jwd.core_final.factory.impl.CrewMemberFactory;
import com.epam.jwd.core_final.service.impl.CrewServiceActs;
import com.epam.jwd.core_final.service.impl.MissionServiceActs;
import com.epam.jwd.core_final.service.impl.SpacemapServiceActs;
import com.epam.jwd.core_final.service.impl.SpaceshipServiceActs;

import java.util.Scanner;
import java.util.SortedMap;
import java.util.stream.Stream;

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
                "Update spaceship details - 10\n" +
                "Create mission - 11\n" +
                "Get random planet - 12\n" +
                "Get distance between planets - 13\n" +
                "Find all spaceships - 14\n" +
                "Find all spaceships by criteria - 15\n" +
                "Find spaceship by criteria - 16\n" +
                "Update spaceship details - 17\n" +
                "Assign spaceship on mission - 18\n" +
                "Create spaceship - 19\n" +
                "Exit - 20");
        Scanner in = new Scanner(System.in);
        int buf;
        do {
            buf = in.nextInt();
            switch (buf){
                case 1: return handleUserInput(crewServiceActs.findAllCrewMembers());
                case 2: {
                    System.out.println("Enter Criteria : \n" +
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
                                            if(buf>2 || buf <1) System.out.println("Wrong number! try again...");
                                        }while(buf > 4 || buf < 1);
                                        rank = Rank.resolveRankById(buf);
                                        CrewMemberCriteria crewMemberCriteria = new CrewMemberCriteria();
                                        crewMemberCriteria.setRank(rank);
                                        return handleUserInput(crewServiceActs.findAllCrewMembersByCriteria(crewMemberCriteria));
                                    }
                                    case 2:{
                                        System.out.println("Enter role: \n" +
                                                "MISSION_SPECIALIST - 1\n" +
                                                "FLIGHT_ENGINEER - 2\n" +
                                                "PILOT - 3\n" +
                                                "COMMANDER - 4");
                                        do {
                                            buf = in.nextInt();
                                            if(buf>2 || buf <1) System.out.println("Wrong number! try again...");
                                        }while(buf > 4 || buf < 1);
                                        role = Role.resolveRoleById(buf);
                                        CrewMemberCriteria crewMemberCriteria = new CrewMemberCriteria();
                                        crewMemberCriteria.setRole(role);
                                        return handleUserInput(crewServiceActs.findAllCrewMembersByCriteria(crewMemberCriteria));
                                    }
                                    case 3:{
                                        System.out.println("Enter name of crew:");
                                        Scanner scan = new Scanner(System.in);
                                        String name= scan.nextLine();
                                        CrewMemberCriteria crewMemberCriteria = new CrewMemberCriteria();
                                        crewMemberCriteria.setName(name);
                                        return handleUserInput(crewServiceActs.findAllCrewMembersByCriteria(crewMemberCriteria));
                                    }
                                    case 4:{
                                        System.out.println(" ready (true) - 1\n" +
                                                " ready (false) - 2");
                                        do{
                                            buf = in.nextInt();
                                            if(buf>2 || buf <1) System.out.println("Wrong number! try again...");
                                            else {
                                                if(buf == 1){
                                                    CrewMemberCriteria crewMemberCriteria = new CrewMemberCriteria();
                                                    crewMemberCriteria.setIsReadyForNextMissions(true);
                                                    return handleUserInput(crewServiceActs.findAllCrewMembersByCriteria(crewMemberCriteria));
                                                }else{
                                                    CrewMemberCriteria crewMemberCriteria = new CrewMemberCriteria();
                                                    crewMemberCriteria.setIsReadyForNextMissions(false);
                                                    return handleUserInput(crewServiceActs.findAllCrewMembersByCriteria(crewMemberCriteria));
                                                }
                                            }
                                        }while(buf>2 || buf <1);
                                    }
                                    default:System.out.println("Wrong number! try again...");
                                }
                            }while(buf>4 || buf<1);
                }
                case 3:{
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
                    System.out.println("Enter preparedness of crew to the mission(true - 1, false - 2: ");
                    int bool;
                    do{
                        bool = in.nextInt();
                        if(bool < 1 || bool > 2) System.out.println("Wrong number! try again...");
                    }while(bool < 1 || bool > 2);
                    System.out.println("Enter name of crew: ");
                    Scanner str = new Scanner(System.in);
                    String temp = str.nextLine();
                    CrewMemberCriteria crewMemberCriteria = new CrewMemberCriteria();
                    crewMemberCriteria.setIsReadyForNextMissions(bool == 1);
                    crewMemberCriteria.setName(temp);
                    crewMemberCriteria.setRole(Role.resolveRoleById(role));
                    crewMemberCriteria.setRank(Rank.resolveRankById(rank));
                    return handleUserInput(crewServiceActs.findCrewMemberByCriteria(crewMemberCriteria));
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
                        return handleUserInput(crewServiceActs.updateCrewMemberDetails(crewMember));
                    }catch(NullPointerException e){
                        System.out.println("Wrong parameteres were entered!");
                    }
                }
                case 5:{

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
                        else return handleUserInput(crewServiceActs.createCrewMember(crewMember));
                    }catch(InvalidStateException e){
                        System.out.println(e.getMessage());
                    }

                }
                case 7:{

                }
                case 8:{

                }
                case 9:{

                }
                case 10:{

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
                case 20: System.exit(0);
                default:
                    System.out.println("Wrong number! try again...\n");
            }
        }while(buf >20 || buf < 1);

        return null;
    }

    default Object handleUserInput(Object o) {
        if(o == null) System.out.println("This object is absent!\n");
        System.out.println(getApplicationContext().retrieveBaseEntityList(CrewMember.class));
        return null;
    }
}
