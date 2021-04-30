package com.epam.db.dao.impl;

import com.epam.criteria.RoomCriteria;
import com.epam.criteria.UserCriteria;
import com.epam.db.ConnectionPool;
import com.epam.entity.*;
import com.epam.exceptions.DaoException;
import com.epam.exceptions.FileException;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class RoomDaoImplTest {
    private final int id = 5;


    @Test
    void findAllEntities() throws FileException, DaoException, SQLException {
        RoomDaoImpl roomDao = new RoomDaoImpl();
        List<Room> list = roomDao.findAllEntities();
        Connection connection = ConnectionPool.getInstance().getConnection().get();
        final String COUNT = "SELECT COUNT(*) FROM ROOM";
        ResultSet resultSet = connection.createStatement().executeQuery(COUNT);
        resultSet.next();
        int count = resultSet.getInt(1);
        assertEquals(count,list.size());
    }

    @Test
    void findEntityById() throws FileException, DaoException {
        int numberOfSeats = 5;
        int price = 140;
        RoomStatus roomStatus = RoomStatus.AVAILABLE;
        RoomDaoImpl roomDao = new RoomDaoImpl();
        if(roomDao.findEntityById(this.id).isPresent()){
            assertEquals(numberOfSeats,roomDao.findEntityById(this.id).get().getNumberOfSeats());
            assertEquals(price,roomDao.findEntityById(this.id).get().getPrice());
            assertEquals(roomStatus,roomDao.findEntityById(this.id).get().getRoomStatus());
        }
        assertEquals(Optional.empty(),roomDao.findEntityById(1240));
    }

    @Test
    void create() throws FileException, DaoException {
        int numberOfSeats = 5;
        int price = 530;
        RoomClass roomClass = RoomClass.LUXE;
        Room room = new Room(roomClass,price,numberOfSeats);
        RoomDaoImpl roomDao = new RoomDaoImpl();
        assertEquals(true,roomDao.create(room));
    }

    @Test
    void delete() throws SQLException, FileException, DaoException {
        Connection connection = ConnectionPool.getInstance().getConnection().get();
        ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM Room");
        int id = 0;
        while(resultSet.next()){
            id = resultSet.getInt("id");
        }
        RoomDaoImpl roomDao = new RoomDaoImpl();
        if(id != 0 ) {
            assertEquals(true, roomDao.delete(id));
            assertEquals(Optional.empty(), roomDao.findEntityById(id));
        }else{
            assertEquals(false, roomDao.delete(id));
        }
    }

    @Test
    void update() throws FileException, DaoException {
        RoomDaoImpl roomDao = new RoomDaoImpl();
        if(roomDao.findEntityById(this.id).isPresent()) {
            Room room = roomDao.findEntityById(1).get();
            final int price = 1390;
            final int defaultPrice = roomDao.findEntityById(1).get().getPrice();
            room.setPrice(price);
            assertEquals(room,roomDao.update(room).get());
            room.setPrice(defaultPrice);
            assertEquals(room.getPrice(),roomDao.update(room).get().getPrice());
        }
    }

    @Test
    void findAllRoomsByCriteria() throws FileException, DaoException {
        RoomCriteria roomCriteria = new RoomCriteria();
        roomCriteria.setRoomStatus(RoomStatus.AVAILABLE);
        RoomDaoImpl roomDao = new RoomDaoImpl();
        List<Room> list = roomDao.findAllEntities();
        int i = 0;
        while(i<list.size()){
            if(!list.get(i).getRoomStatus().equals(roomCriteria.getRoomStatus())){
                list.remove(i);
            }
            i++;
        }
        assertEquals(list.size(),roomDao.findAllRoomsByCriteria(roomCriteria).size());
    }
}