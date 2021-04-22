package com.epam.db;

import com.epam.exceptions.DaoException;
import com.epam.exceptions.FileException;
import com.epam.util.PropertyReader;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;

public final class ConnectionPool {
    private Queue<Connection> availableConnectionList = new LinkedList<>();
    private List<Connection> notAvailableConnectionList = new ArrayList<>();
    private final Properties properties = PropertyReader.getProperties();
    private static ConnectionPool instance = null;
    private final String URL = properties.getProperty("db.url");
    private final String PASSWORD = properties.getProperty("db.password");
    private final String USER = properties.getProperty("db.user");
    private final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(ConnectionPool.class);

    private ConnectionPool() throws DaoException, FileException {
        init();
    }

    private void init() throws DaoException{
        try {
            for (int i = 0; i < Integer.valueOf(properties.getProperty("db.initpoolsize")); ++i) {
                Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                availableConnectionList.add(connection);
            }
            logger.info("Connection pool successfully inited!");
        }catch(SQLException e){
            logger.fatal(e);
            throw new DaoException(e);
        }
    }

    public static ConnectionPool getInstance() throws DaoException, FileException {
        if(instance == null){
            instance = new ConnectionPool();
        }
        return instance;
    }

    private void addConnection() throws DaoException {
        if(availableConnectionList.size()+notAvailableConnectionList.size()<Integer.valueOf(properties.getProperty("db.maxpoolsize"))) {
            try {
                Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                availableConnectionList.add(connection);
            } catch (SQLException e) {
                throw new DaoException(e);
            }
        }
    }

    public Optional<Connection> getConnection() throws DaoException {
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

    }

    public void closeAllConnections(){
        while(notAvailableConnectionList.size()>0){
            Connection connection = notAvailableConnectionList.remove(notAvailableConnectionList.size()-1);
            availableConnectionList.add(connection);
            try {
                connection.close();
            }catch (SQLException e){
                logger.error(e.getMessage());
            }
        }
    }

}
