package com.epam.jwd.core_final;

import com.epam.jwd.core_final.context.Application;
import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.criteria.CrewMemberCriteria;
import com.epam.jwd.core_final.criteria.SpaceshipCriteria;
import com.epam.jwd.core_final.domain.*;
//import com.epam.jwd.core_final.exception.InvalidStateException;
import com.epam.jwd.core_final.exception.InvalidStateException;
import com.epam.jwd.core_final.factory.impl.CrewMemberFactory;
import com.epam.jwd.core_final.factory.impl.SpaceshipFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws InvalidStateException, IOException {
      //  Application.start();
        CrewMemberFactory crewMemberFactory = new CrewMemberFactory();
        CrewMember crewMember = crewMemberFactory.create(Rank.CAPTAIN,Role.COMMANDER,"Svyat");
        CrewMember crewMember1 = crewMemberFactory.create(Rank.FIRST_OFFICER,Role.COMMANDER,"Svyat1");
        System.out.println(crewMember.getId());
        System.out.println(crewMember1.getId());
        System.out.println(crewMember.getId());
        System.out.println(Rank.resolveRankById(3));
        System.out.println();
        NassaContext nassaContext = new NassaContext();
        nassaContext.init();

    }
}