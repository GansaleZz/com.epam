package com.epam.db.dao.impl;

import com.epam.criteria.PaymentCriteria;
import com.epam.db.ConnectionPool;
import com.epam.db.dao.PaymentDao;
import com.epam.entity.Payment;
import com.epam.entity.PaymentStatus;
import com.epam.entity.Request;
import com.epam.entity.RequestStatus;
import com.epam.exceptions.DaoException;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PaymentDaoImpl implements PaymentDao {
    private final String SQL_SELECT_ALL = "SELECT * FROM Payment";
    private final String SQL_SELECT_BY_CRITERIA = "SELECT * FROM Payment WHERE ";
    private final String SQL_GET_COUNT = "SELECT COUNT(*) FROM Payment";
    private final String SQL_INSERT = "INSERT INTO Request (number_of_seats,start_date,end_date,user_id,request_status,room) VALUES(";
    private final String SQL_DELETE = "DELETE FROM Payment WHERE id = ";
    private final String SQL_UPDATE = "UPDATE Payment SET ";

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
            throw new DaoException(e);
        }
        return payment;
    }

    @Override
    public List<Payment> findAllEntities() throws DaoException {
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
            } catch (SQLException e) {
                throw new DaoException(e);
            } finally {
                ConnectionPool connectionPool = ConnectionPool.getInstance();
                connectionPool.close(connection.get());
            }
        }
        return list;
    }

    @Override
    public Optional<Payment> findEntityById(Integer id) throws DaoException {
        return Optional.empty();
    }

    @Override
    public boolean create(Payment payment) throws DaoException {
        return false;
    }

    @Override
    public boolean delete(Integer id) throws DaoException {
        return false;
    }

    @Override
    public Optional<Payment> update(Payment payment) throws DaoException {
        return Optional.empty();
    }

    @Override
    public Optional<Payment> findPaymentByCriteria(PaymentCriteria paymentCriteria) throws DaoException {
        return Optional.empty();
    }

    @Override
    public List<Payment> findAllPaymentByCriteria(PaymentCriteria paymentCriteria) throws DaoException {
        return null;
    }
}
