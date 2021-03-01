package com.epam.jwd.core_final.factory.impl;

import com.epam.jwd.core_final.criteria.CrewMemberCriteria;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.exception.InvalidInArgsException;
import com.epam.jwd.core_final.factory.EntityFactory;

import org.apache.log4j.Logger;

// do the same for other entities
public class CrewMemberFactory implements EntityFactory<CrewMember> {
    private static final Logger logger = Logger.getLogger(CrewMemberFactory.class);

    @Override
    public CrewMember create(Object... args) throws  InvalidInArgsException{
        Rank rank = null;
        Role role = null;
        String name = null;
        CrewMember crewMember = null;
        Boolean bool =true;
        try {
            if (args.length < 3 || args.length > 4) {
                logger.error("Invalid input args for creating crew !");
                throw new InvalidInArgsException(args);
            } else {
                for (Object i : args) {
                    if (i instanceof Rank) rank = (Rank) i;
                    else if (i instanceof Role) role = (Role) i;
                    else if (i instanceof String) name = (String) i;
                    else if (i instanceof Boolean) bool = (Boolean) i;
                }
            }
            if (role == null
                    || rank == null
                    || name == null) {
                logger.error("Invalid input args for creating crew !");
                throw new InvalidInArgsException(args);
            } else {
                crewMember = new CrewMemberCriteria.Builder()
                        .withName(name)
                        .withRank(rank)
                        .withRole(role)
                        .isReadyForNextMissions(bool)
                        .build();
                logger.info("CrewMember was successfully created!");
            }
        }catch(InvalidInArgsException e) {
            logger.error("Invalid input args for creating crew !");
            System.out.println(e.getMessage());
        }
        return crewMember;
    }
}
