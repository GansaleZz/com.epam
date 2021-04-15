package com.epam.db.dao.impl;

import com.epam.criteria.RequestCriteria;
import com.epam.db.dao.RequestDao;
import com.epam.entity.Request;
import com.epam.exceptions.DaoException;

import java.util.List;
import java.util.Optional;

public class RequestDaoImpl implements RequestDao {
    @Override
    public List<Request> findAllEntities() throws DaoException {
        return null;
    }

    @Override
    public Optional<Request> findEntityById(Long id) throws DaoException {
        return Optional.empty();
    }

    @Override
    public boolean create(Long id) throws DaoException {
        return false;
    }

    @Override
    public boolean delete(Long id) throws DaoException {
        return false;
    }

    @Override
    public boolean delete(Request request) throws DaoException {
        return false;
    }

    @Override
    public Optional<Request> update(Request request) throws DaoException {
        return Optional.empty();
    }

    @Override
    public Optional<Request> findRequestByCriteria(RequestCriteria requestCriteria) throws DaoException {
        return Optional.empty();
    }

    @Override
    public List<Request> findAllRequestByCriteria(RequestCriteria requestCriteria) throws DaoException {
        return null;
    }
}
