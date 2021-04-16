package com.epam.entity;

import java.time.LocalDateTime;

public class Payment extends Entity{
    private final int amount;
    private LocalDateTime date;
    private PaymentStatus payment_status = PaymentStatus.INPROGRESS;
    private int id;

    public Payment(int amount){
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
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

    @Override
    public String toString() {
        return "Payment{" +
                "amount=" + amount +
                ", date=" + date +
                ", payment_status=" + payment_status +
                '}';
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }
}
