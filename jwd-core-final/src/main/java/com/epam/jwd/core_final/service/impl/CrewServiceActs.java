package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.context.Application;
import com.epam.jwd.core_final.context.ApplicationMenu;
import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.criteria.CrewMemberCriteria;
import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.exception.DuplicateException;
import com.epam.jwd.core_final.exception.InvalidStateException;
import com.epam.jwd.core_final.service.CrewService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.function.Predicate;

public class CrewServiceActs implements CrewService {
    @Override
    public List<CrewMember> findAllCrewMembers(){
        return (List<CrewMember>) Application.nassaContext.retrieveBaseEntityList(CrewMember.class);
    }

    @Override
    public List<CrewMember> findAllCrewMembersByCriteria(Criteria<? extends CrewMember> criteria) {
        List<CrewMember> crewMembers = new ArrayList<>(Application.nassaContext.retrieveBaseEntityList(CrewMember.class));
        List<CrewMember> list = new ArrayList<>();
        if(((CrewMemberCriteria) criteria).getReadyForNextMissions() != null){
            for(int i=0;i<crewMembers.size();++i){
                if(crewMembers.get(i).isReadyForNexMissions() == (((CrewMemberCriteria) criteria).getReadyForNextMissions())){
                    list.add(crewMembers.get(i));
                    System.out.println(crewMembers.get(i).getName() + " " + crewMembers.get(i)+"\n");
                }
            }
        }else{
            if(((CrewMemberCriteria) criteria).getName() != null){
                for(int i=0;i<crewMembers.size();++i){
                    if(crewMembers.get(i).getName().equals(((CrewMemberCriteria) criteria).getName())){
                        list.add(crewMembers.get(i));
                        System.out.println(crewMembers.get(i).getName() + " " + crewMembers.get(i)+"\n");
                    }
                }
            } else {
                if(((CrewMemberCriteria) criteria).getRank() != null){
                    for(int i=0;i<crewMembers.size();++i){
                        if(crewMembers.get(i).getRank() == (((CrewMemberCriteria) criteria).getRank())){
                            list.add(crewMembers.get(i));
                            System.out.println(crewMembers.get(i).getName() + " " + crewMembers.get(i)+"\n");
                        }
                    }
                } else{
                    if(((CrewMemberCriteria) criteria).getRole() != null){
                        for(int i=0;i<crewMembers.size();++i){
                            if(crewMembers.get(i).getRole() == (((CrewMemberCriteria) criteria).getRole())){
                                list.add(crewMembers.get(i));
                                System.out.println(crewMembers.get(i).getName() + " " + crewMembers.get(i)+"\n");
                            }
                        }
                    }
                }
            }
        }
        return list;
    }

    @Override
    public Optional<CrewMember> findCrewMemberByCriteria(Criteria<? extends CrewMember> criteria) {
        List<CrewMember> crewMembers = new ArrayList<>(Application.nassaContext.retrieveBaseEntityList(CrewMember.class));
        Optional<CrewMember> crewMember = null;
        for(int i=0; i<crewMembers.size();++i){
            if(crewMembers.get(i).getName().equals(((CrewMemberCriteria) criteria).getName())
                && crewMembers.get(i).getRank() == ((CrewMemberCriteria) criteria).getRank()
                && crewMembers.get(i).getRole() == ((CrewMemberCriteria) criteria).getRole()
                && crewMembers.get(i).isReadyForNexMissions() == ((CrewMemberCriteria) criteria).getReadyForNextMissions()) {
                    crewMember = Optional.ofNullable(crewMembers.get(i));
                    System.out.println(crewMembers.get(i).getName() + " " + crewMembers.get(i) + "\n");
                    break;
            }
        }
        return crewMember;
    }

    @Override
    public CrewMember updateCrewMemberDetails(CrewMember crewMember) {
        Scanner in = new Scanner(System.in);
        int rank,role,bool;
        System.out.println("Enter new rank: \n" +
                "TRAINEE - 1\n" +
                "SECOND_OFFICER - 2\n" +
                "FIRST_OFFICER - 3\n" +
                "CAPTAIN - 4\n");
        do{
             rank = in.nextInt();
            if(rank > 4 || rank < 1) System.out.println("Wrong number! try again...\n");
        }while(rank > 4 || rank < 1);

        System.out.println("Enter new role: \n" +
                "MISSION_SPECIALIST - 1\n" +
                "FLIGHT_ENGINEER - 2\n" +
                "PILOT - 3\n" +
                "COMMANDER - 4\n");
        do{
            role = in.nextInt();
            if(role > 4 || role < 1) System.out.println("Wrong number! try again...\n");
        }while(role > 4 || role < 1);

        System.out.println("Enter new preparedness of crew to the mission(true - 1, false - 2: \n");
        do{
            bool = in.nextInt();
            if(bool < 1 || bool > 2) System.out.println("Wrong number! try again...\n");
        }while(bool < 1 || bool > 2);
        crewMember.setRank(Rank.resolveRankById(rank));
        crewMember.setRole(Role.resolveRoleById(role));
        crewMember.setReadyForNexMissions(bool == 1);
        System.out.println(crewMember.getName() +" " + crewMember +"\n");
        return crewMember;
    }

    @Override
    public void assignCrewMemberOnMission(CrewMember crewMember) throws RuntimeException {

    }

    @Override
    public CrewMember createCrewMember(CrewMember crewMember) throws DuplicateException {
        List<CrewMember> crewMembers = new ArrayList<>(Application.nassaContext.retrieveBaseEntityList(CrewMember.class));
        try {
            for (int i = 0; i < crewMembers.size(); ++i) {
                if (crewMembers.get(i).getName().equalsIgnoreCase(crewMember.getName())) {
                    throw new DuplicateException(crewMember.getName(),"Crew");
                }
            }
            crewMembers.add(crewMember);
        }catch(DuplicateException e){
            System.out.println(e.getMessage());
        }
        return crewMember;
    }
}
