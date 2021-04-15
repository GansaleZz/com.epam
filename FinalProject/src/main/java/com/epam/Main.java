package com.epam;

import com.epam.db.ConnectionPool;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        final ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        ResultSet resultSet =connection.createStatement().executeQuery("SELECT * FROM payment_status");
        resultSet.next();
        System.out.println(resultSet.getString(1));
        connectionPool.close(connection);
    }
}
