package com.epam.jwd.core_final;

import com.epam.jwd.core_final.context.Application;
import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.criteria.CrewMemberCriteria;
import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.criteria.SpaceshipCriteria;
import com.epam.jwd.core_final.domain.*;
//import com.epam.jwd.core_final.exception.InvalidStateException;
import com.epam.jwd.core_final.exception.InvalidStateException;
import com.epam.jwd.core_final.factory.impl.CrewMemberFactory;
import com.epam.jwd.core_final.factory.impl.SpaceshipFactory;
import com.epam.jwd.core_final.service.impl.CrewServiceActs;
import com.epam.jwd.core_final.util.PropertyReaderUtil;
import com.fasterxml.jackson.databind.ser.Serializers;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws InvalidStateException, IOException {
          Application.start();
        CrewServiceActs crewServiceActs = new CrewServiceActs();
        System.out.println(crewServiceActs.findAllCrewMembers());
        Criteria<CrewMember> criteria = new CrewMemberCriteria();
        CrewMember crewMember = new CrewMember("fff",1);
//        CrewMemberFactory crewMemberFactory = new CrewMemberFactory();
//        CrewMember crewMember = crewMemberFactory.create(Rank.CAPTAIN,Role.COMMANDER,"Svyat");
//        CrewMember crewMember1 = crewMemberFactory.create(Rank.FIRST_OFFICER,Role.COMMANDER,"Svyat1");
//        System.out.println(crewMember.getId());
//        System.out.println(crewMember1.getId());
//        System.out.println(crewMember.getId());
//        CrewMember crewMember2 = crewMemberFactory.create(Rank.FIRST_OFFICER,Role.COMMANDER,"Svyat2");
//        System.out.println(crewMember2.getId());
//        NassaContext nassaContext = new NassaContext();
//        nassaContext.init();
//
//        CrewMemberFactory crewMemberFactory = new CrewMemberFactory();
//        Class<Spaceship> clas = Spaceship.class;
//        Collection<Spaceship> crewMember = nassaContext.retrieveBaseEntityList(clas);

//        PropertyReaderUtil.loadProperties();
//        System.out.println(PropertyReaderUtil.getProperties().getProperty("spacemapFileName"));
    }
}