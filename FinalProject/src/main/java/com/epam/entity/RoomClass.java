package com.epam.entity;

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
        int id = 0;
        switch (roomClass){
            case BUSINESS -> id = 1;
            case ECONOM -> id = 2;
            case LUXE -> id = 3;
            case PREMIUM -> id = 4;
        }
        return id;
    }

    public static Optional<RoomClass> extractRoomClassById(int id){
        Optional<RoomClass> roomClass = Optional.empty();
        switch(id){
            case 1 -> roomClass = Optional.of(BUSINESS);
            case 2 -> roomClass = Optional.of(ECONOM);
            case 3 -> roomClass = Optional.of(LUXE);
            case 4 -> roomClass = Optional.of(PREMIUM);
        }
        return roomClass;
    }
}
