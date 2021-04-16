package com.epam.entity;

import java.util.Optional;

public enum UserStatus {
    AVAILABLE(1),
    DELETED(2),
    BANNED(3);

    private final int id;

    UserStatus(int id) {
        this.id = id;
    }

    public static Optional<UserStatus> extractUserStatusById(int id){
        Optional<UserStatus> userStatus = Optional.empty();
        switch(id){
            case 1 -> userStatus = Optional.of(AVAILABLE);
            case 2 -> userStatus = Optional.of(DELETED);
            case 3 -> userStatus = Optional.of(BANNED);
        }
        return userStatus;
    }


}
