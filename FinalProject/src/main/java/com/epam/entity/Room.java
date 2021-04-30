package com.epam.entity;

import java.util.Objects;

public class Room extends BaseEntity {
    private RoomClass roomClass;
    private RoomStatus roomStatus;
    private int price;
    private int numberOfSeats;
    public Room(RoomClass room_class, int price, int numberOfSeats, RoomStatus roomStatus,int id) {
        super(id);
        this.roomClass = room_class;
        this.price = price;
        this.numberOfSeats = numberOfSeats;
        this.roomStatus = roomStatus;
    }

    public Room(RoomClass roomClass, int price, int numberOfSeats, RoomStatus roomStatus) {
        this.roomClass = roomClass;
        this.price = price;
        this.numberOfSeats = numberOfSeats;
        this.roomStatus = roomStatus;
    }

    public Room(RoomClass roomClass, int price, int numberOfSeats){
        this.roomClass = roomClass;
        this.price = price;
        this.numberOfSeats = numberOfSeats;
    }

    public RoomClass getRoomClass() {
        return roomClass;
    }

    public void setRoomClass(RoomClass room_class) {
        this.roomClass = room_class;
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

    @Override
    public String toString() {
        return "Room{" +
                "roomClass=" + roomClass +
                ", roomStatus=" + roomStatus +
                ", price=" + price +
                ", numberOfSeats=" + numberOfSeats +
                ", id=" + super.getId() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return price == room.price && numberOfSeats == room.numberOfSeats && roomClass == room.roomClass && roomStatus == room.roomStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomClass, roomStatus, price, numberOfSeats);
    }
}
