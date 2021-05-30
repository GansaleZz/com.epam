package com.epam.util;

import com.epam.entity.Request;
import com.epam.entity.Room;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Cache {
    private static Cache instance = null;
    private Map<Integer, Request> activeRequests = new HashMap<>();


    private Cache(){}

    public static Cache getInstance(){
        if(instance == null){
            instance = new Cache();
        }
        return instance;
    }

    public Map<Integer, Request> getActiveRequests() {
        return activeRequests;
    }

    public void addRequest(Request request){
        activeRequests.put(request.getId(),request);
    }

    public void updateRequests(){
        Iterator i = activeRequests.entrySet().iterator();
        Calendar calendar = Calendar.getInstance();
        while (i.hasNext()) {
            HashMap.Entry pair = (HashMap.Entry) i.next();
            if(((Request)pair.getValue()).getEnd().before(calendar.getTime())){
                activeRequests.remove(pair.getValue());
            }
        }
    }

    public boolean isRoomEngaged(Date start, Date end, Room room){
        Iterator i = activeRequests.entrySet().iterator();
        boolean bool = true;
        while (i.hasNext()) {
            HashMap.Entry pair = (HashMap.Entry) i.next();
            if (((Request) pair.getValue()).getRoom().getId() == room.getId()) {
                bool = false;
                break;
            }
        }
        if(!bool) {
            activeRequests.size();
            while (i.hasNext()) {
                HashMap.Entry pair = (HashMap.Entry) i.next();
                if (((Request) pair.getValue()).getRoom().getId() == room.getId()) {
                    if ((((Request) pair.getValue()).getEnd().before(start) ||
                            ((Request) pair.getValue()).getStart().after(end)) &&
                            ((Request) pair.getValue()).getRoom().getNumberOfSeats() == room.getNumberOfSeats()) {
                        bool = true;
                    } else {
                        bool = false;
                        break;
                    }
                }else{
                    bool = true;
                }
            }
        }
        return bool;
    }
}
