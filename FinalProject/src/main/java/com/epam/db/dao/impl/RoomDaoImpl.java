package com.epam.db.dao.impl;

import com.epam.criteria.RoomCriteria;
import com.epam.db.ConnectionPool;
import com.epam.db.dao.RoomDao;
import com.epam.entity.*;
import com.epam.exceptions.DaoException;
import com.epam.exceptions.FileException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class RoomDaoImpl implements RoomDao {
    private final String SQL_SELECT_ALL = "SELECT * FROM Room";
    private final String SQL_SELECT_BY_CRITERIA = "SELECT * FROM Room WHERE ";
    private final String SQL_INSERT = "INSERT INTO Room (number_of_seats,price,room_status_fk,room_class_fk) VALUES(";
    private final String SQL_DELETE = "DELETE FROM Room WHERE id = ";
    private final String SQL_UPDATE = "UPDATE Room SET ";
    private final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(RoomDaoImpl.class);


    @Override
    public List<Room> findAllEntities() throws DaoException{
        List<Room> list = new ArrayList<>();
        Optional<Connection> connection = ConnectionPool.getInstance().getConnection();
        if(connection.isPresent()) {
            try {
                ResultSet resultSet = connection.get().createStatement().executeQuery(SQL_SELECT_ALL);
                while (resultSet.next()) {
                    if (getRoom(resultSet).isPresent()) {
                        list.add(getRoom(resultSet).get());
                    }
                }
            } catch (SQLException e) {
                logger.error(e.getMessage());
                throw new DaoException(e);
            } finally {
                ConnectionPool connectionPool = ConnectionPool.getInstance();
                connectionPool.close(connection.get());
                try {
                    connection.get().close();
                }catch(SQLException e){
                    throw new DaoException(e);
                }
            }
        }
        return list;
    }

    @Override
    public Optional<Room> findEntityById(Integer id) throws DaoException{
        Optional<Connection> connection = ConnectionPool.getInstance().getConnection();
        Optional<Room> room = Optional.empty();
        if(connection.isPresent()) {
            try {
                ResultSet resultSet = connection.get().createStatement().executeQuery(SQL_SELECT_BY_CRITERIA + "id = " + id);
                if (resultSet.next()) {
                    room = getRoom(resultSet);
                }
            } catch (SQLException e) {
                logger.error(e.getMessage());
                throw new DaoException(e);
            } finally {
                ConnectionPool connectionPool = ConnectionPool.getInstance();
                connectionPool.close(connection.get());
                try {
                    connection.get().close();
                }catch(SQLException e){
                    throw new DaoException(e);
                }
            }
        }
        return room;
    }

    @Override
    public boolean create(Room room) throws DaoException{
        boolean result = false;
        if(room != null) {
            Optional<Connection> connection = ConnectionPool.getInstance().getConnection();
            if(connection.isPresent()) {
                try {
                    connection.get().createStatement().executeUpdate(SQL_INSERT + room.getNumberOfSeats() + ", " + room.getPrice() + ", " + RoomStatus.getIdByRoomStatus(RoomStatus.AVAILABLE) + "," + RoomClass.getIdByRoomClass(room.getRoomClass()) + ")");
                    result = true;
                    logger.info(room + " successfully created!");
                } catch (SQLException e) {
                    logger.error(e.getMessage());
                    throw new DaoException(e);
                } finally {
                    ConnectionPool connectionPool = ConnectionPool.getInstance();
                    connectionPool.close(connection.get());
                    try {
                        connection.get().close();
                    }catch(SQLException e){
                        throw new DaoException(e);
                    }
                }
            }
        }
        return result;
    }

    @Override
    public boolean delete(Integer id) throws DaoException{
        Optional<Connection> connection = ConnectionPool.getInstance().getConnection();
        boolean result = false;
        if(connection.isPresent()) {
            try {
                if (findEntityById(id).isPresent()) {
                    logger.info(findEntityById(id).get() + " successfully deleted");
                    connection.get().createStatement().executeUpdate(SQL_DELETE + id);
                    result = true;
                }
            } catch (SQLException e) {
                logger.error(e.getMessage());
                throw new DaoException(e);
            } finally {
                ConnectionPool connectionPool = ConnectionPool.getInstance();
                connectionPool.close(connection.get());
                try {
                    connection.get().close();
                }catch(SQLException e){
                    throw new DaoException(e);
                }
            }
        }
        return result;
    }

    @Override
    public Optional<Room> update(Room room) throws DaoException{
        Optional<Room> roomOptional = Optional.empty();
        if(room != null) {
            Optional<Connection> connection = ConnectionPool.getInstance().getConnection();
            if(connection.isPresent()) {
                try {
                    connection.get().createStatement().executeUpdate(SQL_UPDATE + "number_of_seats = " + room.getNumberOfSeats()
                            + ", price = " + room.getPrice() + ", room_status_fk = " + RoomStatus.getIdByRoomStatus(room.getRoomStatus()) + ", room_class_fk = " + RoomClass.getIdByRoomClass(room.getRoomClass()) + " WHERE id = " + room.getId());
                    roomOptional = findEntityById(room.getId());
                    logger.info(room + " successfully updated!");
                } catch (SQLException e) {
                    logger.error(e.getMessage());
                    throw new DaoException(e);
                } finally {
                    ConnectionPool connectionPool = ConnectionPool.getInstance();
                    connectionPool.close(connection.get());
                    try {
                        connection.get().close();
                    }catch(SQLException e){
                        throw new DaoException(e);
                    }
                }
            }
        }
        return roomOptional;
    }

    @Override
    public List<Room> findAllRoomsByCriteria(RoomCriteria roomCriteria) throws DaoException{
        List<Room> list = new ArrayList<>();
        if(roomCriteria.getRoomClass() != null){
            list = chooseAllByPredicate(i -> ((Room)i).getRoomClass().equals(roomCriteria.getRoomClass()));
        }else{
            if(roomCriteria.getRoomStatus()!=null){
                list = chooseAllByPredicate(i -> ((Room)i).getRoomStatus().equals(roomCriteria.getRoomStatus()));
            }else{
                if(roomCriteria.getPrice()!=0){
                    list = chooseAllByPredicate(i -> ((Room)i).getPrice() == roomCriteria.getPrice());
                }else{
                    if(roomCriteria.getNumberOfSeats()!=0){
                        list = chooseAllByPredicate(i -> ((Room)i).getNumberOfSeats() == roomCriteria.getNumberOfSeats());
                    }
                }
            }
        }
        return list;
    }

    private List<Room> chooseAllByPredicate(Predicate predicate) throws DaoException {
        List<Room> list = new ArrayList<>();
        findAllEntities()
                .stream()
                .filter(predicate)
                .forEach(i -> list.add((Room) i));
        return list;
    }

    private Optional<Room> getRoom(ResultSet resultSet) throws DaoException {
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
        }catch(SQLException e){
            logger.error(e.getMessage());
            throw new DaoException(e);
        }
        return room;
    }
}
