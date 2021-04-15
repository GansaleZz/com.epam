package com.epam.db.dao;

import com.epam.criteria.PaymentCriteria;
import com.epam.entity.Entity;
import com.epam.entity.Payment;
import com.epam.exceptions.DaoException;

import java.util.List;
import java.util.Optional;

public interface PaymentDao extends BaseDao<Long,Payment>{
    Optional<Payment> findPaymentByCriteria(PaymentCriteria paymentCriteria) throws DaoException;

    List<Payment> findAllPaymentByCriteria(PaymentCriteria paymentCriteria) throws DaoException;

}
