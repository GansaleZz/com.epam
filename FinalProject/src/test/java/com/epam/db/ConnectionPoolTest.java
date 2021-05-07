package com.epam.db;

import com.epam.db.dao.impl.UserDaoImpl;
import com.epam.exceptions.DaoException;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

class ConnectionPoolTest {

    @Test
    void close() throws DaoException {
        UserDaoImpl userDao = new UserDaoImpl();
        for(int i = 0; i<40; ++i){
            userDao.findAllEntities();
        }
    }

    @Test
    void getConnection() {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        for(int i = 0;i<65;i++){
            if(i == 30 || i == 60 ){
                connectionPool.closeAllConnections();
            }
            connectionPool.getConnection();
        }
    }
}