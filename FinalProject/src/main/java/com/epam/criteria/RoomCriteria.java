package com.epam.criteria;

import com.epam.entity.RoomClass;
import com.epam.entity.RoomStatus;

public class RoomCriteria extends BaseCriteria{
    private RoomClass roomClass = null;
    private RoomStatus roomStatus = null;
    private int price = 0;
    private int numberOfSeats = 0;

    public RoomCriteria(){}

    public RoomClass getRoomClass() {
        return roomClass;
    }

    public void setRoomClass(RoomClass roomClass) {
        this.roomClass = roomClass;
    }

    public RoomStatus getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(RoomStatus roomStatus) {
        this.roomStatus = roomStatus;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }
}
