package com.epam.criteria;

import com.epam.entity.PaymentStatus;

import java.time.LocalDateTime;

public class PaymentCriteria extends BaseCriteria{
    private  int amount = Integer.valueOf(null);
    private LocalDateTime date = null;
    private PaymentStatus payment_status = null;

    public PaymentCriteria(){}

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public PaymentStatus getPayment_status() {
        return payment_status;
    }

    public void setPayment_status(PaymentStatus payment_status) {
        this.payment_status = payment_status;
    }
}
