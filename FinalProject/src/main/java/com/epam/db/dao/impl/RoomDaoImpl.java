package com.epam.db.dao.impl;

import com.epam.criteria.impl.RoomCriteria;
import com.epam.db.ConnectionPool;
import com.epam.db.dao.RoomDao;
import com.epam.entity.Room;
import com.epam.entity.RoomClass;
import com.epam.entity.RoomStatus;
import com.epam.exceptions.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;


/**
 * Realisation of dao pattern for {@link Room}
 *
 * @author Andrey Rubin
 */
public class RoomDaoImpl implements RoomDao {
    private final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(RoomDaoImpl.class);
    /**
     * Set of SQL queries
     */
    private final String SQL_SELECT_ALL = "SELECT * FROM Room";
    private final String SQL_SELECT_BY_CRITERIA = "SELECT * FROM Room WHERE ";
    private final String SQL_INSERT = "INSERT INTO Room (number_of_seats,price,room_status_fk,room_class_fk,room_number) VALUES(?,?,?,?,?)";
    private final String SQL_DELETE = "DELETE FROM Room WHERE id = ";
    private final String SQL_UPDATE = "UPDATE Room SET number_of_seats = ?, price = ?, room_status_fk = ?, room_class_fk = ?, room_number = ? WHERE id = ?";

    /**
     * Method, which finds all rooms on db
     * @return list of rooms from db
     */
    @Override
    public List<Room> findAllEntities() throws DaoException{
        List<Room> list = new ArrayList<>();
        Connection connection = ConnectionPool.getInstance().getConnection();
        try {
            ResultSet resultSet = connection.createStatement().executeQuery(SQL_SELECT_ALL);
            while (resultSet.next()) {
                if (getRoom(resultSet).isPresent()) {
                    list.add(getRoom(resultSet).get());
                }
            }
            resultSet.close();
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            connectionPool.close(connection);
        }
        return list;
    }

    /**
     * Method, which use to find room on db by id, if its exists
     * @param id unique parameter, need to find room on db
     * @return room from db, if its exists
     */
    @Override
    public Optional<Room> findEntityById(Integer id) throws DaoException{
        Connection connection = ConnectionPool.getInstance().getConnection();
        Optional<Room> room = Optional.empty();
        try {
            ResultSet resultSet = connection.createStatement().executeQuery(SQL_SELECT_BY_CRITERIA + "id = " + id);
            if (resultSet.next()) {
                room = getRoom(resultSet);
            }
            resultSet.close();
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            connectionPool.close(connection);
        }
        return room;
    }

    /**
     * Method which creates room on db
     * @param room - it is argument, which information will be taken
     * for creating
     * @return boolean, that indicates success of creating of room
     */
    @Override
    public boolean create(Room room) throws DaoException{
        boolean result = false;
        if(room != null) {
            Connection connection = ConnectionPool.getInstance().getConnection();
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT);
                preparedStatement.setInt(1,room.getNumberOfSeats());
                preparedStatement.setInt(2,room.getPrice());
                preparedStatement.setInt(3,RoomStatus.getIdByRoomStatus(RoomStatus.AVAILABLE));
                preparedStatement.setInt(4,RoomClass.getIdByRoomClass(room.getRoomClass()));
                preparedStatement.setInt(5,room.getRoomNumber());
                preparedStatement.execute();
                preparedStatement.close();
                result = true;
                logger.info(room + " successfully created!");
            } catch (SQLException e) {
                logger.error(e.getMessage());
            } finally {
                ConnectionPool connectionPool = ConnectionPool.getInstance();
                connectionPool.close(connection);
            }
        }
        return result;
    }

    /**
     * Method, which use to deleting room from db by id
     * @param id - parameter that need to search room on db
     * @return boolean, that indicates success of deleting  (true - if room
     * with id exists, not - false)
     */
    @Override
    public boolean delete(Integer id) throws DaoException{
        Connection connection = ConnectionPool.getInstance().getConnection();
        boolean result = false;
        try {
            if (findEntityById(id).isPresent()) {
                logger.info(findEntityById(id).get() + " successfully deleted");
                connection.createStatement().executeUpdate(SQL_DELETE + id);
                result = true;
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            connectionPool.close(connection);
        }
        return result;
    }

    /**
     * Method, which use to update information of room on db
     * @param room its argument with new information
     * @return updated room
     */
    @Override
    public Optional<Room> update(Room room) throws DaoException{
        Optional<Room> roomOptional = Optional.empty();
        if(room != null) {
            Connection connection = ConnectionPool.getInstance().getConnection();
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE);
                preparedStatement.setInt(1,room.getNumberOfSeats());
                preparedStatement.setInt(2,room.getPrice());
                preparedStatement.setInt(3,RoomStatus.getIdByRoomStatus(room.getRoomStatus()));
                preparedStatement.setInt(4,RoomClass.getIdByRoomClass(room.getRoomClass()));
                preparedStatement.setInt(5,room.getRoomNumber());
                preparedStatement.setInt(6,room.getId());
                preparedStatement.execute();
                preparedStatement.close();
                roomOptional = findEntityById(room.getId());
                logger.info(room + " successfully updated!");
            } catch (SQLException e) {
                logger.error(e.getMessage());
            } finally {
                ConnectionPool connectionPool = ConnectionPool.getInstance();
                connectionPool.close(connection);
            }
        }
        return roomOptional;
    }

    /**
     * Method that use to find all rooms on db by special criteria
     * @param roomCriteria - parameter by which rooms are searching on db
     * @return list of rooms with a specific criteria
     */
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

    /**
     * Method that saving code from duplicates on method findAllRoomsByCriteria,
     * using for find rooms on db by criteria
     * @param predicate - it's criteria by which we are searching rooms on db
     * @return list of rooms with a specific criteria
     */
    private List<Room> chooseAllByPredicate(Predicate predicate) throws DaoException {
        List<Room> list = new ArrayList<>();
        findAllEntities()
                .stream()
                .filter(predicate)
                .forEach(i -> list.add((Room) i));
        return list;
    }

    /**
     * Method which saving code from duplicates
     * @param resultSet - argument, which keeps result of executing of SQL query
     * @return inited room by information from db
     */
    private Optional<Room> getRoom(ResultSet resultSet) {
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
            int roomNumber = resultSet.getInt(6);
            if(roomStatus != null && roomClass != null){
                room = Optional.of(new RoomCriteria.Builder()
                .newBuilder()
                .withId(id)
                .withRoomStatus(roomStatus)
                .withRoomClass(roomClass)
                .withPrice(price)
                .withNumberOfSeats(numberOfSeats)
                .withRoomNumber(roomNumber)
                .build());
            }
        }catch(SQLException e){
            logger.error(e.getMessage());
        }
        return room;
    }
}
