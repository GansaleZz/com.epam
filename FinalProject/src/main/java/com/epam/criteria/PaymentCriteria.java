package com.epam.criteria;

import com.epam.entity.PaymentStatus;

import java.time.LocalDateTime;

public class PaymentCriteria extends BaseCriteria{
    private  int amount = 0;
    private PaymentStatus paymentStatus = null;

    public PaymentCriteria(){}

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}
