package com.epam.db.dao.impl;

import com.epam.criteria.impl.PaymentCriteria;
import com.epam.db.ConnectionPool;
import com.epam.db.dao.PaymentDao;
import com.epam.entity.Payment;
import com.epam.entity.PaymentStatus;
import com.epam.exceptions.DaoException;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * Realisation of dao pattern for {@link Payment}
 *
 * @author Andrey Rubin
 */
public class PaymentDaoImpl implements PaymentDao {
    private final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(PaymentDaoImpl.class);
    /**
     * Set of SQL queries
     */
    private final String SQL_SELECT_ALL = "SELECT * FROM Payment";
    private final String SQL_SELECT_BY_CRITERIA = "SELECT * FROM Payment WHERE ";
    private final String SQL_INSERT = "INSERT INTO Payment (id,amount,date,payment_status) VALUES(?,?,?,?)";
    private final String SQL_DELETE = "DELETE FROM Payment WHERE id = ";
    private final String SQL_UPDATE = "UPDATE Payment SET amount = ?, date = ?, payment_status = ? WHERE id = ?";

    /**
     * Method that find all payments on db
     * @return list of payments from db
     */
    @Override
    public List<Payment> findAllEntities() throws DaoException{
        List<Payment> list = new ArrayList<>();
        Connection connection = ConnectionPool.getInstance().getConnection();
        try {
            ResultSet resultSet = connection.createStatement().executeQuery(SQL_SELECT_ALL);
            while (resultSet.next()) {
                if (getPayment(resultSet).isPresent()) {
                    list.add(getPayment(resultSet).get());
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
     * Method, which use to find payment by id on db
     * @param id unique parameter, need to find payment on db
     * @return payment from db, if its exists
     */
    @Override
    public Optional<Payment> findEntityById(Integer id) throws DaoException{
        Connection connection = ConnectionPool.getInstance().getConnection();
        Optional<Payment> payment = Optional.empty();
        try {
            ResultSet resultSet = connection.createStatement().executeQuery(SQL_SELECT_BY_CRITERIA + "id = " + id);
            if (resultSet.next()) {
                payment = getPayment(resultSet);
            }
            resultSet.close();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            connectionPool.close(connection);
        }
        return payment;
    }

    /**
     * Method that use to create payment on db
     * @param payment - it is argument, which information will be taken
     * for creating
     * @return boolean, that indicates success of creating of payment
     */
    @Override
    public boolean create(Payment payment) throws DaoException{
        boolean result = false;
        if(payment != null) {
            Connection connection = ConnectionPool.getInstance().getConnection();
            try{
                PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT);
                preparedStatement.setInt(1,payment.getId());
                preparedStatement.setInt(2,payment.getAmount());
                preparedStatement.setDate(3,new Date(payment.getDate().getTime()));
                preparedStatement.setInt(4,PaymentStatus.getIdByPaymentStatus(payment.getStatus()));
                preparedStatement.execute();
                preparedStatement.close();
                result = true;
                LOGGER.info(payment + " successfully created!");
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
     * Method, which use to deleting payment from db by id
     * @param id - parameter that need to search payment on db
     * @return boolean, that indicates success of deleting of payment (true - if payment
     * with id exists, not - false)
     */
    @Override
    public boolean delete(Integer id) throws DaoException{
        Connection connection = ConnectionPool.getInstance().getConnection();
        boolean result = false;
        try {
            if (findEntityById(id).isPresent()) {
                LOGGER.info(findEntityById(id).get() + " successfully deleted!");
                connection.createStatement().executeUpdate(SQL_DELETE + id);
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
     * Method, which use to update information of payment on db
     * @param payment its argument with new information
     * @return updated payment
     */
    @Override
    public Optional<Payment> update(Payment payment) throws DaoException{
        Optional<Payment> paymentOptional = Optional.empty();
        if(payment != null) {
            Connection connection = ConnectionPool.getInstance().getConnection();
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE);
                preparedStatement.setInt(1,payment.getAmount());
                preparedStatement.setDate(2,new Date(payment.getDate().getTime()));
                preparedStatement.setInt(3,PaymentStatus.getIdByPaymentStatus(payment.getStatus()));
                preparedStatement.setInt(4,payment.getId());
                preparedStatement.execute();
                preparedStatement.close();
                paymentOptional = findEntityById(payment.getId());
                LOGGER.info(payment + " successfully updated!");
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
            } finally {
                ConnectionPool connectionPool = ConnectionPool.getInstance();
                connectionPool.close(connection);
            }
        }
        return paymentOptional;
    }

    /**
     * Method that find all payments on db by special criteria
     * @param paymentCriteria - parameter by which payments are searching on db
     * @return list of payments with a specific criteria
     */
    @Override
    public List<Payment> findAllPaymentByCriteria(PaymentCriteria paymentCriteria) throws DaoException{
        List<Payment> list = new ArrayList<>();
        if(paymentCriteria.getPaymentStatus() != null) {
            list = chooseAllByPredicate(i -> ((Payment)i).getStatus() == paymentCriteria.getPaymentStatus());
        }else{
            if(paymentCriteria.getAmount()!=0) {
                list = chooseAllByPredicate(i -> ((Payment)i).getAmount() == paymentCriteria.getAmount());
            }
        }
        return list;
    }


    /**
     * Method that saving code from duplicates on method findAllPaymentByCriteria,
     * using for find payments on db by criteria
     * @param predicate - it's criteria by which we are searching payments on db
     * @return list of payments with a specific criteria
     */
    private List<Payment> chooseAllByPredicate(Predicate predicate) throws DaoException {
        List<Payment> list = new ArrayList<>();
        findAllEntities()
                .stream()
                .filter(predicate)
                .forEach(i -> list.add((Payment) i));
        return list;
    }


    /**
     * Method which saving code from duplicates
     * @param resultSet - argument, which keeps result of executing of SQL query
     * @return inited payment by information from db
     */
    private Optional<Payment> getPayment(ResultSet resultSet){
        Optional<Payment> payment = Optional.empty();
        try {
            int id = resultSet.getInt(1);
            int amount = resultSet.getInt(2);
            Date date = resultSet.getDate(3);
            PaymentStatus paymentStatus = null;
            if(PaymentStatus.extractPaymentStatusById(resultSet.getInt(4)).isPresent()){
                paymentStatus = PaymentStatus.extractPaymentStatusById(resultSet.getInt(4)).get();
            }
            if(date != null) {
                payment = Optional.of(new PaymentCriteria.Builder()
                .newBuilder()
                .withId(id)
                .withAmount(amount)
                .withDate(date)
                .withPaymentStatus(paymentStatus)
                .build());
            }else{
                payment = Optional.of(new PaymentCriteria.Builder()
                .newBuilder()
                .withId(id)
                .withAmount(amount)
                .withPaymentStatus(paymentStatus)
                .build());
            }
        }catch(SQLException e){
            LOGGER.error(e.getMessage());
        }
        return payment;
    }
}
