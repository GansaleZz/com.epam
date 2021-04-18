package com.epam.entity;

public class Room extends Entity{
    private RoomClass roomClass;
    private RoomStatus roomStatus;
    private int price;
    private int numberOfSeats;
    private int id;

    public Room(RoomClass room_class, int price, int numberOfSeats, RoomStatus roomStatus,int id) {
        this.roomClass = room_class;
        this.price = price;
        this.numberOfSeats = numberOfSeats;
        this.roomStatus = roomStatus;
        this.id = id;
    }

    public Room(RoomClass room_class, int price, int numberOfSeats, RoomStatus roomStatus) {
        this.roomClass = room_class;
        this.price = price;
        this.numberOfSeats = numberOfSeats;
        this.roomStatus = roomStatus;
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

    public void setRoomStatus(RoomStatus room_status) {
        this.roomStatus = room_status;
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
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomClass=" + roomClass +
                ", roomStatus=" + roomStatus +
                ", price=" + price +
                ", numberOfSeats=" + numberOfSeats +
                ", id=" + id +
                '}';
    }
}
