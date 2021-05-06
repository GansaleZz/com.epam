package com.epam.entity;

import java.util.Arrays;
import java.util.Optional;

public enum UserStatus {
    AVAILABLE(1),
    BANNED(2);

    private final int id;

    UserStatus(int id) {
        this.id = id;
    }

    public static int getIdByUserStatus(UserStatus userStatus){
        return valueOf(userStatus.name()).id;
    }

    public static Optional<UserStatus> extractUserStatusById(int id){
        return Arrays.stream(values())
                .filter(i -> i.id == id)
                .findAny();
    }


}
