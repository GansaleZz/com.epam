package com.epam.db.dao;

import com.epam.criteria.Impl.PaymentCriteria;
import com.epam.entity.Payment;
import com.epam.exceptions.DaoException;
import java.util.List;

public interface PaymentDao extends BaseDao<Integer,Payment>{

    /**
     * Method that find all payments on db by special criteria
     * @param paymentCriteria - parameter by which payments are searching on db
     * @return list of payments with a specific criteria
     */
    List<Payment> findAllPaymentByCriteria(PaymentCriteria paymentCriteria) throws DaoException;

}
