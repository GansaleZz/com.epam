package com.epam.jwd.core_final.Tests;

import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.exception.InvalidInArgsException;
import com.epam.jwd.core_final.factory.impl.CrewMemberFactory;
import org.junit.platform.commons.util.StringUtils;


import static org.junit.jupiter.api.Assertions.*;

class CrewMemberFactoryTest {

    @org.junit.jupiter.api.Test
    void create() {
        CrewMemberFactory crewMemberFactory = new CrewMemberFactory();
        CrewMember crewMember = crewMemberFactory.create(Rank.CAPTAIN, Role.COMMANDER,"Test Crew");
        assertEquals(crewMember.getRank(),Rank.CAPTAIN);
        assertEquals(crewMember.getRole(),Role.COMMANDER);
        assertEquals(crewMember.getName(),"Test Crew");
    }
}