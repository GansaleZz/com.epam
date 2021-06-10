package com.epam.criteria.Impl;

import com.epam.criteria.BaseCriteria;
import com.epam.entity.Payment;
import com.epam.entity.PaymentStatus;

import java.util.Date;

public class PaymentCriteria extends BaseCriteria<Payment> {
    private  int amount = 0;
    private PaymentStatus paymentStatus = null;

    /**
     * Realisation of builder pattern for {@link Payment}
     */
    public static class Builder extends BaseBuilder<Payment>{

        public Builder newBuilder(){
            actualClass = new Payment();
            return this;
        }

        public Builder withId(int id){
            actualClass.setId(id);
            return this;
        }

        public Builder withPaymentStatus(PaymentStatus paymentStatus){
            actualClass.setStatus(paymentStatus);
            return this;
        }

        public Builder withAmount(int amount){
            actualClass.setAmount(amount);
            return this;
        }

        public Builder withDate(Date date){
            actualClass.setDate(date);
            return this;
        }

        @Override
        protected Payment getActual() {
            return actualClass;
        }
    }

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
