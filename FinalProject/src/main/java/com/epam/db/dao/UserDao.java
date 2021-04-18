package com.epam.db.dao;

import com.epam.criteria.UserCriteria;
import com.epam.entity.Entity;
import com.epam.entity.User;
import com.epam.exceptions.DaoException;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface UserDao extends BaseDao<Integer,User>{

    List<User> findAllUsersByCriteria(UserCriteria userCriteria) throws DaoException, SQLException;

    Optional<User> findUserByCriteria(UserCriteria userCriteria) throws DaoException, SQLException;
}
