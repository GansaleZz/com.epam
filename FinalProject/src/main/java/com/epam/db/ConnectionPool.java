package com.epam.db;

import com.epam.entity.Property;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Realization of working with connections
 */
public final class ConnectionPool {
    private final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(ConnectionPool.class);
    private Queue<Connection> availableConnectionList = new ConcurrentLinkedQueue<>();
    private List<Connection> engagedConnectionList = new CopyOnWriteArrayList<>();
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
                availableConnectionList.add(connection);
            } catch (SQLException e) {
                logger.error(e.getMessage());
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

    /**
     @param connection - connection, which will be removed from the list of engaged connections and added to queue
     */
    public void close(Connection connection){
        logger.info("Connection closed!");
        engagedConnectionList.remove(connection);
        availableConnectionList.add(connection);
    }

    /**
     * All connections will be removed from the list of engaged connections and added to queue
     */
    public void closeAllConnections(){
        logger.info("All connections closed!");
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
            logger.info("Connection pool successfully inited!");
        }catch(SQLException e){
            logger.error(e.getMessage());
        }
    }

}
