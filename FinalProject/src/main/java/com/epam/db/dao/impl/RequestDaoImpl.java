package com.epam.db.dao.impl;

import com.epam.criteria.RequestCriteria;
import com.epam.db.ConnectionPool;
import com.epam.db.dao.RequestDao;
import com.epam.entity.Request;
import com.epam.entity.RequestStatus;
import com.epam.exceptions.DaoException;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class RequestDaoImpl implements RequestDao {
    private final String SQL_SELECT_ALL = "SELECT * FROM Request";
    private final String SQL_SELECT_BY_CRITERIA = "SELECT * FROM Request WHERE ";
    private final String SQL_INSERT = "INSERT INTO Request (number_of_seats,start_date,end_date,user_id,request_status,room) VALUES(";
    private final String SQL_DELETE = "DELETE FROM Request WHERE id = ";
    private final String SQL_UPDATE = "UPDATE Request SET ";
    private final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(RequestDaoImpl.class);


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
            throw new DaoException(e);
        } finally {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            connectionPool.close(connection);
        }
        return list;
    }

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
            throw new DaoException(e);
        } finally {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            connectionPool.close(connection);
        }
        return request;
    }

    @Override
    public boolean create(Request request) throws DaoException{
        boolean result = false;
        if(request != null) {
            Connection connection = ConnectionPool.getInstance().getConnection();
            try {
                connection.createStatement().executeUpdate(SQL_INSERT + request.getNumberOfSeats() + ",'" + request.getStart() + "','" + request.getEnd() + "'," + request.getUser().getId() + "," + RequestStatus.getIdByRequestStatus(request.getRequestStatus()) + "," + request.getRoom().getId() + ")");
                result = true;
                logger.info(request + " successfully created!");
            } catch (SQLException e) {
                logger.error(e.getMessage());
                throw new DaoException(e);
            } finally {
                ConnectionPool connectionPool = ConnectionPool.getInstance();
                connectionPool.close(connection);
            }
        }
        return result;
    }

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
            throw new DaoException(e);
        } finally {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            connectionPool.close(connection);
        }
        return result;
    }

    @Override
    public Optional<Request> update(Request request) throws DaoException{
        Optional<Request> requestOptional = Optional.empty();
        if(request != null) {
            Connection connection = ConnectionPool.getInstance().getConnection();
            try {
                connection.createStatement().executeUpdate(SQL_UPDATE + "number_of_seats = " + request.getNumberOfSeats()
                        + ", start_date = '" + request.getStart() + "', end_date = '" + request.getEnd() + "', user_id = " + request.getUser().getId() + ", request_status = " + RequestStatus.getIdByRequestStatus(request.getRequestStatus()) + ", room = " + request.getRoom().getId() + " WHERE id = " + request.getId());
                requestOptional = findEntityById(request.getId());
                logger.info(request + " successfully updated!");
            } catch (SQLException e) {
                logger.error(e.getMessage());
                throw new DaoException(e);
            } finally {
                ConnectionPool connectionPool = ConnectionPool.getInstance();
                connectionPool.close(connection);
            }
        }
        return requestOptional;
    }


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

    private List<Request> chooseAllByPredicate(Predicate predicate) throws DaoException {
        List<Request> list = new ArrayList<>();
        findAllEntities()
                .stream()
                .filter(predicate)
                .forEach(i -> list.add((Request) i));
        return list;
    }

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
            UserDaoImpl userDao = new UserDaoImpl();
            RoomDaoImpl roomDao = new RoomDaoImpl();
            if(userDao.findEntityById(userId).isPresent() && roomDao.findEntityById(roomId).isPresent()) {
                request = Optional.of(new Request(numberOfSeats, start, end, userDao.findEntityById(userId).get(), id, requestStatus, roomDao.findEntityById(roomId).get()));
            }
        }catch(SQLException e){
            logger.error(e.getMessage());
            throw new DaoException(e);
        }
        return request;
    }
}
