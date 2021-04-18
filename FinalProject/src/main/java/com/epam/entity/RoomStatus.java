package com.epam.entity;

import java.util.Optional;

public enum RoomStatus {
    ENGAGED(1),
    AVAILABLE(2);

    private final int id;

    RoomStatus(int id){
        this.id = id;
    }

    public static int getIdByRoomStatus(RoomStatus roomStatus) {
        int id = 0;
        switch (roomStatus){
            case ENGAGED -> id = 1;
            case AVAILABLE -> id = 2;
        }
        return id;
    }

    public static Optional<RoomStatus> extractRoomStatusById(int id){
        Optional<RoomStatus> roomStatus = Optional.empty();
        switch(id){
            case 1 -> roomStatus = Optional.of(ENGAGED);
            case 2 -> roomStatus = Optional.of(AVAILABLE);
        }
        return roomStatus;
    }
}
