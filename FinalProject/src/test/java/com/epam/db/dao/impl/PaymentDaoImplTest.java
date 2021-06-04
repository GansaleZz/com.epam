package com.epam.db.dao.impl;

import com.epam.criteria.PaymentCriteria;
import com.epam.db.ConnectionPool;
import com.epam.entity.Payment;
import com.epam.entity.PaymentStatus;
import com.epam.exceptions.DaoException;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PaymentDaoImplTest {

    private final int id = -1;

    @Order(1)
    @Test
    void findAllEntities() throws DaoException, SQLException {
        PaymentDaoImpl paymentDao = new PaymentDaoImpl();
        List<Payment> list = paymentDao.findAllEntities();
        Connection connection = ConnectionPool.getInstance().getConnection();
        final String COUNT = "SELECT COUNT(*) FROM Payment";
        ResultSet resultSet = connection.createStatement().executeQuery(COUNT);
        resultSet.next();
        int count = resultSet.getInt(1);
        assertEquals(count,list.size());
        ConnectionPool.getInstance().close(connection);
    }

    @Order(2)
    @Test
    void create() throws DaoException {
        PaymentDaoImpl paymentDao = new PaymentDaoImpl();
        Payment payment = new PaymentCriteria.Builder()
                .newBuilder()
                .withId(this.id)
                .withDate(Calendar.getInstance().getTime())
                .withAmount(1000)
                .withPaymentStatus(PaymentStatus.PAID)
                .build();
        assertEquals(true,paymentDao.create(payment));
    }

    @Order(3)
    @Test
    void findEntityById() throws DaoException {
        int amount = 1000;
        PaymentDaoImpl paymentDao = new PaymentDaoImpl();
        assertEquals(amount,paymentDao.findEntityById(this.id).get().getAmount());
    }

    @Order(4)
    @Test
    void findAllPaymentByCriteria() throws DaoException {
        PaymentCriteria paymentCriteria = new PaymentCriteria();
        paymentCriteria.setPaymentStatus(PaymentStatus.PAID);
        PaymentDaoImpl paymentDao = new PaymentDaoImpl();
        List<Payment> list = paymentDao.findAllEntities();
        int i = 0;
        while(i<list.size()){
            if(!(list.get(i).getStatus().equals(paymentCriteria.getPaymentStatus()))){
                list.remove(i);
            }
            i++;
        }
        assertEquals(list.size(),paymentDao.findAllPaymentByCriteria(paymentCriteria).size());
    }

    @Order(5)
    @Test
    void update() throws DaoException {
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

    @Order(6)
    @Test
    void delete() throws DaoException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        PaymentDaoImpl paymentDao = new PaymentDaoImpl();
        assertEquals(true, paymentDao.delete(id));
        assertEquals(Optional.empty(), paymentDao.findEntityById(id));
        assertEquals(false, paymentDao.delete(id));
        ConnectionPool.getInstance().close(connection);
    }
}