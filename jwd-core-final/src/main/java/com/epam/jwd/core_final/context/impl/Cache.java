package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.MissionResult;
import com.epam.jwd.core_final.service.impl.CrewServiceActs;
import org.apache.log4j.Logger;

import java.time.LocalDateTime;
import java.util.*;

public class Cache extends TimerTask {
    private static Map<Long,FlightMission> cache = new HashMap<>();
    private static Cache instance;
    private static final Logger logger = Logger.getLogger(Cache.class);
    private Cache(){}

    public static Cache getInstance(){
        if(instance == null){
            instance = new Cache();
        }
        return instance;
    }

    @Override
    public void run() {
        refreshCache();
    }


    public static void addToCache(FlightMission flightMission){
        if(flightMission.getMissionResult() == MissionResult.IN_PROGRESS){
            cache.putIfAbsent(flightMission.getId(),flightMission);
        }
    }

    public static void removeFromCache(FlightMission flightMission){
        if (flightMission.getMissionResult() == MissionResult.CANCELLED) {
            JsonUtils.parseFlightMissionJson(flightMission);
            cache.remove(flightMission.getId());
        }
    }

    public static void refreshCache(){
        if(cache.size() != 0 ) {
            Iterator i = cache.entrySet().iterator();
            while(i.hasNext()) {
                Map.Entry pair = (Map.Entry) i.next();
                if(((FlightMission) pair.getValue()).getEnd().isBefore(LocalDateTime.now())){
                    Random random = new Random();
                    int res = random.nextInt(10);
                    if(res <=5){
                        ((FlightMission) pair.getValue()).setMissionResult(MissionResult.COMPLETED);
                        JsonUtils.parseFlightMissionJson(((FlightMission) pair.getValue()));
                        ((FlightMission) pair.getValue()).getAssignedSpaceShip().setReadyForNextMissions(true);
                        ((FlightMission) pair.getValue()).getAssignedSpaceShip().setFlightDist(((FlightMission) pair.getValue()).getAssignedSpaceShip().getFlightDist() - ((FlightMission) pair.getValue()).getDistance());
                        ((FlightMission) pair.getValue()).setAssignedSpaceShip(null);
                        ((FlightMission) pair.getValue()).getAssignedCrew().stream()
                                .forEach(crew -> crew.setReadyForNexMissions(true));
                        ((FlightMission) pair.getValue()).getAssignedCrew().clear();
                        System.out.println("Mission "+((FlightMission) pair.getValue()).getName()+ " completed!");
                        logger.info("Mission " + ((FlightMission) pair.getValue()).getName() + " completed!");
                        cache.remove(pair.getKey());
                    }else{
                        ((FlightMission) pair.getValue()).setMissionResult(MissionResult.FAILED);
                        JsonUtils.parseFlightMissionJson(((FlightMission) pair.getValue()));
                        ((FlightMission) pair.getValue()).setAssignedSpaceShip(null);
                        ((FlightMission) pair.getValue()).getAssignedCrew().clear();
                        System.out.println("Mission "+((FlightMission) pair.getValue()).getName()+ " failed!");
                        logger.info("Mission "+((FlightMission) pair.getValue()).getName()+ " failed!");
                        cache.remove(pair.getKey());
                    }
                }
            }
        }
    }
}
