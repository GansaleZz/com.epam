package com.epam;

import com.epam.db.ConnectionPool;
import com.epam.db.dao.impl.UserDaoImpl;
import com.epam.entity.User;
import com.epam.entity.UserRole;
import com.epam.entity.UserStatus;
import com.epam.util.PropertyReader;

import java.sql.*;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        UserDaoImpl userDao = new UserDaoImpl();
        if(userDao.findEntityById(10).isPresent()) {
            System.out.println(userDao.findEntityById(10).get());
        }
//        User user = new User("Test3","123","12345","123", UserStatus.extractUserStatusById(1).get(), UserRole.extractUserRolebyId(1).get());
//        System.out.println(userDao.create(user));
        System.out.println(userDao.delete(7));
    }
}
