package com.epam.jwd.core_final.context;

import com.epam.jwd.core_final.criteria.CrewMemberCriteria;
import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.factory.impl.CrewMemberFactory;
import com.epam.jwd.core_final.service.impl.CrewServiceActs;
import com.epam.jwd.core_final.service.impl.MissionServiceActs;
import com.epam.jwd.core_final.service.impl.SpacemapServiceActs;
import com.epam.jwd.core_final.service.impl.SpaceshipServiceActs;

import java.util.Scanner;

// todo replace Object with your own types
@FunctionalInterface
public interface ApplicationMenu {

    ApplicationContext getApplicationContext();

    CrewServiceActs crewServiceActs = new CrewServiceActs();
    SpacemapServiceActs spacemapServiceActs = new SpacemapServiceActs();
    SpaceshipServiceActs spaceshipServiceActs = new SpaceshipServiceActs();
    MissionServiceActs missionServiceActs = new MissionServiceActs();


    default Object printAvailableOptions() {
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
                "Exit - 20\n");
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
                            "Name - 3 ");
                            do{
                                buf = in.nextInt();
                                Rank rank;
                                Role role;
                                switch (buf){
                                    case 1:{
                                        System.out.println("Enter Rank: \n" +
                                                "TRAINEE - 1\n" +
                                                "SECOND_OFFICER - 2\n" +
                                                "FIRST_OFFICER - 3\n" +
                                                "CAPTAIN - 4\n");
                                        buf = in.nextInt();
                                        rank = Rank.resolveRankById(buf);
                                        CrewMemberCriteria crewMemberCriteria = new CrewMemberCriteria();
                                        crewMemberCriteria.setRank(rank);
                                        return handleUserInput(crewServiceActs.findAllCrewMembersByCriteria(crewMemberCriteria));
                                    }
                                    case 2:{
                                        System.out.println("Enter Role: \n" +
                                                "MISSION_SPECIALIST - 1\n" +
                                                "FLIGHT_ENGINEER - 2\n" +
                                                "PILOT - 3\n" +
                                                "COMMANDER - 4\n");
                                        buf = in.nextInt();
                                        role = Role.resolveRoleById(buf);
                                        CrewMemberCriteria crewMemberCriteria = new CrewMemberCriteria();
                                        crewMemberCriteria.setRole(role);
                                        return handleUserInput(crewServiceActs.findAllCrewMembersByCriteria(crewMemberCriteria));
                                    }
                                    case 3:{
                                        System.out.println("Enter name:");
                                        String name = in.nextLine();
                                        CrewMemberCriteria crewMemberCriteria = new CrewMemberCriteria();
                                        crewMemberCriteria.setName(name);
                                        return handleUserInput(crewServiceActs.findAllCrewMembersByCriteria(crewMemberCriteria));
                                    }
                                }
                            }while(buf>4 || buf<1);
                }
                case 20: System.exit(0);
                default:
                    System.out.println("Wrong number! try again...");
            }
        }while(buf >20 || buf < 1);

        return null;
    }

    default Object handleUserInput(Object o) {
        System.out.println(o);
        return null;
    }
}
