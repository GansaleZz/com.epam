package com.epam.criteria;

import com.epam.criteria.impl.RoomCriteria;
import com.epam.entity.Room;
import com.epam.entity.RoomClass;
import com.epam.entity.RoomStatus;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoomCriteriaTest {

    private RoomClass roomClass = RoomClass.LUXE;
    private RoomStatus roomStatus = RoomStatus.CLOSED;
    private int price = 1000;
    private int numberOfSeats = 5;


    @Test
    Room createRoom(){
        Room room = new RoomCriteria.Builder()
                .newBuilder()
                .withId(1)
                .withRoomClass(roomClass)
                .withRoomStatus(roomStatus)
                .withPrice(price)
                .withNumberOfSeats(5)
                .withRoomNumber(100)
                .build();
        assertNotNull(room);
        return room;
    }

    @Test
    void getRoomClass() {
        RoomCriteria roomCriteria = new RoomCriteria();
        roomCriteria.setRoomClass(createRoom().getRoomClass());
        assertEquals(roomClass,roomCriteria.getRoomClass());
    }

    @Test
    void setRoomClass() {
        RoomCriteria roomCriteria = new RoomCriteria();
        roomCriteria.setRoomClass(createRoom().getRoomClass());
        assertEquals(roomClass,roomCriteria.getRoomClass());
    }

    @Test
    void getRoomStatus() {
        RoomCriteria roomCriteria = new RoomCriteria();
        roomCriteria.setRoomStatus(createRoom().getRoomStatus());
        assertEquals(roomStatus,roomCriteria.getRoomStatus());
    }

    @Test
    void setRoomStatus() {
        RoomCriteria roomCriteria = new RoomCriteria();
        roomCriteria.setRoomStatus(createRoom().getRoomStatus());
        assertEquals(roomStatus,roomCriteria.getRoomStatus());
    }

    @Test
    void getPrice() {
        RoomCriteria roomCriteria = new RoomCriteria();
        roomCriteria.setPrice(createRoom().getPrice());
        assertEquals(price,roomCriteria.getPrice());
    }

    @Test
    void setPrice() {
        RoomCriteria roomCriteria = new RoomCriteria();
        roomCriteria.setPrice(createRoom().getPrice());
        assertEquals(price,roomCriteria.getPrice());
    }

    @Test
    void getNumberOfSeats() {
        RoomCriteria roomCriteria = new RoomCriteria();
        roomCriteria.setNumberOfSeats(createRoom().getNumberOfSeats());
        assertEquals(numberOfSeats,roomCriteria.getNumberOfSeats());
    }

    @Test
    void setNumberOfSeats() {
        RoomCriteria roomCriteria = new RoomCriteria();
        roomCriteria.setNumberOfSeats(createRoom().getNumberOfSeats());
        assertEquals(numberOfSeats,roomCriteria.getNumberOfSeats());
    }
}