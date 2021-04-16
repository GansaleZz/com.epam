package com.epam.db.dao.impl;

import com.epam.criteria.UserCriteria;
import com.epam.db.ConnectionPool;
import com.epam.db.dao.UserDao;
import com.epam.entity.User;
import com.epam.entity.UserRole;
import com.epam.entity.UserStatus;
import com.epam.exceptions.DaoException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao {
    private final String SQL_SELECT_ALL = "SELECT * FROM USER";
    private final String SQL_SELECT_BY_ID = "SELECT * FROM USER WHERE id = ";
    private final String SQL_SELECT_BY_CRITERIA = "SELECT * FROM USER WHERE ";
    private final String SQL_GET_COUNT = "SELECT COUNT(*) FROM USER";
    private final String SQL_INSERT = "INSERT INTO User (login,password,name,email,role_fk,status_fk) VALUES(";
    private final String SQL_DELETE = "DELETE FROM User WHERE id = ";

    private Optional<User> getUser(ResultSet resultSet) throws SQLException {
        Optional<User> user = Optional.empty();
        try {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String email = resultSet.getString("email");
            String login = resultSet.getString("login");
            String password = resultSet.getString("password");
            UserStatus userStatus = null;
            if (UserStatus.extractUserStatusById(resultSet.getInt("status_fk")).isPresent()) {
                userStatus = UserStatus.extractUserStatusById(resultSet.getInt("status_fk")).get();
            }
            UserRole userRole = null;
            if (UserRole.extractUserRolebyId(resultSet.getInt("role_fk")).isPresent()) {
                userRole = UserRole.extractUserRolebyId(resultSet.getInt("role_fk")).get();
            }
            if (userRole != null && userStatus != null) {
                user = Optional.of(new User(login, password, email, name, id, userStatus, userRole));
            }
        }catch(DaoException e){
            System.out.println(e.getMessage());
        }
        return user;
    }

    @Override
    public List<User> findAllEntities() throws SQLException {
        List<User> list = new ArrayList<>();
        Connection connection = ConnectionPool.getInstance().getConnection();
        try {
            ResultSet resultSet = connection.createStatement().executeQuery(SQL_SELECT_ALL);
            while (resultSet.next()){
                if(getUser(resultSet).isPresent()){
                    list.add(getUser(resultSet).get());
                }
            }
        }catch(DaoException e){
            System.out.println(e.getMessage());
        }
        finally {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            connectionPool.close(connection);
        }
        return list;
    }

    @Override
    public Optional<User> findEntityById(Integer id) throws SQLException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        Optional<User> user = Optional.empty();
        try {
            ResultSet tempSet = connection.createStatement().executeQuery(SQL_GET_COUNT);
            tempSet.next();
            if(tempSet.getInt(1)>=id) {
                ResultSet resultSet = connection.createStatement().executeQuery(SQL_SELECT_BY_ID+id);
                resultSet.next();
                if (getUser(resultSet).isPresent()) {
                    user = getUser(resultSet);
                }
            }
        }catch(DaoException e){
            System.out.println(e.getMessage());
        }
        finally {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            connectionPool.close(connection);
        }
        return user;
    }

    @Override
    public boolean create(User user) throws SQLException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        boolean result = false;
        try{
            if (user.getEmail() != null && user.getLogin() != null && user.getPassword() != null && user.getName() != null) {
                if(!connection.createStatement().executeQuery(SQL_SELECT_BY_CRITERIA+"login = '"+user.getLogin()+"'").next()
                && !connection.createStatement().executeQuery(SQL_SELECT_BY_CRITERIA+"email = '"+user.getEmail()+"'").next()){
                    connection.createStatement().executeUpdate(SQL_INSERT+"'" +user.getLogin()+"','"+user.getPassword()+"','"+user.getName()
                            +"','"+user.getEmail()+"',"+UserRole.getIdByUserRole(UserRole.CLIENT)+","+UserStatus.getIdByUserStatus(UserStatus.AVAILABLE)+")");
                    result = true;
                }
            }
        }catch(DaoException e){
            System.out.println(e.getMessage());
        }
        finally {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            connectionPool.close(connection);
        }
        return result;
    }

    @Override
    public boolean delete(Integer id) throws SQLException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        boolean result = false;
        try{
            if(findEntityById(id).isPresent()){
                connection.createStatement().executeUpdate(SQL_DELETE+id);
                result = true;
            }
        }catch(DaoException e){
            System.out.println(e.getMessage());
        }finally {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            connectionPool.close(connection);
        }
        return result;
    }

    @Override
    public Optional<User> update(User user) throws DaoException {
        return Optional.empty();
    }

    @Override
    public List<User> findAllUsersByCriteria(UserCriteria userCriteria) throws DaoException {
        return null;
    }

    @Override
    public Optional<User> findUserByCriteria(UserCriteria userCriteria) throws DaoException {
        return Optional.empty();
    }
}
