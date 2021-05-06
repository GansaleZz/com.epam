package com.epam.db;

import com.epam.db.dao.impl.UserDaoImpl;
import com.epam.exceptions.DaoException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConnectionPoolTest {

    @Test
    void close() throws DaoException {
        UserDaoImpl userDao = new UserDaoImpl();
        for(int i = 0; i<40; ++i){
            userDao.findAllEntities();
        }
    }
}