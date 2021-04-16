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

    @Override
    public List<User> findAllEntities() throws SQLException {
        List<User> list = new ArrayList<>();
        Connection connection = ConnectionPool.getInstance().getConnection();
        try {
            ResultSet resultSet = connection.createStatement().executeQuery(SQL_SELECT_ALL);
            while (resultSet.next()){
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String login = resultSet.getString("login");
                String password = resultSet.getString("password");
                int id = resultSet.getInt("id");
                UserStatus userStatus = null;
                if(UserStatus.extractUserStatusById(resultSet.getInt("status_fk")).isPresent()){
                    userStatus = UserStatus.extractUserStatusById(resultSet.getInt("status_fk")).get();
                };
                UserRole userRole = null;
                if(UserRole.extractUserRolebyId(resultSet.getInt("role_fk")).isPresent()){
                    userRole = UserRole.extractUserRolebyId(resultSet.getInt("role_fk")).get();
                };
                if(userRole != null && userStatus != null) {
                    User user = new User(login, password, email, name, id, userStatus, userRole);
                    list.add(user);
                }
            }
        }catch(DaoException e){
            System.out.println(e.getMessage());
        }
        connection.close();
        return list;
    }

    @Override
    public Optional<User> findEntityById(Long id) throws DaoException {
        return Optional.empty();
    }

    @Override
    public boolean create(Long id) throws DaoException {
        return false;
    }

    @Override
    public boolean delete(Long id) throws DaoException {
        return false;
    }

    @Override
    public boolean delete(User user) throws DaoException {
        return false;
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
