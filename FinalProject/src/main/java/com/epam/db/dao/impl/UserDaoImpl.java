package com.epam.db.dao.impl;

import com.epam.criteria.UserCriteria;
import com.epam.db.dao.UserDao;
import com.epam.entity.User;
import com.epam.exceptions.DaoException;

import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao {
    @Override
    public List<User> findAllEntities() throws DaoException {
        return null;
    }

    @Override
    public Optional<User> findEntityById(Long id) throws DaoException {
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
    public boolean delete(User user) throws DaoException {
        return false;
    }

    @Override
    public Optional<User> update(User user) throws DaoException {
        return Optional.empty();
    }

    @Override
    public List<User> findAllUsersByCriteria(UserCriteria userCriteria) throws DaoException {
        return null;
    }

    @Override
    public Optional<User> findUserByCriteria(UserCriteria userCriteria) throws DaoException {
        return Optional.empty();
    }
}
