package com.epam.db.dao;

import com.epam.criteria.UserCriteria;
import com.epam.entity.User;
import com.epam.exceptions.DaoException;
import java.util.List;
import java.util.Optional;

public interface UserDao extends BaseDao<Integer,User>{

    /**
     * Method that use to find all users on db by special criteria
     * @param userCriteria - parameter by which users are searching on db
     * @return list of users with a specific criteria
     */
    List<User> findAllUsersByCriteria(UserCriteria userCriteria) throws DaoException;

    /**
     * Method that use to find user on db by special criteria
     * @param userCriteria - parameter by which user is searching on db
     * @return user with a specific criteria
     */
    Optional<User> findUserByCriteria(UserCriteria userCriteria) throws DaoException;

    /**
     * Method, which use to find value of user's balance on db by id
     * @param id unique parameter, need to find balance on db
     * @return value of user's balance
     */
    Double findBalanceById(Integer id) throws DaoException;
}
