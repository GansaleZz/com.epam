package com.epam.db;

import com.epam.entity.Property;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;


public final class ConnectionPool {
    private Queue<Connection> availableConnectionList = new ConcurrentLinkedQueue<>();
    private List<Connection> engagedConnectionList = new CopyOnWriteArrayList<>();
    private static ConnectionPool instance = null;
    private final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(ConnectionPool.class);
    private Property property = Property.getInstance();
    private ConnectionPool(){
        init();
    }

    private void init(){
        try {
            for (int i = 0; i < property.getINITPOOLSIZE(); ++i) {
                Connection connection = new ConnectionProxy(DriverManager.getConnection(property.getURL()+property.getSCHEME(), property.getUSER(), property.getPASSWORD()));
                availableConnectionList.add(connection);
            }
            logger.info("Connection pool successfully inited!");
        }catch(SQLException e){
            logger.error(e.getMessage());
        }
    }

    public static ConnectionPool getInstance(){
        if(instance == null){
            instance = new ConnectionPool();
        }
        return instance;
    }

    private void addConnection(){
        if(availableConnectionList.size()+ engagedConnectionList.size()<property.getMAXPOOLSIZE()) {
            try {
                Connection connection = DriverManager.getConnection(property.getURL()+property.getSCHEME(), property.getUSER(), property.getPASSWORD());
                availableConnectionList.add(connection);
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
        }
    }

    public Connection getConnection() {
        Connection pool;
        if(availableConnectionList.size() <= 0){
            try {
                if (engagedConnectionList.size() == Property.getInstance().getMAXPOOLSIZE()) {
                    while (engagedConnectionList.size() == Property.getInstance().getMAXPOOLSIZE()) {
                        Thread.sleep(500);
                    }
                }else{
                    logger.info("Connection received!");
                    addConnection();
                }
            } catch (InterruptedException e) {
                logger.error(e.getMessage());
            }
        }
        pool = availableConnectionList.poll();
        engagedConnectionList.add(pool);
        return pool;
    }

    public void close(Connection connection){
        logger.info("Connection closed!");
        engagedConnectionList.remove(connection);
        availableConnectionList.add(connection);
    }

    public void closeAllConnections(){
        logger.info("All connections closed!");
        engagedConnectionList.stream()
                .forEach(i -> {
                    availableConnectionList.add(i);
                    engagedConnectionList.remove(i);
                });
    }

}
