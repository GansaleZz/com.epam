package com.epam;

import com.epam.criteria.UserCriteria;
import com.epam.db.ConnectionPool;
import com.epam.db.dao.RoomDao;
import com.epam.db.dao.impl.RoomDaoImpl;
import com.epam.db.dao.impl.UserDaoImpl;
import com.epam.entity.*;
import com.epam.util.PropertyReader;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
//        UserDaoImpl userDao = new UserDaoImpl();
//        User user = new User("maggy63","1234","1234a5","Maggy", UserStatus.extractUserStatusById(1).get(), UserRole.extractUserRolebyId(1).get());
//        UserCriteria userCriteria = new UserCriteria();
//        userCriteria.setRole(UserRole.CLIENT);
//        System.out.println(userDao.findAllUsersByCriteria(userCriteria));
        RoomDaoImpl roomDao = new RoomDaoImpl();
        System.out.println(roomDao.findAllEntities());
        System.out.println(roomDao.findEntityById(2));
        Room room = new Room(RoomClass.BUSINESS,140,5,RoomStatus.AVAILABLE);
        room.setId(10);
        roomDao.update(room);
        System.out.println(roomDao.findEntityById(10));
        List<Room> list = new ArrayList<>();
        list.add(null);
//        System.out.println(roomDao.create(room));
//        System.out.println(roomDao.findEntityById(2));
//        System.out.println(roomDao.delete(2));
//        System.out.println(roomDao.findAllEntities());
    }
}
