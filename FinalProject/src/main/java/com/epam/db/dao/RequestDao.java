package com.epam.db.dao;

import com.epam.criteria.RequestCriteria;
import com.epam.entity.Payment;
import com.epam.entity.Request;
import com.epam.exceptions.DaoException;
import java.util.List;

public interface RequestDao extends BaseDao<Integer,Request>{
    boolean createRequestPayment(Request request, Payment payment) throws DaoException;

    List<Request> findAllRequestsByCriteria(RequestCriteria requestCriteria) throws DaoException;
}
