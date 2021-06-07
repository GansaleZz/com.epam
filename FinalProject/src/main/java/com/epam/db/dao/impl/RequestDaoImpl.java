package com.epam.db.dao.impl;

import com.epam.criteria.RequestCriteria;
import com.epam.db.ConnectionPool;
import com.epam.db.dao.RequestDao;
import com.epam.entity.Request;
import com.epam.entity.RequestStatus;
import com.epam.entity.RoomClass;
import com.epam.exceptions.DaoException;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;


/**
 * Realisation of dao pattern for {@link Request}
 *
 * @author Andrey Rubin
 */
public class RequestDaoImpl implements RequestDao {
    private final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(RequestDaoImpl.class);
    /**
     * Set of SQL queries
     */
    private final String SQL_SELECT_ALL = "SELECT * FROM Request";
    private final String SQL_SELECT_BY_CRITERIA = "SELECT * FROM Request WHERE ";
    private final String SQL_INSERT = "INSERT INTO Request (number_of_seats,start_date,end_date,user_id,request_status,room) VALUES(?,?,?,?,?,?)";
    private final String SQL_INSERT_WITHOUT_ROOM = "INSERT INTO Request (number_of_seats,start_date,end_date,user_id,request_status,room_class) VALUES(?,?,?,?,?,?)";
    private final String SQL_DELETE = "DELETE FROM Request WHERE id = ";
    private final String SQL_UPDATE = "UPDATE Request SET number_of_seats = ?, start_date = ?, end_date = ?, user_id = ?, request_status = ?, room = ? WHERE id = ?";
    private final String SQL_UPDATE_WITHOUT_ROOM = "UPDATE Request SET number_of_seats = ?, start_date = ?, end_date = ?, user_id = ?, request_status = ?, room_class = ? WHERE id = ?";
    private final String SQL_UPDATE_WITH_PAYMENT = "UPDATE Request SET number_of_seats = ?, start_date = ?, end_date = ?, user_id = ?, request_status = ?, room = ?, request_payment = ? WHERE id = ?";

    /**
     * Method, which finds all requests on db
     * @return list of requests from db
     */
    @Override
    public List<Request> findAllEntities() throws DaoException{
        List<Request> list = new ArrayList<>();
        Connection connection = ConnectionPool.getInstance().getConnection();
        try {
            ResultSet resultSet = connection.createStatement().executeQuery(SQL_SELECT_ALL);
            while (resultSet.next()) {
                if (getRequest(resultSet).isPresent()) {
                    list.add(getRequest(resultSet).get());
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
     * Method, which use to find request on db by id, if its exists
     * @param id unique parameter, need to find request on db
     * @return request from db, if its exists
     */
    @Override
    public Optional<Request> findEntityById(Integer id) throws DaoException{
        Connection connection = ConnectionPool.getInstance().getConnection();
        Optional<Request> request = Optional.empty();
        try {
            ResultSet resultSet = connection.createStatement().executeQuery(SQL_SELECT_BY_CRITERIA + "id = " + id);
            if (resultSet.next()) {
                request = getRequest(resultSet);
            }
            resultSet.close();
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            connectionPool.close(connection);
        }
        return request;
    }

    /**
     * Method which creates request on db
     * @param request - it is argument, which information will be taken
     * for creating
     * @return boolean, that indicates success of creating of request
     */
    @Override
    public boolean create(Request request) throws DaoException{
        boolean result = false;
        if(request != null) {
            Connection connection = ConnectionPool.getInstance().getConnection();
            try {
                PreparedStatement preparedStatement;
                if(request.getRoom() == null ) {
                    preparedStatement = connection.prepareStatement(SQL_INSERT_WITHOUT_ROOM);
                    preparedStatement.setInt(6, RoomClass.getIdByRoomClass(request.getRoomClass()));
                }else{
                    preparedStatement = connection.prepareStatement(SQL_INSERT);
                    preparedStatement.setInt(6, request.getRoom().getId());
                }
                prepStmnt(request, preparedStatement);
                result = true;
                logger.info(request + " successfully created!");
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
     * Method, which use to deleting request from db by id
     * @param id - parameter that need to search request on db
     * @return boolean, that indicates success of deleting  (true - if request
     * with id exists, not - false)
     */
    @Override
    public boolean delete(Integer id) throws DaoException{
        Connection connection = ConnectionPool.getInstance().getConnection();
        boolean result = false;
        try {
            if (findEntityById(id).isPresent()) {
                logger.info(findEntityById(id) + " successfully deleted!");
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
     * Method, which use to update information of request on db
     * @param request its argument with new information
     * @return updated request
     */
    @Override
    public Optional<Request> update(Request request) throws DaoException{
        Optional<Request> requestOptional = Optional.empty();
        if(request != null) {
            Connection connection = ConnectionPool.getInstance().getConnection();
            try {
                PreparedStatement preparedStatement;
                if(request.getRoom() != null) {
                    if (request.getPayment() == null) {
                        preparedStatement = connection.prepareStatement(SQL_UPDATE);
                        preparedStatement.setInt(6,request.getRoom().getId());
                        preparedStatement.setInt(7,request.getId());
                    }else{
                        preparedStatement = connection.prepareStatement(SQL_UPDATE_WITH_PAYMENT);
                        preparedStatement.setInt(6, request.getRoom().getId());
                        preparedStatement.setInt(7,request.getPayment().getId());
                        preparedStatement.setInt(8,request.getId());
                    }
                }else {
                    preparedStatement = connection.prepareStatement(SQL_UPDATE_WITHOUT_ROOM);
                    preparedStatement.setInt(6, RoomClass.getIdByRoomClass(request.getRoomClass()));
                    preparedStatement.setInt(7,request.getId());
                }
                prepStmnt(request, preparedStatement);
                requestOptional = findEntityById(request.getId());
                logger.info(request + " successfully updated!");
            } catch (SQLException e) {
                logger.error(e.getMessage());
            } finally {
                ConnectionPool connectionPool = ConnectionPool.getInstance();
                connectionPool.close(connection);
            }
        }
        return requestOptional;
    }

    /**
     * Method that use to find all requests on db by special criteria
     * @param requestCriteria - parameter by which requests are searching on db
     * @return list of requests with a specific criteria
     */
    @Override
    public List<Request> findAllRequestsByCriteria(RequestCriteria requestCriteria) throws DaoException{
        List<Request> list = new ArrayList<>();
        if(requestCriteria.getRequestStatus() != null) {
            list = chooseAllByPredicate(i -> ((Request)i).getRequestStatus() == requestCriteria.getRequestStatus());
        }else{
            if(requestCriteria.getNumberOfSeats()!=0) {
                list = chooseAllByPredicate(i -> ((Request)i).getNumberOfSeats() == requestCriteria.getNumberOfSeats());
            }
        }
        return list;
    }

    /**
     * Method that saving code from duplicates on method findAllRequestsByCriteria,
     * using for find requests on db by criteria
     * @param predicate - it's criteria by which we are searching requests on db
     * @return list of requests with a specific criteria
     */
    private List<Request> chooseAllByPredicate(Predicate predicate) throws DaoException {
        List<Request> list = new ArrayList<>();
        findAllEntities()
                .stream()
                .filter(predicate)
                .forEach(i -> list.add((Request) i));
        return list;
    }

    /**
     * Method that saving code from duplicates, using to set general
     * parameters for SQL queries and execute it
     * @param request argument, from which will be taken parameters
     * @param preparedStatement statement on which setting parameters
     */
    private void prepStmnt(Request request, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setInt(1, request.getNumberOfSeats());
        preparedStatement.setDate(2, new Date(request.getStart().getTime()));
        preparedStatement.setDate(3, new Date(request.getEnd().getTime()));
        preparedStatement.setInt(4, request.getUser().getId());
        preparedStatement.setInt(5, RequestStatus.getIdByRequestStatus(request.getRequestStatus()));
        preparedStatement.execute();
        preparedStatement.close();
    }

    /**
     * Method which saving code from duplicates
     * @param resultSet - argument, which keeps result of executing of SQL query
     * @return inited request by information from db
     */
    private Optional<Request> getRequest(ResultSet resultSet) throws DaoException{
        Optional<Request> request = Optional.empty();
        try {
            int id = resultSet.getInt(1);
            int numberOfSeats = resultSet.getInt(2);
            Date start = resultSet.getDate(3);
            Date end = resultSet.getDate(4);
            int userId = resultSet.getInt(5);
            RequestStatus requestStatus = null;
            if(RequestStatus.extractRequestStatusById(resultSet.getInt(6)).isPresent()){
                requestStatus = RequestStatus.extractRequestStatusById(resultSet.getInt(6)).get();
            }
            int roomId = resultSet.getInt(7);
            int roomClass = 0;
            if (roomId == 0 ){
                roomClass = resultSet.getInt(8);
            }
            UserDaoImpl userDao = new UserDaoImpl();
            RoomDaoImpl roomDao = new RoomDaoImpl();
            if(userDao.findEntityById(userId).isPresent()) {
                request = Optional.of(new RequestCriteria.Builder()
                .newBuilder()
                .withNumberOfSeats(numberOfSeats)
                .withStart(start)
                .withEnd(end)
                .withUser(userDao.findEntityById(userId).get())
                .withId(id)
                .withRequestStatus(requestStatus)
                .build());
                if(roomId == 0){
                    request.get().setRoomClass(RoomClass.extractRoomClassById(roomClass).get());
                }else{
                    request.get().setRoom(roomDao.findEntityById(roomId).get());
                }
            }
        }catch(SQLException e){
            logger.error(e.getMessage());
        }
        return request;
    }

}
