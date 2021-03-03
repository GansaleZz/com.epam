package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.MissionResult;
import com.epam.jwd.core_final.domain.Spaceship;

import java.time.LocalDateTime;
import java.util.*;

public class Cache {
    private int id;
    private static Hashtable<Long,FlightMission> cache;
    private static Cache instance;

    private Cache(){}

    public static Cache getInstance(){
        if(instance ==null){
            instance = new Cache();
        }
        return instance;
    }

    public static void addToCache(FlightMission flightMission){
        if(flightMission.getMissionResult() == MissionResult.IN_PROGRESS){
            cache.putIfAbsent(flightMission.getId(),flightMission);
        }
    }

    public static void refreshCache(){
        if(cache.size() != 0 ) {
            List<FlightMission> list = new ArrayList<>(cache.values());
            for (int i = 0; i < list.size(); ++i) {
                if(list.get(i).getEnd().isBefore(LocalDateTime.now())){
                    Random random = new Random();
                    int res = random.nextInt(10);
                    if(res <=5){
                        list.get(i).setMissionResult(MissionResult.COMPLETED);
                        list.get(i).getAssignedSpaceShip().setReadyForNextMissions(true);
                        list.get(i).getAssignedCrew().stream()
                                .forEach(crew -> crew.setReadyForNexMissions(true));
                        list.remove(i);
                    }else{
                        list.get(i).setMissionResult(MissionResult.FAILED);
                        list.remove(i);
                    }
                }
            }
        }
    }
}
