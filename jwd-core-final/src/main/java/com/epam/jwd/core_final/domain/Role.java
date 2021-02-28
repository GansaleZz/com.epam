package com.epam.jwd.core_final.domain;

import com.epam.jwd.core_final.exception.UnknownEntityException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public enum Role implements BaseEntity {
    MISSION_SPECIALIST(1L),
    FLIGHT_ENGINEER(2L),
    PILOT(3L),
    COMMANDER(4L);

    private final Long id;

    Role(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }

    /**
     * todo via java.lang.enum methods!
     */
    @Override
    public String getName() {
        return this.name();
    }

    /**
     * todo via java.lang.enum methods!
     * @throws UnknownEntityException if such id does not exist
     */

    public static Role resolveRoleById(int id) throws UnknownEntityException{
        Role role = null;
        try{
            switch (id){
                case 1: {
                    role = MISSION_SPECIALIST;
                    break;
                }
                case 2: {
                    role = FLIGHT_ENGINEER;
                    break;
                }
                case 3: {
                        role = PILOT;
                        break;
                }
                case 4:{
                        role = COMMANDER;
                        break;
                }
            }
        }catch (UnknownEntityException e){
            Logger logger = LoggerFactory.getLogger("UnknownEntityException");
            logger.error("Such id " + id +"does not exist for this entity:" + "Role");
            throw new UnknownEntityException("Role",id);
        }
        return role;
    }
}
