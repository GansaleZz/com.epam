package com.epam.entity;

import java.util.Arrays;
import java.util.Optional;

public enum RoomStatus {
    ENGAGED(1),
    AVAILABLE(2);

    private final int id;

    RoomStatus(int id){
        this.id = id;
    }

    public static int getIdByRoomStatus(RoomStatus roomStatus) {
        return valueOf(roomStatus.name()).id;
    }

    public static Optional<RoomStatus> extractRoomStatusById(int id){
        return Arrays.stream(values())
                .filter(i -> i.id == id)
                .findAny();
    }
}
