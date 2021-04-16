package com.epam.db;

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
    private String url = properties.getProperty("db.url");
    private String password = properties.getProperty("db.password");
    private String user = properties.getProperty("db.user");

    private ConnectionPool(){
        init();
    }

    private void init(){
        try {
            for (int i = 0; i < Integer.valueOf(properties.getProperty("db.initpoolsize")); ++i) {
                Connection connection = DriverManager.getConnection(url, user, password);
                availableConnectionList.add(connection);
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public static ConnectionPool getInstance(){
        if(instance == null){
            instance = new ConnectionPool();
        }
        return instance;
    }

    private void addConnection(){
        if(availableConnectionList.size()+notAvailableConnectionList.size()<Integer.valueOf(properties.getProperty("db.maxpoolsize"))) {
            try {
                Connection connection = DriverManager.getConnection(url, user, password);
                availableConnectionList.add(connection);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public Connection getConnection(){
        if(availableConnectionList.size()<=0){
            addConnection();
        }
        final Connection pool = availableConnectionList.poll();
        notAvailableConnectionList.add(pool);
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
