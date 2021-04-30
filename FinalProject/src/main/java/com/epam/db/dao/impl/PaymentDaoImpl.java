package com.epam.db.dao.impl;

import com.epam.criteria.PaymentCriteria;
import com.epam.db.ConnectionPool;
import com.epam.db.dao.PaymentDao;
import com.epam.entity.Payment;
import com.epam.entity.PaymentStatus;
import com.epam.entity.Request;
import com.epam.entity.RequestStatus;
import com.epam.exceptions.DaoException;
import com.epam.exceptions.FileException;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class PaymentDaoImpl implements PaymentDao {
    private final String SQL_SELECT_ALL = "SELECT * FROM Payment";
    private final String SQL_SELECT_BY_CRITERIA = "SELECT * FROM Payment WHERE ";
    private final String SQL_INSERT = "INSERT INTO Payment (amount,date,payment_status) VALUES(";
    private final String SQL_DELETE = "DELETE FROM Payment WHERE id = ";
    private final String SQL_UPDATE = "UPDATE Payment SET ";
    private final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(PaymentDaoImpl.class);

    @Override
    public List<Payment> findAllEntities() throws DaoException{
        List<Payment> list = new ArrayList<>();
        Optional<Connection> connection = ConnectionPool.getInstance().getConnection();
        if(connection.isPresent()) {
            try {
                ResultSet resultSet = connection.get().createStatement().executeQuery(SQL_SELECT_ALL);
                while (resultSet.next()) {
                    if (getPayment(resultSet).isPresent()) {
                        list.add(getPayment(resultSet).get());
                    }
                }
                resultSet.close();
            } catch (SQLException e) {
                logger.error(e.getMessage());
                throw new DaoException(e);
            } finally {
                ConnectionPool connectionPool = ConnectionPool.getInstance();
                connectionPool.close(connection.get());
                try {
                    connection.get().close();
                }catch(SQLException e){
                    throw new DaoException(e);
                }
            }
        }
        return list;
    }

    @Override
    public Optional<Payment> findEntityById(Integer id) throws DaoException{
        Optional<Connection> connection = ConnectionPool.getInstance().getConnection();
        Optional<Payment> payment = Optional.empty();
        if(connection.isPresent()) {
            try {
                ResultSet resultSet = connection.get().createStatement().executeQuery(SQL_SELECT_BY_CRITERIA + "id = " + id);
                if (resultSet.next()) {
                    payment = getPayment(resultSet);
                }
                resultSet.close();
            } catch (SQLException e) {
                logger.error(e.getMessage());
                throw new DaoException(e);
            } finally {
                ConnectionPool connectionPool = ConnectionPool.getInstance();
                connectionPool.close(connection.get());
                try {
                    connection.get().close();
                }catch(SQLException e){
                    throw new DaoException(e);
                }
            }
        }
        return payment;
    }

    @Override
    public boolean create(Payment payment) throws DaoException{
        boolean result = false;
        if(payment != null) {
            Optional<Connection> connection = ConnectionPool.getInstance().getConnection();
            if(connection.isPresent()) {
                try {
                    logger.info(payment + "successfully created!");
                    connection.get().createStatement().executeUpdate(SQL_INSERT +payment.getAmount()+",'"+payment.getDate()+"',"+PaymentStatus.getIdByPaymentStatus(payment.getPaymentStatus())+")");
                    result = true;
                } catch (SQLException e) {
                    logger.error(e.getMessage());
                    throw new DaoException(e);
                } finally {
                    ConnectionPool connectionPool = ConnectionPool.getInstance();
                    connectionPool.close(connection.get());
                    try {
                        connection.get().close();
                    }catch(SQLException e){
                        throw new DaoException(e);
                    }
                }
            }
        }
        return result;
    }

    @Override
    public boolean delete(Integer id) throws DaoException{
        Optional<Connection> connection = ConnectionPool.getInstance().getConnection();
        boolean result = false;
        if(connection.isPresent()) {
            try {
                if (findEntityById(id).isPresent()) {
                    logger.info(findEntityById(id).get() + "successfully deleted!");
                    connection.get().createStatement().executeUpdate(SQL_DELETE + id);
                    result = true;
                }
            } catch (SQLException e) {
                logger.error(e.getMessage());
                throw new DaoException(e);
            } finally {
                ConnectionPool connectionPool = ConnectionPool.getInstance();
                connectionPool.close(connection.get());
                try {
                    connection.get().close();
                }catch(SQLException e){
                    throw new DaoException(e);
                }
            }
        }
        return result;
    }

    @Override
    public Optional<Payment> update(Payment payment) throws DaoException{
        Optional<Payment> paymentOptional = Optional.empty();
        if(payment != null) {
            Optional<Connection> connection = ConnectionPool.getInstance().getConnection();
            if(connection.isPresent()) {
                try {
                    connection.get().createStatement().executeUpdate(SQL_UPDATE + "amount = " + payment.getAmount()
                            + ", date = '" + payment.getDate() + "', payment_status = " + PaymentStatus.getIdByPaymentStatus(payment.getPaymentStatus()) + " WHERE id = " + payment.getId());
                    paymentOptional = findEntityById(payment.getId());
                    logger.info(payment + " successfully updated!");
                } catch (SQLException e) {
                    logger.error(e.getMessage());
                    throw new DaoException(e);
                } finally {
                    ConnectionPool connectionPool = ConnectionPool.getInstance();
                    connectionPool.close(connection.get());
                    try {
                        connection.get().close();
                    }catch(SQLException e){
                        throw new DaoException(e);
                    }
                }
            }
        }
        return paymentOptional;
    }


    @Override
    public List<Payment> findAllPaymentByCriteria(PaymentCriteria paymentCriteria) throws DaoException{
        List<Payment> list = new ArrayList<>();
        if(paymentCriteria.getPaymentStatus() != null) {
            list = chooseAllByPredicate(i -> ((Payment)i).getPaymentStatus() == paymentCriteria.getPaymentStatus());
        }else{
            if(paymentCriteria.getAmount()!=0) {
                list = chooseAllByPredicate(i -> ((Payment)i).getAmount() == paymentCriteria.getAmount());
            }
        }
        return list;
    }

    private List<Payment> chooseAllByPredicate(Predicate predicate) throws DaoException {
        List<Payment> list = new ArrayList<>();
        findAllEntities()
                .stream()
                .filter(predicate)
                .forEach(i -> list.add((Payment) i));
        return list;
    }

    private Optional<Payment> getPayment(ResultSet resultSet) throws DaoException {
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
                payment = Optional.of(new Payment(id,amount,date,paymentStatus));
            }else{
                payment = Optional.of(new Payment(id,amount,paymentStatus));
            }
        }catch(SQLException e){
            logger.error(e.getMessage());
            throw new DaoException(e);
        }
        return payment;
    }
}
