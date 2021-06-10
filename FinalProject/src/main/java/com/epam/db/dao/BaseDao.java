package com.epam.db.dao;

import com.epam.entity.BaseEntity;
import com.epam.exceptions.DaoException;
import java.util.List;
import java.util.Optional;

/**
 * Interface with general methods of dao pattern for all entities
 *
 * @author Andrey Rubin
 */
public interface BaseDao <K,T extends BaseEntity> {

    /**
     * Method, which finds all entities on db
     * @return list of entities from db
     */
    List<T> findAllEntities() throws DaoException;

    /**
     * Method, which use to find entity on db by id, if its exists
     * @param id unique parameter, need to find entity on db
     * @return entity from db, if its exists
     */
    Optional<T> findEntityById(K id) throws DaoException;

    /**
     * Method which creates entity on db
     * @param t - it is argument, which information will be taken
     * for creating
     * @return boolean, that indicates success of creating of entity
     */
    boolean create(T t) throws DaoException;

    /**
     * Method, which use to deleting entity from db by id
     * @param id - parameter that need to search entity on db
     * @return boolean, that indicates success of deleting  (true - if entity
     * with id exists, not - false)
     */
    boolean delete(K id) throws DaoException;

    /**
     * Method, which use to update information of entity on db
     * @param t - it is argument with new information
     * @return updated entity
     */
    Optional<T> update(T t) throws DaoException;
}
