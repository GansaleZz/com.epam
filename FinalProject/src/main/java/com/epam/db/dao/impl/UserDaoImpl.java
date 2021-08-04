package com.epam.db.dao.impl;

import com.epam.criteria.impl.UserCriteria;
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

/**
 * Realisation of dao pattern for {@link User}
 *
 * @author Andrey Rubin
 */
public class UserDaoImpl implements UserDao {
    private final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(UserDaoImpl.class);
    /**
     * Set of SQL queries
     */
    private final String SQL_SELECT_ALL = "SELECT * FROM USER";
    private final String SQL_SELECT_BY_CRITERIA = "SELECT * FROM USER WHERE ";
    private final String SQL_SELECT_BALANCE = "SELECT * FROM user_balance WHERE id = ";
    private final String SQL_INSERT_BALANCE = "INSERT INTO user_balance (id,balance) VALUES (?,?)";
    private final String SQL_UPDATE_BALANCE = "UPDATE user_balance SET balance = ? WHERE id = ?";
    private final String SQL_INSERT = "INSERT INTO User (login,password, name, email, role_fk, status_fk) VALUES (?,?,?,?,?,?)";
    private final String SQL_DELETE = "DELETE FROM User WHERE id = ";
    private final String SQL_UPDATE = "UPDATE User SET name = ?, email = ?, password = ?, status_fk = ?, role_fk = ? WHERE id = ? AND login = ?";
    private final String SQL_UPDATE_USERS_BALANCE = "UPDATE User SET balance_fk = ? WHERE id = ?";
    private final String SQL_DELETE_BALANCE = "DELETE FROM user_balance WHERE id = ";

    /**
     * Method, which finds all users on db
     * @return list of users from db
     */
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
            LOGGER.error(e.getMessage());
        } finally {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            connectionPool.close(connection);
        }
        return list;
    }

    /**
     * Method, which use to find user on db by id, if its exists
     * @param id unique parameter, need to find user on db
     * @return user from db, if its exists
     */
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
            LOGGER.error(e.getMessage());
        } finally {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            connectionPool.close(connection);
        }
        return user;
    }

    /**
     * Method which creates user on db
     * @param user - it is argument, which information will be taken
     * for creating
     * @return boolean, that indicates success of creating of user
     */
    @Override
    public boolean create(User user) throws DaoException{
        boolean result = false;
        if(user!=null) {
            Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement preparedStatement ;
            try {
                if (!connection.createStatement().executeQuery(SQL_SELECT_BY_CRITERIA + "login = '" + user.getLogin() + "'").next() &&
                    !connection.createStatement().executeQuery(SQL_SELECT_BY_CRITERIA + "email = '" + user.getEmail() + "'").next()) {
                    preparedStatement = connection.prepareStatement(SQL_INSERT);
                    preparedStatement.setString(1,user.getLogin());
                    preparedStatement.setString(2,user.getPassword());
                    preparedStatement.setString(3,user.getName());
                    preparedStatement.setString(4,user.getEmail());
                    preparedStatement.setInt(5,UserRole.getIdByUserRole(UserRole.CLIENT));
                    preparedStatement.setInt(6,UserStatus.getIdByUserStatus(UserStatus.AVAILABLE));
                    preparedStatement.execute();
                    preparedStatement.close();
                    UserCriteria userCriteria = new UserCriteria();
                    userCriteria.setLogin(user.getLogin());
                    User userTemp = findUserByCriteria(userCriteria).get();
                    preparedStatement = connection.prepareStatement(SQL_INSERT_BALANCE);
                    preparedStatement.setInt(1, userTemp.getId());
                    preparedStatement.setDouble(2, user.getBalance());
                    preparedStatement.execute();
                    preparedStatement.close();
                    preparedStatement = connection.prepareStatement(SQL_UPDATE_USERS_BALANCE);
                    preparedStatement.setInt(1,userTemp.getId());
                    preparedStatement.setInt(2,userTemp.getId());
                    preparedStatement.execute();
                    preparedStatement.close();
                    result = true;
                    LOGGER.info(user+" successfully created!");
                }
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
            } finally {
                ConnectionPool connectionPool = ConnectionPool.getInstance();
                connectionPool.close(connection);
            }
        }
        return result;
    }

    /**
     * Method, which use to deleting user from db by id
     * @param id - parameter that need to search user on db
     * @return boolean, that indicates success of deleting  (true - if user
     * with id exists, not - false)
     */
    @Override
    public boolean delete(Integer id) throws DaoException{
        Connection connection = ConnectionPool.getInstance().getConnection();
        boolean result = false;
        try {
            if (findEntityById(id).isPresent()) {
                LOGGER.info(findEntityById(id).get()+" successfully deleted!");
                connection.createStatement().executeUpdate(SQL_DELETE + id);
                connection.createStatement().executeUpdate(SQL_DELETE_BALANCE+id);
                result = true;
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            connectionPool.close(connection);
        }
        return result;
    }

    /**
     * Method, which use to update information of user on db
     * @param user its argument with new information
     * @return updated user
     */
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
                if(user.getUserRole() != UserRole.CLIENT){
                    connection.createStatement().executeUpdate(SQL_DELETE_BALANCE+user.getId());
                }else{
                    preparedStatement = connection.prepareStatement(SQL_UPDATE_BALANCE);
                    preparedStatement.setDouble(1,user.getBalance());
                    preparedStatement.setInt(2,user.getId());
                    preparedStatement.execute();
                    preparedStatement.close();
                }
                userOptional = findEntityById(user.getId());
                LOGGER.info(user + " successfully updated!");
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
            } finally {
                ConnectionPool connectionPool = ConnectionPool.getInstance();
                connectionPool.close(connection);
            }
        }
        return userOptional;
    }

    /**
     * Method that use to find all users on db by special criteria
     * @param userCriteria - parameter by which users are searching on db
     * @return list of users with a specific criteria
     */
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

    /**
     * Method that use to find user on db by special criteria
     * @param userCriteria - parameter by which user is searching on db
     * @return user with a specific criteria
     */
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

    /**
     * Method, which use to find value of user's balance on db by id
     * @param id unique parameter, need to find balance on db
     * @return value of user's balance
     */
    @Override
    public Double findBalanceById(Integer id) throws DaoException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        double balance = 0;
        try {
            ResultSet resultSet = connection.createStatement().executeQuery(SQL_SELECT_BALANCE + id);
            if (resultSet.next()) {
                balance = resultSet.getDouble("balance");
            }
            resultSet.close();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            connectionPool.close(connection);
        }
        return balance;
    }

    /**
     * Method that saving code from duplicates on method findUserByCriteria,
     * using for find user on db by criteria
     * @param predicate - it's criteria by which we are searching user on db
     * @return user with a specific criteria
     */
    private Optional<User> chooseByPredicate(Predicate predicate) throws DaoException {
        Optional<User> user = findAllEntities()
                .stream()
                .filter(predicate)
                .findAny();
        return user;
    }

    /**
     * Method that saving code from duplicates on method findAllUsersByCriteria,
     * using for find users on db by criteria
     * @param predicate - it's criteria by which we are searching users on db
     * @return list of users with a specific criteria
     */
    private List<User> chooseAllByPredicate(Predicate predicate) throws DaoException {
        List<User> list = new ArrayList<>();
        findAllEntities()
                .stream()
                .filter(predicate)
                .forEach(i -> list.add((User)i));
        return list;
    }

    /**
     * Method which saving code from duplicates
     * @param resultSet - argument, which keeps result of executing of SQL query
     * @return inited user by information from db
     */
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
                if(userRole == UserRole.CLIENT){
                    user.get().setBalance(findBalanceById(user.get().getId()));
                }
            }
        }catch(SQLException e){
            LOGGER.error(e.getMessage());
        }
        return user;
    }


}
