package com.epam.db.dao;

import com.epam.criteria.RequestCriteria;
import com.epam.entity.Request;
import com.epam.exceptions.DaoException;

import java.util.List;
import java.util.Optional;

public interface RequestDao extends BaseDao<Long,Request>{
    Optional<Request> findRequestByCriteria(RequestCriteria requestCriteria) throws DaoException;

    List<Request> findAllRequestByCriteria(RequestCriteria requestCriteria) throws DaoException;
}
