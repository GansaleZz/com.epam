package com.epam.db.dao;

import com.epam.entity.Entity;
import com.epam.exceptions.DaoException;

import java.util.List;
import java.util.Optional;

public interface BaseDao <K,T extends Entity> {
    List<T> findAllEntities() throws DaoException;

    Optional<T> findEntityById(K id) throws DaoException;

    boolean create(K id) throws DaoException;

    boolean delete(K id) throws DaoException;

    boolean delete(T t) throws DaoException;

    Optional<T> update(T t) throws DaoException;
}
