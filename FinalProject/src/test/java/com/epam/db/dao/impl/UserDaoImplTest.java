package com.epam.db.dao.impl;

import com.epam.criteria.UserCriteria;
import com.epam.db.ConnectionPool;
import com.epam.entity.User;
import com.epam.entity.UserStatus;
import com.epam.exceptions.DaoException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoImplTest {
    private final int id = 1;


    @org.junit.jupiter.api.Test
    void findAllEntities() throws DaoException, SQLException {
        UserDaoImpl userDao = new UserDaoImpl();
        List<User> list = userDao.findAllEntities();
        Connection connection = ConnectionPool.getInstance().getConnection();
        final String COUNT = "SELECT COUNT(*) FROM USER";
        ResultSet resultSet = connection.createStatement().executeQuery(COUNT);
        resultSet.next();
        int count = resultSet.getInt(1);
        assertEquals(count,list.size());
        ConnectionPool.getInstance().close(connection);
    }

    @org.junit.jupiter.api.Test
    void findEntityById() throws DaoException {
        String login = "Admin";
        String password = "admin";
        String name = "Andrey";
        UserDaoImpl userDao = new UserDaoImpl();
        if(userDao.findEntityById(this.id).isPresent()) {
            User user = userDao.findEntityById(this.id).get();
            assertEquals(login,user.getLogin());
            assertEquals(name,user.getName());
            assertEquals(password,user.getPassword());
        }
        assertEquals(Optional.empty(),userDao.findEntityById(100));
    }

    @org.junit.jupiter.api.Test
    void create() throws DaoException {
        String name = "Test";
        String login = "Test001";
        String password = "asd";
        String email = "Test001@mail.ru";
        User user = new UserCriteria.Builder()
                .newBuilder()
                .withLogin(login)
                .withPassword(password)
                .withEmail(email)
                .withName(name)
                .withBalance(100)
                .build();
        UserDaoImpl userDao = new UserDaoImpl();
        UserCriteria userCriteria = new UserCriteria();
        userCriteria.setLogin(login);
        if(userDao.findUserByCriteria(userCriteria).isEmpty()) {
            assertEquals(true, userDao.create(user));
        }else{
            assertEquals(false,userDao.create(user));
        }
    }

    @org.junit.jupiter.api.Test
    void delete() throws DaoException, SQLException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM USER");
        int id = 0;
        while(resultSet.next()){
            id = resultSet.getInt("id");
        }
        UserDaoImpl userDao = new UserDaoImpl();
        if(id != 0 ) {
            assertEquals(true, userDao.delete(id));
            assertEquals(Optional.empty(), userDao.findEntityById(id));
        }else{
            assertEquals(false, userDao.delete(id));
        }
        ConnectionPool.getInstance().close(connection);
    }

    @org.junit.jupiter.api.Test
    void update() throws DaoException {
        UserDaoImpl userDao = new UserDaoImpl();
        if(userDao.findEntityById(this.id).isPresent()) {
            User user = userDao.findEntityById(1).get();
            final String name = "HOFMANNITA";
            final String defaultName = userDao.findEntityById(1).get().getName();
            user.setName(name);
            assertEquals(user,userDao.update(user).get());
            user.setName(defaultName);
            assertEquals(user.getName(),userDao.update(user).get().getName());
        }
    }

    @org.junit.jupiter.api.Test
    void findAllUsersByCriteria() throws DaoException {
        UserCriteria userCriteria = new UserCriteria();
        userCriteria.setStatus(UserStatus.AVAILABLE);
        UserDaoImpl userDao = new UserDaoImpl();
        List<User> list = userDao.findAllEntities();
        list.stream()
                .filter(i -> i.getStatus() != userCriteria.getStatus())
                .forEach(i -> list.remove(i));
        assertEquals(userDao.findAllEntities().size(),userDao.findAllUsersByCriteria(userCriteria).size());
    }

    @org.junit.jupiter.api.Test
    void findUserByCriteria() throws DaoException {
        UserCriteria userCriteria = new UserCriteria();
        UserDaoImpl userDao = new UserDaoImpl();
        if(userDao.findEntityById(this.id).isPresent()){
            User user = userDao.findEntityById(1).get();
            userCriteria.setName(user.getName());
            assertEquals(Optional.empty(),userDao.findUserByCriteria(userCriteria));
            userCriteria = new UserCriteria();
            userCriteria.setLogin(user.getLogin());
            assertEquals(user,userDao.findUserByCriteria(userCriteria).get());
        }
    }
}