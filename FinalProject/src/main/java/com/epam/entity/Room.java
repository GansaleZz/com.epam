package com.epam.entity;

public class Room extends Entity{
    private RoomClass room_class;
    private RoomStatus room_status = RoomStatus.AVAILABLE;
    private int price;
    private int numberOfSeats;
    private int id;

    public Room(RoomClass room_class, int price, int numberOfSeats) {
        this.room_class = room_class;
        this.price = price;
        this.numberOfSeats = numberOfSeats;
    }

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

    @Override
    public String toString() {
        return "Room{" +
                "room_class=" + room_class +
                ", room_status=" + room_status +
                ", price=" + price +
                ", numberOfSeats=" + numberOfSeats +
                '}';
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }
}
