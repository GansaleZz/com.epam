package com.epam.entity;

import java.util.Arrays;
import java.util.Optional;

public enum PaymentStatus {
    PAID(1),
    CANCELLED(2),
    INPROGRESS(3);

    private final int id;

    PaymentStatus(int id){
        this.id = id;
    }

    public static int getIdByPaymentStatus(PaymentStatus paymentStatus){
        return valueOf(paymentStatus.name()).id;
    }

    public static Optional<PaymentStatus> extractPaymentStatusById(int id){
        return Arrays.stream(values())
                .filter(i -> i.id == id)
                .findAny();
    }
}
