package com.epam.db.dao.impl;

import com.epam.criteria.UserCriteria;
import com.epam.db.ConnectionPool;
import com.epam.db.dao.UserDao;
import com.epam.entity.User;
import com.epam.entity.UserRole;
import com.epam.entity.UserStatus;
import com.epam.exceptions.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class UserDaoImpl implements UserDao {
    private final String SQL_SELECT_ALL = "SELECT * FROM USER";
    private final String SQL_SELECT_BY_CRITERIA = "SELECT * FROM USER WHERE ";
    private final String SQL_INSERT = "INSERT INTO User (login,password, name, email, role_fk, status_fk) VALUES (?,?,?,?,?,?)";
    private final String SQL_DELETE = "DELETE FROM User WHERE id = ";
    private final String SQL_UPDATE = "UPDATE User SET name = ?, email = ?, password = ?, status_fk = ?, role_fk = ? WHERE id = ? AND login = ?";
    private final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(UserDaoImpl.class);


    @Override
    public List<User> findAllEntities() throws DaoException{
        List<User> list = new ArrayList<>();
        Connection connection = ConnectionPool.getInstance().getConnection();
        try {
            ResultSet resultSet = connection.createStatement().executeQuery(SQL_SELECT_ALL);
            while (resultSet.next()) {
                if (getUser(resultSet).isPresent()) {
                    list.add(getUser(resultSet).get());
                }
            }
            resultSet.close();
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new DaoException(e);
        } finally {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            connectionPool.close(connection);
        }
        return list;
    }

    @Override
    public Optional<User> findEntityById(Integer id) throws DaoException{
        Connection connection = ConnectionPool.getInstance().getConnection();
        Optional<User> user = Optional.empty();
        try {
            ResultSet resultSet = connection.createStatement().executeQuery(SQL_SELECT_BY_CRITERIA + "id = " + id);
            if (resultSet.next()) {
                user = getUser(resultSet);
            }
            resultSet.close();
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new DaoException(e);
        } finally {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            connectionPool.close(connection);
        }
        return user;
    }

    @Override
    public boolean create(User user) throws DaoException{
        boolean result = false;
        if(user!=null) {
            Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement preparedStatement = null;
            try {
                if (!connection.createStatement().executeQuery(SQL_SELECT_BY_CRITERIA + "login = '" + user.getLogin() + "'").next()) {
                    preparedStatement = connection.prepareStatement(SQL_INSERT);
                    preparedStatement.setString(1,user.getLogin());
                    preparedStatement.setString(2,user.getPassword());
                    preparedStatement.setString(3,user.getName());
                    preparedStatement.setString(4,user.getEmail());
                    preparedStatement.setInt(5,UserRole.getIdByUserRole(UserRole.CLIENT));
                    preparedStatement.setInt(6,UserStatus.getIdByUserStatus(UserStatus.AVAILABLE));
                    preparedStatement.execute();
                    preparedStatement.close();
                    result = true;
                    logger.info(user+" successfully created!");
                }
            } catch (SQLException e) {
                logger.error(e.getMessage());
                throw new DaoException(e);
            } finally {
                ConnectionPool connectionPool = ConnectionPool.getInstance();
                connectionPool.close(connection);
            }
        }
        return result;
    }

    @Override
    public boolean delete(Integer id) throws DaoException{
        Connection connection = ConnectionPool.getInstance().getConnection();
        boolean result = false;
        try {
            if (findEntityById(id).isPresent()) {
                logger.info(findEntityById(id).get()+" successfully deleted!");
                connection.createStatement().executeUpdate(SQL_DELETE + id);
                result = true;
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new DaoException(e);
        } finally {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            connectionPool.close(connection);
        }
        return result;
    }

    @Override
    public Optional<User> update(User user) throws DaoException {
        Optional<User> userOptional = Optional.empty();
        Connection connection = ConnectionPool.getInstance().getConnection();
        if(user != null) {
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE);
                preparedStatement.setString(1,user.getName());
                preparedStatement.setString(2,user.getEmail());
                preparedStatement.setString(3,user.getPassword());
                preparedStatement.setInt(4,UserStatus.getIdByUserStatus(user.getStatus()));
                preparedStatement.setInt(5,UserRole.getIdByUserRole(user.getUserRole()));
                preparedStatement.setInt(6,user.getId());
                preparedStatement.setString(7,user.getLogin());
                preparedStatement.execute();
                preparedStatement.close();
                userOptional = findEntityById(user.getId());
                logger.info(user + " successfully updated!");
            } catch (SQLException e) {
                logger.error(e.getMessage());
                throw new DaoException(e);
            } finally {
                ConnectionPool connectionPool = ConnectionPool.getInstance();
                connectionPool.close(connection);
            }
        }
        return userOptional;
    }

    @Override
    public List<User> findAllUsersByCriteria(UserCriteria userCriteria) throws DaoException{
        List<User> list = new ArrayList<>();
            if(userCriteria.getName() != null) {
                list = chooseAllByPredicate(i -> ((User)i).getEmail().equals(userCriteria.getName()));
            }else{
                if(userCriteria.getStatus()!=null){
                    list = chooseAllByPredicate(i -> ((User)i).getStatus() == userCriteria.getStatus());
                }else{
                    if(userCriteria.getRole()!=null){
                        list = chooseAllByPredicate(i -> ((User)i).getUserRole() == userCriteria.getRole());
                    }
                }
            }
        return list;
    }

    @Override
    public Optional<User> findUserByCriteria(UserCriteria userCriteria) throws DaoException{
        Optional<User> user = Optional.empty();
        if(userCriteria.getEmail() != null){
            user = chooseByPredicate(i -> ((User)i).getEmail().equals(userCriteria.getEmail()));
        }else{
            if(userCriteria.getLogin() != null){
                user = chooseByPredicate(i -> ((User)i).getLogin().equals(userCriteria.getLogin()));
            }else{
                if(userCriteria.getPassword() != null){
                    user = chooseByPredicate(i -> ((User)i).getPassword().equals(userCriteria.getPassword()));
                }
            }
        }
        return user;
    }

    private Optional<User> chooseByPredicate(Predicate predicate) throws DaoException {
        Optional<User> user = findAllEntities()
                .stream()
                .filter(predicate)
                .findAny();
        return user;
    }

    private List<User> chooseAllByPredicate(Predicate predicate) throws DaoException {
        List<User> list = new ArrayList<>();
        findAllEntities()
                .stream()
                .filter(predicate)
                .forEach(i -> list.add((User)i));
        return list;
    }

    private Optional<User> getUser(ResultSet resultSet) throws DaoException {
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
                user = Optional.of(new UserCriteria.Builder()
                        .newBuilder()
                        .withLogin(login)
                        .withPassword(password)
                        .withEmail(email)
                        .withName(name)
                        .withId(id)
                        .withUserRole(userRole)
                        .withUserStatus(userStatus)
                        .build());
            }
        }catch(SQLException e){
            logger.error(e.getMessage());
            throw new DaoException(e);
        }
        return user;
    }
}
