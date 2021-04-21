package com.epam.entity;

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
        int id = 0;
        switch(paymentStatus){
            case PAID -> id = 1;
            case CANCELLED -> id = 2;
            case INPROGRESS -> id = 3;
        }
        return  id;
    }

    public static Optional<PaymentStatus> extractPaymentStatusById(int id){
        Optional<PaymentStatus> paymentStatus = Optional.empty();
        switch (id){
            case 1 -> paymentStatus = Optional.of(PAID);
            case 2 -> paymentStatus = Optional.of(CANCELLED);
            case 3 -> paymentStatus = Optional.of(INPROGRESS);
        }
        return paymentStatus;
    }
}
