package com.epam.entity;

import java.util.Optional;

public enum UserStatus {
    AVAILABLE(1),
    BANNED(2);

    private final int id;

    UserStatus(int id) {
        this.id = id;
    }

    public static int getIdByUserStatus(UserStatus userStatus){
        int id = 0;
        switch(userStatus){
            case AVAILABLE -> id = 1;
            case BANNED -> id = 2;
        }
        return  id;
    }

    public static Optional<UserStatus> extractUserStatusById(int id){
        Optional<UserStatus> userStatus = Optional.empty();
        switch(id){
            case 1 -> userStatus = Optional.of(AVAILABLE);
            case 2 -> userStatus = Optional.of(BANNED);
        }
        return userStatus;
    }


}
