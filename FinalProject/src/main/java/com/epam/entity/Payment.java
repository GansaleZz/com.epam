package com.epam.entity;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

public class Payment extends Entity{
    private int amount;
    private Date date;
    private PaymentStatus payment_status = PaymentStatus.INPROGRESS;
    private int id;

    public Payment(int id, int amount, Date date, PaymentStatus payment_status ){
        this.amount = amount;
        this.id = id;
        this.date = date;
        this.payment_status = payment_status;
    }

    public Payment(int id, int amount, PaymentStatus payment_status){
        this.id = id;
        this.amount = amount;
        this.payment_status = payment_status;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

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
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return amount == payment.amount && id == payment.id && Objects.equals(date, payment.date) && payment_status == payment.payment_status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, date, payment_status, id);
    }
}
