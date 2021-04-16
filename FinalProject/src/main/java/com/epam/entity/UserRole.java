package com.epam.entity;

import java.util.Optional;

public enum UserRole {
    ADMIN(1),
    CLIENT(2);

    private final int id;

    UserRole(int id){
        this.id = id;
    }

    public static int getIdByUserRole(UserRole userRole) {
        int id = 0;
        switch (userRole){
            case ADMIN -> id = 1;
            case CLIENT -> id = 2;
        }
        return id;
    }

    public static Optional<UserRole> extractUserRolebyId(int id){
        Optional<UserRole> userRole = Optional.empty();
        switch(id){
            case 1 -> userRole = Optional.of(ADMIN);
            case 2 -> userRole = Optional.of(CLIENT);
        }
        return userRole;
    }
}
