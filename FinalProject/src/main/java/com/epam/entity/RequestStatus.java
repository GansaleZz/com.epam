package com.epam.entity;

import java.util.Arrays;
import java.util.Optional;

public enum RequestStatus {
    ACCEPTED(1),
    INPROGRESS(2),
    DENIED(3),
    PAID(4);

    private final int id;

    RequestStatus(int id){
        this.id = id;
    }

    public static int getIdByRequestStatus(RequestStatus requestStatus){
        return valueOf(requestStatus.name()).id;
    }
    public static Optional<RequestStatus> extractRequestStatusById(int id){
        return Arrays.stream(values())
                .filter(i -> i.id == id)
                .findAny();
    }
}
