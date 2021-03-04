package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.context.Application;
import com.epam.jwd.core_final.criteria.CrewMemberCriteria;
import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.criteria.FlightMissionCriteria;
import com.epam.jwd.core_final.domain.*;
import com.epam.jwd.core_final.exception.*;
import com.epam.jwd.core_final.service.CrewService;

import java.util.*;

public class CrewServiceActs implements CrewService {
    private static CrewServiceActs instance;

    private CrewServiceActs(){}

    public static CrewServiceActs getInstance(){
        if(instance == null){
            instance = new CrewServiceActs();
        }
        return instance;
    }

    @Override
    public List<CrewMember> findAllCrewMembers(){
        return (List<CrewMember>) Application.nassaContext.retrieveBaseEntityList(CrewMember.class);
    }

    @Override
    public List<CrewMember> findAllCrewMembersByCriteria(Criteria<? extends CrewMember> criteria) {
        List<CrewMember> crewMembers = (List<CrewMember>) Application.nassaContext.retrieveBaseEntityList(CrewMember.class);
        List<CrewMember> list = new ArrayList<>();
        if(((CrewMemberCriteria) criteria).getReadyForNextMissions() != null){
            crewMembers.stream()
                    .filter(i -> i.isReadyForNexMissions() == (((CrewMemberCriteria) criteria).getReadyForNextMissions()))
                    .forEach(i -> list.add(i));
        }else{
            if(((CrewMemberCriteria) criteria).getName() != null){
                crewMembers.stream()
                        .filter(i -> i.getName().equals(((CrewMemberCriteria) criteria).getName()))
                        .forEach(i -> list.add(i));
            } else {
                if(((CrewMemberCriteria) criteria).getRank() != null){
                    crewMembers.stream()
                            .filter(i -> i.getRank() == (((CrewMemberCriteria) criteria).getRank()))
                            .forEach(i -> list.add(i));
                } else{
                    if(((CrewMemberCriteria) criteria).getRole() != null){
                        crewMembers.stream()
                                .filter(i -> i.getRole() == (((CrewMemberCriteria) criteria).getRole()))
                                .forEach(i -> list.add(i));
                    }
                }
            }
        }
        return list;
    }

    @Override
    public Optional<CrewMember> findCrewMemberByCriteria(Criteria<? extends CrewMember> criteria) {
        List<CrewMember> crewMembers = (List<CrewMember>) Application.nassaContext.retrieveBaseEntityList(CrewMember.class);
        Optional<CrewMember> crewMember;
        crewMember = crewMembers.stream()
                .filter(i -> i.getName().equals(((CrewMemberCriteria) criteria).getName()))
                .findAny();
        return crewMember;
    }

    @Override
    public CrewMember updateCrewMemberDetails(CrewMember crewMember) {
        if(crewMember.isReadyForNexMissions() != true){
            System.out.println("Crew member is busy now ! Try again next time...\n");
        }
        Scanner in = new Scanner(System.in);
        int rank,role,buf;
        System.out.println("Enter which detail you want to update: \n" +
                "Role - 1\n" +
                "Rank -2");
        do{
            buf = in.nextInt();
            switch (buf){
                case 1:{
                    System.out.println("Enter new role: \n" +
                            "MISSION_SPECIALIST - 1\n" +
                            "FLIGHT_ENGINEER - 2\n" +
                            "PILOT - 3\n" +
                            "COMMANDER - 4\n");
                    do{
                        role = in.nextInt();
                        if(role > 4 || role < 1) System.out.println("Wrong number! try again...\n");
                    }while(role > 4 || role < 1);
                    crewMember.setRole(Role.resolveRoleById(role));
                    break;
                }
                case 2:{
                    System.out.println("Enter new rank: \n" +
                            "TRAINEE - 1\n" +
                            "SECOND_OFFICER - 2\n" +
                            "FIRST_OFFICER - 3\n" +
                            "CAPTAIN - 4\n");
                    do{
                        rank = in.nextInt();
                        if(rank > 4 || rank < 1) System.out.println("Wrong number! try again...\n");
                    }while(rank > 4 || rank < 1);
                    crewMember.setRank(Rank.resolveRankById(rank));
                    break;
                }
                default:System.out.println("Wrong number! try again...\n");
            }
        }while(buf > 2 || buf < 1);
//        in.close();
        return crewMember;
    }

    @Override
    public void assignCrewMemberOnMission(CrewMember crewMember) throws ReadinessException, InvalidStateException, FullAssignedException {
        try {
            if (crewMember.isReadyForNexMissions() != true) throw new ReadinessException(crewMember.getName());
            else{
                Scanner str = new Scanner(System.in);
                MissionServiceActs missionServiceActs = MissionServiceActs.getInstance();
                FlightMissionCriteria flightMissionCriteria= new FlightMissionCriteria();
                System.out.println("Enter name of mission: ");
                String name = str.nextLine();
//                String name = "Test";
                flightMissionCriteria.setName(name);
                if(!missionServiceActs.findMissionByCriteria(flightMissionCriteria).isPresent()) throw new InvalidStateException("flight mission");
                else{
                    FlightMission flightMission = missionServiceActs.findMissionByCriteria(flightMissionCriteria).get();
                    if(flightMission.getAssignedSpaceShip() == null) {
                        System.out.println("You need to assign spaceship first!");
                    }
                    else
                    if(flightMission.getMissionResult() != MissionResult.PLANNED) throw new AssignException("crew member");
                    else{
                        Map<Role,Short> crew = flightMission.getAssignedSpaceShip().getCrew();
                        if(flightMission.getAssignedCrew().size()!= 0) {
                            short count = (short) flightMission.getAssignedCrew().stream()
                                    .filter(i -> i.getRole() == crewMember.getRole())
                                    .count();
                            if(count >= crew.get(crewMember.getRole())) throw new FullAssignedException(flightMission.getName());
                        }else{
                            crewMember.setReadyForNexMissions(false);
                            flightMission.addToCrew(crewMember);
                        }
                    }
                }
//                str.close();
            }
        } catch (ReadinessException | InvalidStateException | FullAssignedException | AssignException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public CrewMember createCrewMember(CrewMember crewMember) throws DuplicateException {
        List<CrewMember> crewMembers = (List<CrewMember>) Application.nassaContext.retrieveBaseEntityList(CrewMember.class);
        try {
            Optional<CrewMember> crewCheck;
                    crewCheck = crewMembers.stream()
                    .filter(i -> i.getName().equalsIgnoreCase(crewMember.getName()))
                    .findAny();
                if (crewCheck.isPresent()) {
                    throw new DuplicateException(crewMember.getName(),"crew");
                }
                crewMembers.add(crewMember);
        }catch(DuplicateException e){
            System.out.println(e.getMessage());
        }
        return crewMember;
    }
}
