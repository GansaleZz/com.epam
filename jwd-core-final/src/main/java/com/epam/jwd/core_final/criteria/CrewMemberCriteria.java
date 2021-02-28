package com.epam.jwd.core_final.criteria;

import com.epam.jwd.core_final.domain.BaseEntity;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.factory.impl.CrewMemberFactory;

/**
 * Should be a builder for {@link com.epam.jwd.core_final.domain.CrewMember} fields
 */

//OK
public class CrewMemberCriteria extends Criteria<CrewMember> {
    private static int id = 1;
    private String name;
    private Rank rank;
    private Role role;

    public static class Builder extends BaseBuilder<CrewMember>{

        public Builder withName(String name){
            actualClass = new CrewMember(name,id);
            return this;
        }

        public Builder withRank(Rank rank){
            actualClass.setRank(rank);
            return this;
        }

        public Builder withRole(Role role){
            actualClass.setRole(role);
            return this;
        }

        @Override
        protected CrewMember getActual() {
            return actualClass;
        }
    }

    public void setRole(Role role){
        this.role = role;
    }

    public void setRank(Rank rank){
        this.rank = rank;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Rank getRank() {
        return rank;
    }

    public Role getRole() {
        return role;
    }
}
