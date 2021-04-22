package com.epam.db.dao;

import com.epam.criteria.RequestCriteria;
import com.epam.entity.Request;
import com.epam.exceptions.DaoException;
import com.epam.exceptions.FileException;

import java.sql.SQLException;
import java.util.List;

public interface RequestDao extends BaseDao<Integer,Request>{
    List<Request> findAllRequestByCriteria(RequestCriteria requestCriteria) throws DaoException, FileException;
}
