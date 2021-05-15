package com.epam.entity;

import java.util.Arrays;
import java.util.Optional;

public enum UserRole {
    ADMIN(1,"/usersView/admin/"),
    MODERATOR(2,"/usersView/moderator/"),
    CLIENT(3,"/usersView/client/");

    private final int id;
    private final String permissions;


    UserRole(int id, String permissions){
        this.id = id;
        this.permissions = permissions;
    }

    public static int getIdByUserRole(UserRole userRole) {
        return valueOf(userRole.name()).id;
    }

    public static UserRole getRole(String role){
        return valueOf(role);
    }

    public static Optional<UserRole> extractUserRolebyId(int id){
        return Arrays.stream(values())
                .filter(i -> i.id == id)
                .findAny();
    }

    public String getPermissions() {
        return permissions;
    }

    public static String getPermissionsByUserRole(UserRole userRole){
        return valueOf(userRole.name()).permissions;
    }

}
