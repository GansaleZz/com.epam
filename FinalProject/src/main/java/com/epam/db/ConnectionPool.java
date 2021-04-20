package com.epam.db;

import com.epam.exceptions.DaoException;
import com.epam.util.PropertyReader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;

public final class ConnectionPool {
    private Queue<Connection> availableConnectionList = new LinkedList<>();
    private List<Connection> notAvailableConnectionList = new ArrayList<>();
    Properties properties = PropertyReader.getProperties();
    private static ConnectionPool instance = null;
    private final String URL = properties.getProperty("db.url");
    private final String PASSWORD = properties.getProperty("db.password");
    private final String USER = properties.getProperty("db.user");

    private ConnectionPool() throws DaoException {
        init();
    }

    private void init() throws DaoException{
        try {
            for (int i = 0; i < Integer.valueOf(properties.getProperty("db.initpoolsize")); ++i) {
                Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                availableConnectionList.add(connection);
            }
        }catch(SQLException e){
            throw new DaoException(e);
        }
    }

    public static ConnectionPool getInstance() throws DaoException {
        if(instance == null){
            instance = new ConnectionPool();
        }
        return instance;
    }

    private void addConnection(){
        if(availableConnectionList.size()+notAvailableConnectionList.size()<Integer.valueOf(properties.getProperty("db.maxpoolsize"))) {
            try {
                Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                availableConnectionList.add(connection);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public Optional<Connection> getConnection(){
        Optional<Connection> pool = Optional.empty();
        if(availableConnectionList.size()<=0){
            addConnection();
        }
        if(availableConnectionList.size()>0) {
            pool = Optional.of(availableConnectionList.poll());
            notAvailableConnectionList.add(pool.get());
        }
        return pool;
    }

    public void close(Connection connection){
        notAvailableConnectionList.remove(connection);
        availableConnectionList.add(connection);
        try {
            connection.close();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

}
