package com.epam.jwd.core_final.domain;

import com.epam.jwd.core_final.exception.UnknownEntityException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public enum Rank implements BaseEntity {
    TRAINEE(1L),
    SECOND_OFFICER(2L),
    FIRST_OFFICER(3L),
    CAPTAIN(4L);

    private final Long id;

    Rank(Long id) {
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
     *
     * @throws UnknownEntityException if such id does not exist
     */

    //Ok
    public static Rank resolveRankById(int id) throws UnknownEntityException{
        Rank rank = null;

        try{
            switch (id){
                case 1: {
                    rank = TRAINEE;
                    break;
                }
                case 2: {
                    rank = SECOND_OFFICER;
                    break;
                }
                case 3: {
                    rank = FIRST_OFFICER;
                    break;
                }
                case 4: {
                    rank = CAPTAIN;
                    break;
                }
            }
        }catch (UnknownEntityException e){
            Logger logger = LoggerFactory.getLogger("UnknownEntityException");
            logger.error("Such id " + id +"does not exist for this entity:" + "Rank");
            new UnknownEntityException("Rank",id);
            System.out.println(e.getMessage());
        }
        return rank;
    }
}
