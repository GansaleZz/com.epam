package com.epam.db.dao.impl;

import com.epam.criteria.PaymentCriteria;
import com.epam.criteria.RequestCriteria;
import com.epam.db.ConnectionPool;
import com.epam.entity.*;
import com.epam.exceptions.DaoException;
import com.epam.exceptions.FileException;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class PaymentDaoImplTest {

    private final int id = 1;

    @Test
    void findAllEntities() throws FileException, DaoException, SQLException {
        PaymentDaoImpl paymentDao = new PaymentDaoImpl();
        List<Payment> list = paymentDao.findAllEntities();
        Connection connection = ConnectionPool.getInstance().getConnection().get();
        final String COUNT = "SELECT COUNT(*) FROM Payment";
        ResultSet resultSet = connection.createStatement().executeQuery(COUNT);
        resultSet.next();
        int count = resultSet.getInt(1);
        assertEquals(count,list.size());
    }

    @Test
    void findEntityById() throws FileException, DaoException {
        int amount = 123;
        PaymentDaoImpl paymentDao = new PaymentDaoImpl();
        assertEquals(amount,paymentDao.findEntityById(this.id).get().getAmount());
    }

    @Test
    void create() throws FileException, DaoException {
        PaymentDaoImpl paymentDao = new PaymentDaoImpl();
        Payment payment = paymentDao.findEntityById(this.id).get();
        payment.setAmount(190);
        assertEquals(true,paymentDao.create(payment));
    }

    @Test
    void delete() throws SQLException, FileException, DaoException {
        Connection connection = ConnectionPool.getInstance().getConnection().get();
        ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM Payment");
        int id = 0;
        while(resultSet.next()){
            id = resultSet.getInt("id");
        }
        PaymentDaoImpl paymentDao = new PaymentDaoImpl();
        if(id != 0 ) {
            assertEquals(true, paymentDao.delete(id));
            assertEquals(Optional.empty(), paymentDao.findEntityById(id));
        }else{
            assertEquals(false, paymentDao.delete(id));
        }
    }

    @Test
    void update() throws FileException, DaoException {
        PaymentDaoImpl paymentDao = new PaymentDaoImpl();
        if(paymentDao.findEntityById(this.id).isPresent()) {
            Payment payment = paymentDao.findEntityById(this.id).get();
            int amount = 460;
            int defaultAmount = paymentDao.findEntityById(this.id).get().getAmount();
            payment.setAmount(amount);
            assertEquals(payment,paymentDao.update(payment).get());
            payment.setAmount(defaultAmount);
            assertEquals(payment.getAmount(),paymentDao.update(payment).get().getAmount());
        }
    }

    @Test
    void findAllPaymentByCriteria() throws FileException, DaoException {
        PaymentCriteria paymentCriteria = new PaymentCriteria();
        paymentCriteria.setPaymentStatus(PaymentStatus.INPROGRESS);
        PaymentDaoImpl paymentDao = new PaymentDaoImpl();
        List<Payment> list = paymentDao.findAllEntities();
        int i = 0;
        while(i<list.size()){
            if(!(list.get(i).getPaymentStatus().equals(paymentCriteria.getPaymentStatus()))){
                list.remove(i);
            }
            i++;
        }
        assertEquals(list.size(),paymentDao.findAllPaymentByCriteria(paymentCriteria).size());
    }
}