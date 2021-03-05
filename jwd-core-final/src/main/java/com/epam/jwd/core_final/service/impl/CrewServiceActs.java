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
        return list;
    }

    @Override
    public Optional<CrewMember> findCrewMemberByCriteria(Criteria<? extends CrewMember> criteria) {
        List<CrewMember> crewMembers = (List<CrewMember>) Application.nassaContext.retrieveBaseEntityList(CrewMember.class);
        Optional<CrewMember> crewMember = null;
            crewMember = crewMembers.stream()
                    .filter(i -> i.getName().equals(((CrewMemberCriteria) criteria).getName()))
                    .findAny();
        return crewMember;
    }

    @Override
    public CrewMember updateCrewMemberDetails(CrewMember crewMember) {
        if(crewMember.isReadyForNexMissions() != true){
            System.out.println("Crew member is busy now ! Try again next time...");
        }
        else {
            Scanner in = new Scanner(System.in);
            int rank, role, buf;
            System.out.println("Enter which detail you want to update: \n" +
                    "1 - Role\n" +
                    "2 - Rank");
            do {
                while (!in.hasNextInt()) {
                    System.out.println("You need to enter number! Try again...");
                    in.nextLine();
                }
                buf = in.nextInt();
                switch (buf) {
                    case 1: {
                        System.out.println("Enter new role: \n" +
                                "1 - MISSION_SPECIALIST\n" +
                                "2 - FLIGHT_ENGINEER\n" +
                                "3 - PILOT\n" +
                                "4 - COMMANDER");
                        do {
                            in.nextLine();
                            while (!in.hasNextInt()) {
                                System.out.println("You need to enter number! Try again...");
                                in.nextLine();
                            }
                            role = in.nextInt();
                            if (role > 4 || role < 1) System.out.println("Wrong number! try again...\n");
                        } while (role > 4 || role < 1);
                        crewMember.setRole(Role.resolveRoleById(role));
                        break;
                    }
                    case 2: {
                        System.out.println("Enter new rank: \n" +
                                "1 - TRAINEE\n" +
                                "2 - SECOND_OFFICER\n" +
                                "3 - FIRST_OFFICER\n" +
                                "4 - CAPTAIN");
                        do {
                            in.nextLine();
                            while (!in.hasNextInt()) {
                                System.out.println("You need to enter number! Try again...");
                                in.nextLine();
                            }
                            rank = in.nextInt();
                            if (rank > 4 || rank < 1) System.out.println("Wrong number! try again...\n");
                        } while (rank > 4 || rank < 1);
                        crewMember.setRank(Rank.resolveRankById(rank));
                        break;
                    }
                    default:
                        System.out.println("Wrong number! try again...\n");
                }
            } while (buf > 2 || buf < 1);
        }
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
                flightMissionCriteria.setName(name);
                if(missionServiceActs.findMissionByCriteria(flightMissionCriteria).isEmpty()) throw new InvalidStateException("flight mission");
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
                            System.out.println("Crew member "+crewMember.getName()+" was completely assigned on mission "+flightMission.getName());
                        }
                    }
                }
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
            CrewMember finalCrewMember = crewMember;
            crewCheck = crewMembers.stream()
                    .filter(i -> i.getName().equalsIgnoreCase(finalCrewMember.getName()))
                    .findAny();
                if (crewCheck.isPresent()) {
                    crewMember = crewCheck.get();
                    throw new DuplicateException(crewMember.getName(),"crew");
                }
                crewMembers.add(crewMember);
        }catch(DuplicateException e){
            System.out.println(e.getMessage());
        }
        return crewMember;
    }
}
