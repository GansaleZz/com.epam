package com.epam.db.dao.impl;

import com.epam.criteria.RoomCriteria;
import com.epam.db.ConnectionPool;
import com.epam.entity.Room;
import com.epam.entity.RoomClass;
import com.epam.entity.RoomStatus;
import com.epam.exceptions.DaoException;
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
    void findAllEntities() throws DaoException, SQLException {
        RoomDaoImpl roomDao = new RoomDaoImpl();
        List<Room> list = roomDao.findAllEntities();
        Connection connection = ConnectionPool.getInstance().getConnection();
        final String COUNT = "SELECT COUNT(*) FROM ROOM";
        ResultSet resultSet = connection.createStatement().executeQuery(COUNT);
        resultSet.next();
        int count = resultSet.getInt(1);
        assertEquals(count,list.size());
        ConnectionPool.getInstance().close(connection);
    }

    @Test
    void findEntityById() throws DaoException {
        int numberOfSeats = 5;
        int price = 140;
        RoomStatus roomStatus = RoomStatus.AVAILABLE;
        RoomDaoImpl roomDao = new RoomDaoImpl();
        if(roomDao.findEntityById(this.id).isPresent()){
            assertEquals(numberOfSeats,roomDao.findEntityById(this.id).get().getNumberOfSeats());
            assertEquals(price,roomDao.findEntityById(this.id).get().getPrice());
            assertEquals(roomStatus,roomDao.findEntityById(this.id).get().getRoomStatus());
            assertEquals(104,roomDao.findEntityById(this.id).get().getRoomNumber());
        }
        assertEquals(Optional.empty(),roomDao.findEntityById(1240));
    }

    @Test
    void create() throws DaoException {
        int numberOfSeats = 5;
        int price = 530;
        int roomNumber = 104;
        RoomClass roomClass = RoomClass.LUXE;
        Room room = new RoomCriteria.Builder()
                .newBuilder()
                .withRoomClass(roomClass)
                .withPrice(price)
                .withNumberOfSeats(numberOfSeats)
                .withRoomNumber(roomNumber)
                .build();
        RoomDaoImpl roomDao = new RoomDaoImpl();
        assertEquals(true,roomDao.create(room));
    }

    @Test
    void delete() throws SQLException, DaoException {
        Connection connection = ConnectionPool.getInstance().getConnection();
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
        ConnectionPool.getInstance().close(connection);
    }

    @Test
    void update() throws DaoException {
        RoomDaoImpl roomDao = new RoomDaoImpl();
            Room room = roomDao.findEntityById(roomDao.findAllEntities().get(roomDao.findAllEntities().size()-1).getId()).get();
            final int price = 1390;
            final int defaultPrice = roomDao.findEntityById(roomDao.findAllEntities().get(roomDao.findAllEntities().size()-1).getId()).get().getPrice();
            room.setPrice(price);
            assertEquals(room,roomDao.update(room).get());
            room.setPrice(defaultPrice);
            assertEquals(room.getPrice(),roomDao.update(room).get().getPrice());
    }

    @Test
    void findAllRoomsByCriteria() throws DaoException {
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