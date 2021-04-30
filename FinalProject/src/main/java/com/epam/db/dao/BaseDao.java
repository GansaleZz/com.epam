package com.epam.db.dao;

import com.epam.entity.BaseEntity;
import com.epam.exceptions.DaoException;
import com.epam.exceptions.FileException;

import java.util.List;
import java.util.Optional;

public interface BaseDao <K,T extends BaseEntity> {

    List<T> findAllEntities() throws DaoException;

    Optional<T> findEntityById(K id) throws DaoException;

    boolean create(T t) throws DaoException;

    boolean delete(K id) throws DaoException;

    Optional<T> update(T t) throws DaoException;

}
