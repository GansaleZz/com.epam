package com.epam.util;

import com.epam.db.dao.impl.RequestDaoImpl;
import com.epam.entity.Request;
import com.epam.entity.RequestStatus;
import com.epam.entity.Room;
import com.epam.exceptions.DaoException;

import java.util.*;

public class Cache {
    private static Cache instance = null;
    private Map<Integer, Request> activeRequests = new HashMap<>();


    private Cache(){
        RequestDaoImpl requestDao = new RequestDaoImpl();
        Calendar calendar = Calendar.getInstance();
        try {
            requestDao.findAllEntities().stream()
                    .filter(i -> i.getRequestStatus().equals(RequestStatus.ACCEPTED) ||
                            (i.getRequestStatus().equals(RequestStatus.PAID) && i.getEnd().after(calendar.getTime())))
                    .forEach(i -> addRequest(i));
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

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
        RequestDaoImpl requestDao = new RequestDaoImpl();
        try {
            List<Request> list = requestDao.findAllEntities();
            while (i.hasNext()) {
                HashMap.Entry pair = (HashMap.Entry) i.next();
                if (((Request) pair.getValue()).getEnd().before(calendar.getTime()) ||
                        (list.stream()
                                .filter(j -> j.getId() == ((Request) pair.getValue()).getId())
                                .findAny().isEmpty())) {
                    activeRequests.remove(((Request) pair.getValue()).getId());
                }
            }
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    public void removeRequest(int id){
        activeRequests.remove(id);
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
            i = activeRequests.entrySet().iterator();
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
