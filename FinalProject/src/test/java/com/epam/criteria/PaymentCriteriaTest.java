package com.epam.criteria;

import com.epam.criteria.impl.PaymentCriteria;
import com.epam.entity.Payment;
import com.epam.entity.PaymentStatus;
import org.junit.jupiter.api.Test;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;

class PaymentCriteriaTest {

    private final int amount = 1000;
    private final PaymentStatus paymentStatus = PaymentStatus.PAID;

    @Test
    Payment createPayment(){
        Payment payment = new PaymentCriteria.Builder()
                .newBuilder()
                .withPaymentStatus(paymentStatus)
                .withDate(Calendar.getInstance().getTime())
                .withId(-1)
                .withAmount(amount)
                .build();
        assertNotNull(payment);
        return payment;
    }

    @Test
    void getAmount() {
        PaymentCriteria paymentCriteria = new PaymentCriteria();
        paymentCriteria.setAmount(createPayment().getAmount());
        assertEquals(amount, paymentCriteria.getAmount());
    }

    @Test
    void setAmount() {
        PaymentCriteria paymentCriteria = new PaymentCriteria();
        paymentCriteria.setAmount(createPayment().getAmount());
        assertEquals(amount, paymentCriteria.getAmount());
    }

    @Test
    void getPaymentStatus() {
        PaymentCriteria paymentCriteria = new PaymentCriteria();
        paymentCriteria.setPaymentStatus(createPayment().getStatus());
        assertEquals(paymentStatus, paymentCriteria.getPaymentStatus());
    }

    @Test
    void setPaymentStatus() {
        PaymentCriteria paymentCriteria = new PaymentCriteria();
        paymentCriteria.setPaymentStatus(createPayment().getStatus());
        assertEquals(paymentStatus, paymentCriteria.getPaymentStatus());
    }
}