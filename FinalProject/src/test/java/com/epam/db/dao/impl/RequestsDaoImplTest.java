package com.epam.db.dao.impl;

import com.epam.criteria.impl.RequestCriteria;
import com.epam.db.ConnectionPool;
import com.epam.entity.Request;
import com.epam.entity.RequestStatus;
import com.epam.entity.RoomClass;
import com.epam.entity.User;
import com.epam.exceptions.DaoException;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class RequestsDaoImplTest {

    @Order(1)
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
        ConnectionPool.getInstance().close(connection);
    }

    @Order(3)
    @Test
    void findEntityById() throws DaoException {
        int numberOfSeats = 1;
        RequestDaoImpl requestDao = new RequestDaoImpl();
        List<Request> list = requestDao.findAllEntities();
        assertEquals(numberOfSeats,requestDao.findEntityById(list.get(list.size()-1).getId()).get().getNumberOfSeats());
    }

    @Order(2)
    @Test
    void create() throws DaoException {
        RequestDaoImpl requestDao = new RequestDaoImpl();
        User user = new UserDaoImpl().findEntityById(3).get();
        Request request = new RequestCriteria.Builder()
                .newBuilder()
                .withNumberOfSeats(1)
                .withStart(Calendar.getInstance().getTime())
                .withEnd(Calendar.getInstance().getTime())
                .withUser(user)
                .withRequestStatus(RequestStatus.PAID)
                .withRoomClass(RoomClass.BUSINESS)
                .withId(0)
                .build();
        assertTrue(requestDao.create(request));
    }

    @Order(6)
    @Test
    void delete() throws DaoException, SQLException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        RequestDaoImpl requestDao = new RequestDaoImpl();
        List<Request> list = requestDao.findAllEntities();
        int id = list.get(list.size()-1).getId() + 1;
        assertTrue(requestDao.delete(list.get(list.size()-1).getId()));
        assertEquals(Optional.empty(), requestDao.findEntityById(id));
        assertFalse(requestDao.delete(id));
        ConnectionPool.getInstance().close(connection);
    }

    @Order(5)
    @Test
    void update() throws DaoException {
        RequestDaoImpl requestDao = new RequestDaoImpl();
        List<Request> list = requestDao.findAllEntities();
        int id = list.get(list.size()-1).getId();
        Request request = requestDao.findEntityById(id).get();
        int numberOfSeats = 6;
        int defaultNumberOfSeats = requestDao.findEntityById(id).get().getNumberOfSeats();
        request.setNumberOfSeats(numberOfSeats);
        assertEquals(request,requestDao.update(request).get());
        request.setNumberOfSeats(defaultNumberOfSeats);
        assertEquals(request,requestDao.update(request).get());
    }

    @Order(4)
    @Test
    void findAllRequestByCriteria() throws DaoException {
        RequestCriteria requestCriteria = new RequestCriteria();
        requestCriteria.setNumberOfSeats(1);
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