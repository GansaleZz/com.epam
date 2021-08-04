package com.epam.entity;

import java.util.Arrays;
import java.util.Optional;

public enum AccountStatus {
    NOTACTIVATED(1),
    ACTIVATED(2);

    private final int id;

    AccountStatus(int id){
        this.id = id;
    }

    public static int getIdByAccountStatus(AccountStatus accountStatus){
        return valueOf(accountStatus.name()).id;
    }

    public static AccountStatus getStatus(String status){
        return valueOf(status);
    }

    public static Optional<AccountStatus> extractUserStatusById(int id){
        return Arrays.stream(values())
                .filter(i -> i.id == id)
                .findAny();
    }
}
