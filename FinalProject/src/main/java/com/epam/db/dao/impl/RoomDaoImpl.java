package com.epam.db.dao.impl;

import com.epam.criteria.RoomCriteria;
import com.epam.db.ConnectionPool;
import com.epam.db.dao.RoomDao;
import com.epam.entity.*;
import com.epam.exceptions.DaoException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RoomDaoImpl implements RoomDao {
    private final String SQL_SELECT_ALL = "SELECT * FROM Room";
    private final String SQL_SELECT_BY_CRITERIA = "SELECT * FROM Room WHERE ";
    private final String SQL_GET_COUNT = "SELECT COUNT(*) FROM Room";
    private final String SQL_INSERT = "INSERT INTO Room (number_of_seats,price,room_status_fk,room_class_fk) VALUES(";
    private final String SQL_DELETE = "DELETE FROM Room WHERE id = ";
    private final String SQL_UPDATE = "UPDATE Room SET ";

    private Optional<Room> getRoom(ResultSet resultSet) throws SQLException {
        Optional<Room> room = Optional.empty();
        try {
            int id = resultSet.getInt(1);
            int numberOfSeats = resultSet.getInt(2);
            int price = resultSet.getInt(3);
            RoomStatus roomStatus =null;
            if(RoomStatus.extractRoomStatusById(resultSet.getInt(4)).isPresent()){
                roomStatus = RoomStatus.extractRoomStatusById(resultSet.getInt(4)).get();
            }
            RoomClass roomClass = null;
            if(RoomClass.extractRoomClassById(resultSet.getInt(5)).isPresent()){
                roomClass = RoomClass.extractRoomClassById(resultSet.getInt(5)).get();
            }
            if(roomStatus != null && roomClass != null){
                room = Optional.of(new Room(roomClass,price,numberOfSeats,roomStatus,id));
            }
        }catch(DaoException e){
            System.out.println(e.getMessage());
        }
        return room;
    }

    @Override
    public List<Room> findAllEntities() throws SQLException {
        List<Room> list = new ArrayList<>();
        Connection connection = ConnectionPool.getInstance().getConnection();
        try {
            ResultSet resultSet = connection.createStatement().executeQuery(SQL_SELECT_ALL);
            while (resultSet.next()){
                if(getRoom(resultSet).isPresent()){
                    list.add(getRoom(resultSet).get());
                }
            }
        }catch(DaoException e){
            System.out.println(e.getMessage());
        }
        finally {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            connectionPool.close(connection);
        }
        return list;
    }

    @Override
    public Optional<Room> findEntityById(Integer id) throws SQLException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        Optional<Room> room = Optional.empty();
        try {
            ResultSet tempSet = connection.createStatement().executeQuery(SQL_GET_COUNT);
            tempSet.next();
            ResultSet resultSet = connection.createStatement().executeQuery(SQL_SELECT_BY_CRITERIA+"id = "+id);
            if (resultSet.next()) {
                room = getRoom(resultSet);
            }
        }catch(DaoException e){
            System.out.println(e.getMessage());
        }
        finally {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            connectionPool.close(connection);
        }
        return room;
    }

    @Override
    public boolean create(Room room) throws SQLException {
        boolean result = false;
        if(room != null) {
            Connection connection = ConnectionPool.getInstance().getConnection();
            try {
                connection.createStatement().executeUpdate(SQL_INSERT + room.getNumberOfSeats() + ", " + room.getPrice() + ", " + RoomStatus.getIdByRoomStatus(room.getRoomStatus()) + "," + RoomClass.getIdByRoomClass(room.getRoomClass()) + ")");
                result = true;
            } catch (DaoException e) {
                System.out.println(e.getMessage());
            } finally {
                ConnectionPool connectionPool = ConnectionPool.getInstance();
                connectionPool.close(connection);
            }
        }
        return result;
    }

    @Override
    public boolean delete(Integer id) throws SQLException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        boolean result = false;
        try{
            if(findEntityById(id).isPresent()){
                connection.createStatement().executeUpdate(SQL_DELETE+id);
                result = true;
            }
        }catch(DaoException e){
            System.out.println(e.getMessage());
        }finally {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            connectionPool.close(connection);
        }
        return result;
    }

    @Override
    public Optional<Room> update(Room room) throws SQLException {
        Optional<Room> roomOptional = Optional.empty();
        if(room != null) {
            Connection connection = ConnectionPool.getInstance().getConnection();
            try {
                connection.createStatement().executeUpdate(SQL_UPDATE + "number_of_seats = " + room.getNumberOfSeats()
                        + ", price = " + room.getPrice() + ", room_status_fk = " + RoomStatus.getIdByRoomStatus(room.getRoomStatus()) + ", room_class_fk = " + RoomClass.getIdByRoomClass(room.getRoomClass()) +" WHERE id = " + room.getId());
                roomOptional = findEntityById(room.getId());
            } catch (DaoException e) {
                System.out.println(e.getMessage());
            } finally {
                ConnectionPool connectionPool = ConnectionPool.getInstance();
                connectionPool.close(connection);
            }
        }
        return roomOptional;
    }

    @Override
    public List<Room> findAllRoomsByCriteria(RoomCriteria roomCriteria) throws SQLException {
        List<Room> list = new ArrayList<>();
        if(roomCriteria.getRoomClass() != null){
            findAllEntities()
                    .stream()
                    .filter(i -> i.getRoomClass().equals(roomCriteria.getRoomClass()))
                    .forEach(i -> list.add(i));
        }else{
            if(roomCriteria.getRoomStatus()!=null){
                findAllEntities()
                        .stream()
                        .filter(i -> i.getRoomStatus().equals(roomCriteria.getRoomStatus()))
                        .forEach(i -> list.add(i));
            }else{
                if(roomCriteria.getPrice()!=0){
                    findAllEntities()
                            .stream()
                            .filter(i -> i.getPrice() == roomCriteria.getPrice())
                            .forEach(i -> list.add(i));
                }else{
                    if(roomCriteria.getNumberOfSeats()!=0){
                        findAllEntities()
                                .stream()
                                .filter(i -> i.getNumberOfSeats() == roomCriteria.getNumberOfSeats())
                                .forEach(i -> list.add(i));
                    }
                }
            }
        }
        return list;
    }
}
