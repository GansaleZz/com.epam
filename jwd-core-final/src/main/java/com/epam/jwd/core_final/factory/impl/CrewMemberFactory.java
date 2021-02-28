package com.epam.jwd.core_final.factory.impl;

import com.epam.jwd.core_final.criteria.CrewMemberCriteria;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.exception.InvalidInArgsException;
import com.epam.jwd.core_final.factory.EntityFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// do the same for other entities
public class CrewMemberFactory implements EntityFactory<CrewMember> {
    @Override
    public CrewMember create(Object... args) throws  InvalidInArgsException{
        Rank rank = null;
        Role role = null;
        String name = null;
        CrewMember crewMember;
        Logger logger = LoggerFactory.getLogger(this.getClass());
        if (args.length != 3 ) {
            logger.error("Invalid input args : " +args);
            throw new InvalidInArgsException(args);
        }
            else {
                for (Object i : args) {
                    if (i instanceof Rank) rank = (Rank) i;
                    else if (i instanceof Role) role = (Role) i;
                    else if (i instanceof String) name = (String) i;
                }
            }

        if(role == null
                || rank == null
                || name == null) {
            logger.error("Invalid input args : " +args);
            throw new InvalidInArgsException(args);
        }else
            try {
//            crewMember = new CrewMemberCriteria(name)
//                    .withRank(rank)
//                    .withRole(role)
//                    .build();
                crewMember = new CrewMemberCriteria.Builder()
                       .withName(name)
                       .withRank(rank)
                       .withRole(role)
                       .build();

            logger.info("CrewMember was completely created!");
        }catch(InvalidInArgsException e){
            logger.error("Invalid input args : " +args);
            throw new InvalidInArgsException(args);
        }
        return crewMember;
    }
}
