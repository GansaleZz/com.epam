package com.epam.db.dao;

import com.epam.criteria.PaymentCriteria;
import com.epam.entity.Payment;
import com.epam.exceptions.DaoException;
import com.epam.exceptions.FileException;

import java.util.List;

public interface PaymentDao extends BaseDao<Integer,Payment>{
    List<Payment> findAllPaymentByCriteria(PaymentCriteria paymentCriteria) throws DaoException, FileException;

}
