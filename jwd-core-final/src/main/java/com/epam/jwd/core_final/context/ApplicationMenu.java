package com.epam.jwd.core_final.context;

import com.epam.jwd.core_final.service.impl.CrewServiceActs;
import com.epam.jwd.core_final.service.impl.MissionServiceActs;
import com.epam.jwd.core_final.service.impl.SpacemapServiceActs;
import com.epam.jwd.core_final.service.impl.SpaceshipServiceActs;

// todo replace Object with your own types
@FunctionalInterface
public interface ApplicationMenu {

    ApplicationContext getApplicationContext();
    CrewServiceActs crewServiceActs = new CrewServiceActs();
    SpacemapServiceActs spacemapServiceActs = new SpacemapServiceActs();
    SpaceshipServiceActs spaceshipServiceActs = new SpaceshipServiceActs();
    MissionServiceActs missionServiceActs = new MissionServiceActs();


    default Object printAvailableOptions() {
        return null;
    }

    default Object handleUserInput(Object o) {
        return null;
    }
}
