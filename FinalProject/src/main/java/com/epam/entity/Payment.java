package com.epam.entity;

import java.util.Date;
import java.util.Objects;

public class Payment extends BaseEntity {
    private int amount;
    private Date date;
    private PaymentStatus status;

    public Payment(){}

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public PaymentStatus getPaymentStatus() {
        return status;
    }

    public void setPaymentstatus(PaymentStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "amount=" + amount +
                ", date=" + date +
                ", payment_status=" + status +
                ", id=" + super.getId() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return amount == payment.amount && Objects.equals(date, payment.date) && status == payment.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, date, status);
    }

}
