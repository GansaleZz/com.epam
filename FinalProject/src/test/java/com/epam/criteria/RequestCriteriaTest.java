package com.epam.criteria;

import com.epam.criteria.impl.PaymentCriteria;
import com.epam.criteria.impl.RequestCriteria;
import com.epam.criteria.impl.RoomCriteria;
import com.epam.criteria.impl.UserCriteria;
import com.epam.entity.*;
import org.junit.jupiter.api.Test;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;

class RequestCriteriaTest {

    private final RequestStatus requestStatus = RequestStatus.PAID;
    private final int numberOfSeats =5;

    @Test
    Request createRequest(){
        User user = new UserCriteria.Builder()
                .newBuilder()
                .withName("Test")
                .withEmail("123")
                .withUserRole(UserRole.ADMIN)
                .withId(0)
                .build();

        Payment payment = new PaymentCriteria.Builder()
                .newBuilder()
                .withId(1)
                .build();

        Room room = new RoomCriteria.Builder()
                .newBuilder()
                .withId(0)
                .build();

        Request request = new RequestCriteria.Builder()
                .newBuilder()
                .withRequestStatus(requestStatus)
                .withNumberOfSeats(numberOfSeats)
                .withUser(user)
                .withRoomClass(RoomClass.BUSINESS)
                .withEnd(Calendar.getInstance().getTime())
                .withStart(Calendar.getInstance().getTime())
                .withId(0)
                .withPayment(payment)
                .withRoom(room)
                .build();
        assertNotNull(request);
        return request;
    }

    @Test
    void getNumberOfSeats() {
        RequestCriteria requestCriteria = new RequestCriteria();
        requestCriteria.setNumberOfSeats(createRequest().getNumberOfSeats());
        assertEquals(numberOfSeats, requestCriteria.getNumberOfSeats());
    }

    @Test
    void setNumberOfSeats() {
        RequestCriteria requestCriteria = new RequestCriteria();
        requestCriteria.setNumberOfSeats(createRequest().getNumberOfSeats());
        assertEquals(numberOfSeats, requestCriteria.getNumberOfSeats());
    }

    @Test
    void getRequestStatus() {
        RequestCriteria requestCriteria = new RequestCriteria();
        requestCriteria.setRequestStatus(createRequest().getRequestStatus());
        assertEquals(requestStatus,requestCriteria.getRequestStatus());
    }

    @Test
    void setRequestStatus() {
        RequestCriteria requestCriteria = new RequestCriteria();
        requestCriteria.setRequestStatus(createRequest().getRequestStatus());
        assertEquals(requestStatus,requestCriteria.getRequestStatus());
    }
}