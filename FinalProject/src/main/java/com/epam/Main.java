package com.epam;

import com.epam.criteria.UserCriteria;
import com.epam.db.dao.impl.UserDaoImpl;
import com.epam.entity.UserStatus;
import com.epam.exceptions.DaoException;
import com.epam.exceptions.FileException;

public class Main {
    public static void main(String[] args) throws FileException, DaoException {
        UserDaoImpl userDao = new UserDaoImpl();
        System.out.println(userDao.findAllEntities());
        System.out.println(userDao.findEntityById(1));
        UserCriteria userCriteria = new UserCriteria();
        userCriteria.setStatus(UserStatus.AVAILABLE);
        System.out.println(userDao.findAllUsersByCriteria(userCriteria));
    }
}
