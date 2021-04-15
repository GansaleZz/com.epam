package com.epam.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public final class ConnectionPool {
    private Queue<Connection> availableConnectionList = new LinkedList<>();
    private List<Connection> notAvailableConnectionList = new ArrayList<>();
    private static ConnectionPool instance = null;
    private String url = "jdbc:mysql://localhost:3306/project";
    private String password = "AdminAdmin8!";
    private String user = "root";

    private ConnectionPool(){

    }

    public static ConnectionPool getInstance(){
        if(instance == null){
            instance = new ConnectionPool();
        }
        return instance;
    }

    private void addConnection(){
        try {
            Connection connection = DriverManager.getConnection(url,user,password);
            this.availableConnectionList.add(connection);
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public Connection getConnection(){
        if(availableConnectionList.size()<=0){
            addConnection();
        }
        final Connection poll = availableConnectionList.poll();
        notAvailableConnectionList.add(poll);
        return poll;
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
