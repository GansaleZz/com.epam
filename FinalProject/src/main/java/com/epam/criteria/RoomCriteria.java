package com.epam.criteria;

import com.epam.entity.RoomClass;
import com.epam.entity.RoomStatus;

public class RoomCriteria extends BaseCriteria{
    private RoomClass room_class = null;
    private RoomStatus room_status = null;
    private int price = Integer.valueOf(null);
    private int numberOfSeats = Integer.valueOf(null);

    public RoomCriteria(){}

    public RoomClass getRoom_class() {
        return room_class;
    }

    public void setRoom_class(RoomClass room_class) {
        this.room_class = room_class;
    }

    public RoomStatus getRoom_status() {
        return room_status;
    }

    public void setRoom_status(RoomStatus room_status) {
        this.room_status = room_status;
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
