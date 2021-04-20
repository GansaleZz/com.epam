package com.epam.db.dao.impl;

import com.epam.criteria.RequestCriteria;
import com.epam.db.ConnectionPool;
import com.epam.db.dao.RequestDao;
import com.epam.entity.*;
import com.epam.exceptions.DaoException;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RequestDaoImpl implements RequestDao {
    private final String SQL_SELECT_ALL = "SELECT * FROM Request";
    private final String SQL_SELECT_BY_CRITERIA = "SELECT * FROM Request WHERE ";
    private final String SQL_GET_COUNT = "SELECT COUNT(*) FROM Request";
    private final String SQL_INSERT = "INSERT INTO Request (number_of_seats,start_date,end_date,user_id,request_status,room) VALUES(";
    private final String SQL_DELETE = "DELETE FROM Request WHERE id = ";
    private final String SQL_UPDATE = "UPDATE Request SET ";

    private Optional<Request> getRequest(ResultSet resultSet) throws DaoException {
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
           throw new DaoException(e);
        }
        return request;
    }

    @Override
    public List<Request> findAllEntities() throws DaoException {
        List<Request> list = new ArrayList<>();
        Optional<Connection> connection = ConnectionPool.getInstance().getConnection();
        if(connection.isPresent()) {
            try {
                ResultSet resultSet = connection.get().createStatement().executeQuery(SQL_SELECT_ALL);
                while (resultSet.next()) {
                    if (getRequest(resultSet).isPresent()) {
                        list.add(getRequest(resultSet).get());
                    }
                }
            } catch (SQLException e) {
                throw new DaoException(e);
            } finally {
                ConnectionPool connectionPool = ConnectionPool.getInstance();
                connectionPool.close(connection.get());
            }
        }
        return list;
    }

    @Override
    public Optional<Request> findEntityById(Integer id) throws DaoException {
        Optional<Connection> connection = ConnectionPool.getInstance().getConnection();
        Optional<Request> request = Optional.empty();
        if(connection.isPresent()) {
            try {
                ResultSet tempSet = connection.get().createStatement().executeQuery(SQL_GET_COUNT);
                tempSet.next();
                ResultSet resultSet = connection.get().createStatement().executeQuery(SQL_SELECT_BY_CRITERIA + "id = " + id);
                if (resultSet.next()) {
                    request = getRequest(resultSet);
                }
            } catch (SQLException e) {
                throw new DaoException(e);
            } finally {
                ConnectionPool connectionPool = ConnectionPool.getInstance();
                connectionPool.close(connection.get());
            }
        }
        return request;
    }

    @Override
    public boolean create(Request request) throws DaoException {
        boolean result = false;
        if(request != null) {
            Optional<Connection> connection = ConnectionPool.getInstance().getConnection();
            if(connection.isPresent()) {
                try {
                    connection.get().createStatement().executeUpdate(SQL_INSERT + request.getNumberOfSeats() + "," + request.getStart() + "," + request.getEnd() + "," + request.getUser().getId() + "," + RequestStatus.getIdByRequestStatus(request.getRequestStatus()) + "," + request.getRoom().getId() + ")");
                    result = true;
                } catch (SQLException e) {
                    throw new DaoException(e);
                } finally {
                    ConnectionPool connectionPool = ConnectionPool.getInstance();
                    connectionPool.close(connection.get());
                }
            }
        }
        return result;
    }

    @Override
    public boolean delete(Integer id) throws DaoException {
        Optional<Connection> connection = ConnectionPool.getInstance().getConnection();
        boolean result = false;
        if(connection.isPresent()) {
            try {
                if (findEntityById(id).isPresent()) {
                    connection.get().createStatement().executeUpdate(SQL_DELETE + id);
                    result = true;
                }
            } catch (SQLException e) {
                throw new DaoException(e);
            } finally {
                ConnectionPool connectionPool = ConnectionPool.getInstance();
                connectionPool.close(connection.get());
            }
        }
        return result;
    }

    @Override
    public Optional<Request> update(Request request) throws DaoException {
        Optional<Request> requestOptional = Optional.empty();
        if(request != null) {
            Optional<Connection> connection = ConnectionPool.getInstance().getConnection();
            if(connection.isPresent()) {
                try {
                    connection.get().createStatement().executeUpdate(SQL_UPDATE + "number_of_seats = " + request.getNumberOfSeats()
                            + ", start_date = " + request.getStart() + ", end_date = " + request.getEnd() + ", user_id = " + request.getUser().getId() + ", request_status = " + RequestStatus.getIdByRequestStatus(request.getRequestStatus()) + ", room = " + request.getRoom().getId() + " WHERE id = " + request.getId());
                    requestOptional = findEntityById(request.getId());
                } catch (SQLException e) {
                    throw new DaoException(e);
                } finally {
                    ConnectionPool connectionPool = ConnectionPool.getInstance();
                    connectionPool.close(connection.get());
                }
            }
        }
        return requestOptional;
    }


    @Override
    public List<Request> findAllRequestByCriteria(RequestCriteria requestCriteria) throws DaoException {
        List<Request> list = new ArrayList<>();
        if(requestCriteria.getRequestStatus() != null) {
            findAllEntities()
                    .stream()
                    .filter(i -> i.getRequestStatus() == requestCriteria.getRequestStatus())
                    .forEach(i -> list.add(i));
        }else{
            if(requestCriteria.getNumberOfSeats()!=0) {
                findAllEntities()
                        .stream()
                        .filter(i -> i.getNumberOfSeats() == requestCriteria.getNumberOfSeats())
                        .forEach(i -> list.add(i));
            }
        }
        return list;
    }
}
