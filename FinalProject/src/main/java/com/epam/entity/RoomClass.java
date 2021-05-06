package com.epam.entity;

import java.util.Arrays;
import java.util.Optional;

public enum RoomClass {
    BUSINESS(1),
    ECONOM(2),
    LUXE(3),
    PREMIUM(4);

    private final int id;

    RoomClass(int id){
        this. id = id;
    }

    public static int getIdByRoomClass(RoomClass roomClass) {
        return valueOf(roomClass.name()).id;
    }

    public static Optional<RoomClass> extractRoomClassById(int id){
        return Arrays.stream(values())
                .filter(i -> i.id == id)
                .findAny();
    }
}
