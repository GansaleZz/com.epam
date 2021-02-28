package com.epam.jwd.core_final.criteria;

import com.epam.jwd.core_final.domain.BaseEntity;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;

/**
 * Should be a builder for {@link com.epam.jwd.core_final.domain.CrewMember} fields
 */

//OK
public class CrewMemberCriteria extends Criteria<CrewMember> {
    private static int id = 1;

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
}
