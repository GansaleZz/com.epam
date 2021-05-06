package com.epam.db.dao.impl;

import com.epam.criteria.RequestCriteria;
import com.epam.criteria.RoomCriteria;
import com.epam.db.ConnectionPool;
import com.epam.entity.Request;
import com.epam.entity.Room;
import com.epam.entity.RoomStatus;
import com.epam.entity.User;
import com.epam.exceptions.DaoException;
import com.epam.exceptions.FileException;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class RequestsDaoImplTest {
    private final int id = 1;


    @Test
    void findAllEntities() throws DaoException, SQLException {
        RequestDaoImpl requestDao = new RequestDaoImpl();
        List<Request> list = requestDao.findAllEntities();
        Connection connection = ConnectionPool.getInstance().getConnection();
        final String COUNT = "SELECT COUNT(*) FROM Request";
        ResultSet resultSet = connection.createStatement().executeQuery(COUNT);
        resultSet.next();
        int count = resultSet.getInt(1);
        assertEquals(count,list.size());
    }

    @Test
    void findEntityById() throws DaoException {
        int numberOfSeats = 2;
        UserDaoImpl userDao = new UserDaoImpl();
        User user = userDao.findEntityById(this.id).get();
        RequestDaoImpl requestDao = new RequestDaoImpl();
        assertEquals(numberOfSeats,requestDao.findEntityById(this.id).get().getNumberOfSeats());
        assertEquals(user,requestDao.findEntityById(this.id).get().getUser());
    }

    @Test
    void create() throws DaoException {
        RequestDaoImpl requestDao = new RequestDaoImpl();
        Request request = requestDao.findEntityById(this.id).get();
        request.setNumberOfSeats(5);
        UserDaoImpl userDao = new UserDaoImpl();
        User user = userDao.findEntityById(2).get();
        request.setUser(user);
        assertEquals(true,requestDao.create(request));
    }

    @Test
    void delete() throws DaoException, SQLException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM Request");
        int id = 0;
        while(resultSet.next()){
            id = resultSet.getInt("id");
        }
        RequestDaoImpl requestDao = new RequestDaoImpl();
        if(id != 0 ) {
            assertEquals(true, requestDao.delete(id));
            assertEquals(Optional.empty(), requestDao.findEntityById(id));
        }else{
            assertEquals(false, requestDao.delete(id));
        }
    }

    @Test
    void update() throws DaoException {
        RequestDaoImpl requestDao = new RequestDaoImpl();
        Request request = requestDao.findEntityById(this.id).get();
        final int numberOfSeats = 6;
        final int defaultNumberOfSeats = requestDao.findEntityById(1).get().getNumberOfSeats();
        request.setNumberOfSeats(numberOfSeats);
        assertEquals(request,requestDao.update(request).get());
        request.setNumberOfSeats(defaultNumberOfSeats);
        assertEquals(request,requestDao.update(request).get());
    }

    @Test
    void findAllRequestByCriteria() throws DaoException {
        RequestCriteria requestCriteria = new RequestCriteria();
        requestCriteria.setNumberOfSeats(2);
        RequestDaoImpl requestDao = new RequestDaoImpl();
        List<Request> list = requestDao.findAllEntities();
        int i = 0;
        while(i<list.size()){
            if(!(list.get(i).getNumberOfSeats() == requestCriteria.getNumberOfSeats())){
                list.remove(i);
            }
            i++;
        }
        assertEquals(list.size(),requestDao.findAllRequestsByCriteria(requestCriteria).size());
    }
}