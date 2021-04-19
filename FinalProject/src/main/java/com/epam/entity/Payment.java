package com.epam.entity;

import java.time.LocalDateTime;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return amount == payment.amount && id == payment.id && date.equals(payment.date) && payment_status == payment.payment_status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, date, payment_status, id);
    }
}
