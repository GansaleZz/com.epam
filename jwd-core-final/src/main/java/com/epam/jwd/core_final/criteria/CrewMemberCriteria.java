package com.epam.jwd.core_final.criteria;

import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;

/**
 * Should be a builder for {@link com.epam.jwd.core_final.domain.CrewMember} fields
 */

//OK
public class CrewMemberCriteria extends Criteria<CrewMember> {
    private CrewMember crewMember;
    private static int id = 1;

    public CrewMemberCriteria(String name){
        crewMember = new CrewMember(name,id++);
    }

    public CrewMemberCriteria withRank(Rank rank){
        crewMember.setRank(rank);
        return this;
    }

    public CrewMemberCriteria withRole(Role role){
        crewMember.setRole(role);
        return this;
    }


    public CrewMember build(){
        return crewMember;
    }
}
