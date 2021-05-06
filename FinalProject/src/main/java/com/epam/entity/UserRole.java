package com.epam.entity;

import java.util.Arrays;
import java.util.Optional;

public enum UserRole {
    ADMIN(1),
    MODERATOR(2),
    CLIENT(3);

    private final int id;

    UserRole(int id){
        this.id = id;
    }

    public static int getIdByUserRole(UserRole userRole) {
        return valueOf(userRole.name()).id;
    }

    public static Optional<UserRole> extractUserRolebyId(int id){
        return Arrays.stream(values())
                .filter(i -> i.id == id)
                .findAny();
    }


}
