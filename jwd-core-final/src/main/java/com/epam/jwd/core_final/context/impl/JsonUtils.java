package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.domain.FlightMission;

import com.fasterxml.jackson.core.JsonParser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

import java.io.FileWriter;
import java.io.IOException;

public class JsonUtils {

    private static JSONArray jsonArray = new JSONArray();


    public static void parseFlightMissionJson(FlightMission flightMission){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name",""+flightMission.getName());
        jsonObject.put("flight distance",""+flightMission.getDistance());
        jsonObject.put("initial planet",""+flightMission.getFrom());
        jsonObject.put("ending planet",""+flightMission.getTo());
        jsonObject.put("mission result",""+flightMission.getMissionResult());
        jsonObject.put("starting of mission",""+flightMission.getStart());
        jsonObject.put("ending of mission",""+flightMission.getEnd());
        jsonArray.add(jsonObject);
        System.out.println(jsonArray.size());
        try {
            FileWriter file = new FileWriter("/Users/andrew_wannasesh/Folders/EpamJAva/jwd-core-final/src/main/resources/output/flightmissions.json");
            file.write(jsonArray.toJSONString());
            file.close();
        }catch (IOException e){
            e.printStackTrace();
        }

    }

}
