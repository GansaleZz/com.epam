package com.epam.entity;

import java.util.Optional;

public enum RequestStatus {
    ACCEPTED(1),
    INPROGRESS(2),
    DENIED(3);

    private final int id;

    RequestStatus(int id){
        this.id = id;
    }

    public static int getIdByRequestStatus(RequestStatus requestStatus){
        int id = 0;
        switch(requestStatus){
            case ACCEPTED -> id = 1;
            case INPROGRESS -> id = 2;
            case DENIED -> id = 3;
        }
        return  id;
    }
    public static Optional<RequestStatus> extractRequestStatusById(int id){
        Optional<RequestStatus> requestStatus = Optional.empty();
        switch(id){
            case 1 -> requestStatus = Optional.of(ACCEPTED);
            case 2 -> requestStatus = Optional.of(INPROGRESS);
            case 3 -> requestStatus = Optional.of(DENIED);
        }
        return requestStatus;
    }
}
