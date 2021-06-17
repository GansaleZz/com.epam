package com.epam.util;

import com.epam.db.dao.impl.RequestDaoImpl;
import com.epam.entity.Request;
import com.epam.entity.RequestStatus;
import com.epam.entity.Room;
import com.epam.exceptions.DaoException;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Realisation of cache which controlling relevance of requests
 */
public class Cache {
    private static Cache instance = null;

    /**
     * Map 'activeRequests' need to store requests which was accepted/paid for checking their relevance
     */
    private Map<Integer, Request> activeRequests = new Hashtable<>();
    private final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(Cache.class);

    /**
     * Initialization of map
     */
    private Cache(){
        RequestDaoImpl requestDao = new RequestDaoImpl();
        Calendar calendar = Calendar.getInstance();
        try {
            requestDao.findAllEntities().stream()
                    .filter(i -> i.getRequestStatus().equals(RequestStatus.ACCEPTED) ||
                            (i.getRequestStatus().equals(RequestStatus.PAID) && i.getEnd().after(calendar.getTime())))
                    .forEach(i -> addRequest(i));
        } catch (DaoException e) {
            logger.error(e.getMessage());
        }
    }

    public static Cache getInstance(){
        if(instance == null){
            instance = new Cache();
        }
        return instance;
    }

    /**
     * Method that use to add new accepted / paid request on map
     */
    public void addRequest(Request request){
        activeRequests.put(request.getId(),request);
    }

    /**
     * Method that use to update map of 'active requests' and requests on db.
     * In 'while' we check whether the requests in the map are relevant by time or,
     * if any of this requests where deleted on db, they are deleting from map too.
     * Next we check requests on db, if any of them didn't get answer or was accepted,
     * but client didn't pay for it, and their 'start date' before current date, then
     * request status automatically become 'Denied'
     */
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
            requestDao.findAllEntities().stream()
                    .filter(j -> (j.getRequestStatus().equals(RequestStatus.ACCEPTED) | j.getRequestStatus().equals(RequestStatus.INPROGRESS)))
                    .forEach(j -> {
                        Calendar start = Calendar.getInstance();
                        start.setTime(j.getStart());
                        if((start.get(Calendar.YEAR) >= Calendar.getInstance().get(Calendar.YEAR) &&
                                start.get(Calendar.DAY_OF_YEAR) < Calendar.getInstance().get(Calendar.DAY_OF_YEAR)) ||
                                start.get(Calendar.YEAR) < Calendar.getInstance().get(Calendar.YEAR)) {
                            try {
                                j.setRequestStatus(RequestStatus.DENIED);
                                requestDao.update(j);
                            } catch (DaoException e) {
                                logger.error(e.getMessage());
                            }
                        }
                    });
        } catch (DaoException e) {
            logger.error(e.getMessage());
        }
    }

    /**
     * Method use to remove request from map by id
     * @param id use to find request on map
     */
    public void removeRequest(Integer id){
        activeRequests.remove(id);
    }

    /**
     * Method that use to check if a room is engaged between 'start' and 'end' date for reservation
     * @param start - start date of reservation of room
     * @param end - end date of reservation of room
     * @param room - room being checked
     */
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
