package com.epam.db;

import com.epam.entity.Property;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Realization of working with connections
 */
public final class ConnectionPool {
    private final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(ConnectionPool.class);
    private BlockingDeque<Connection> availableConnectionList = new LinkedBlockingDeque<>();
    private List<Connection> engagedConnectionList = new LinkedList<>();
    /**
     * @see Property
     */
    private Property property = Property.getInstance();
    private static ConnectionPool instance = null;

    public static ConnectionPool getInstance(){
        if(instance == null){
            instance = new ConnectionPool();
        }
        return instance;
    }

    /**
     * Adding available connection on queue
     */
    private void addConnection(){
        if(availableConnectionList.size()+ engagedConnectionList.size()<property.getMAXPOOLSIZE()) {
            try {
                Connection connection = DriverManager.getConnection(property.getURL()+property.getSCHEME(), property.getUSER(), property.getPASSWORD());
                availableConnectionList.put(connection);
            } catch (SQLException | InterruptedException e) {
                LOGGER.error(e.getMessage());
            }
        }
    }


    /**
     * @return connection from queue and adding it to list of engaged connections
     */
    public Connection getConnection() {
        Connection pool;
        if(availableConnectionList.size() <= 0){
            try {
                /**
                 * While queue of available connections is empty and maximum pool size has achieved
                 * we are waiting when one of engaged connections will be available
                 */
                if (engagedConnectionList.size() == Property.getInstance().getMAXPOOLSIZE()) {
                    while (engagedConnectionList.size() == Property.getInstance().getMAXPOOLSIZE()) {
                        Thread.sleep(500);
                    }
                }else{
                    LOGGER.info("Connection received!");
                    addConnection();
                }
            } catch (InterruptedException e) {
                LOGGER.error(e.getMessage());
            }
        }
        pool = availableConnectionList.poll();
        engagedConnectionList.add(pool);
        return pool;
    }

    /**
     @param connection - connection, which will be removed from the list of engaged connections and added to queue
     */
    public void close(Connection connection){
        engagedConnectionList.remove(connection);
        availableConnectionList.add(connection);
    }

    /**
     * All connections will be removed from the list of engaged connections and added to queue
     */
    public void closeAllConnections(){
        LOGGER.info("All connections closed!");
        engagedConnectionList.stream()
                .forEach(i -> {
                    availableConnectionList.add(i);
                    engagedConnectionList.remove(i);
                });
    }

    private ConnectionPool(){
        init();
    }

    /**
     * Initialisation of queue of available connections
     */
    private void init(){
        try {
            for (int i = 0; i < property.getINITPOOLSIZE(); ++i) {
                Connection connection = new ConnectionProxy(DriverManager.getConnection(property.getURL()+property.getSCHEME(), property.getUSER(), property.getPASSWORD()));
                availableConnectionList.add(connection);
            }
            LOGGER.info("Connection pool successfully inited!");
        }catch(SQLException e){
            LOGGER.error(e.getMessage());
        }
    }

}
