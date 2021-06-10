package com.epam.criteria.Impl;

import com.epam.criteria.BaseCriteria;
import com.epam.entity.Request;
import com.epam.entity.Room;
import com.epam.entity.RoomClass;
import com.epam.entity.RoomStatus;

public class RoomCriteria extends BaseCriteria<Room> {
    private RoomClass roomClass = null;
    private RoomStatus roomStatus = null;
    private int price = 0;
    private int numberOfSeats = 0;

    /**
     * Realisation of builder pattern for {@link Room}
     */
    public static class Builder extends BaseBuilder<Room>{

        public Builder newBuilder(){
            actualClass = new Room();
            return this;
        }

        public Builder withId(int id){
            actualClass.setId(id);
            return this;
        }

        public Builder withRoomClass(RoomClass roomClass){
            actualClass.setRoomClass(roomClass);
            return this;
        }

        public Builder withRoomStatus(RoomStatus roomStatus){
            actualClass.setRoomStatus(roomStatus);
            return this;
        }

        public Builder withPrice(int price){
            actualClass.setPrice(price);
            return this;
        }

        public Builder withRoomNumber(int roomNumber){
            actualClass.setRoomNumber(roomNumber);
            return this;
        }

        public Builder withNumberOfSeats(int numberOfSeats){
            actualClass.setNumberOfSeats(numberOfSeats);
            return this;
        }

        @Override
        protected Room getActual() {
            return actualClass;
        }
    }

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
