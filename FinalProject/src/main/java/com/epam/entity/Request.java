package com.epam.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Request extends BaseEntity implements Serializable {
    private int numberOfSeats;
    private Date start;
    private Date end;
    private User user;
    private Room room;
    private RoomClass roomClass;
    private RequestStatus requestStatus;
    private Payment payment;

    public Request(){}

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public RoomClass getRoomClass() {
        return roomClass;
    }

    public void setRoomClass(RoomClass roomClass) {
        this.roomClass = roomClass;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public RequestStatus getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(RequestStatus requestStatus) {
        this.requestStatus = requestStatus;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    @Override
    public String toString() {
        return "Request{" +
                "numberOfSeats=" + numberOfSeats +
                ", start=" + start +
                ", end=" + end +
                ", user=" + user +
                ", room=" + room +
                ", roomClass=" + roomClass +
                ", requestStatus=" + requestStatus +
                ", payment=" + payment +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Request request = (Request) o;
        return numberOfSeats == request.numberOfSeats && Objects.equals(start, request.start) && Objects.equals(end, request.end) && Objects.equals(user, request.user) && Objects.equals(room, request.room) && roomClass == request.roomClass && requestStatus == request.requestStatus && Objects.equals(payment, request.payment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numberOfSeats, start, end, user, room, roomClass, requestStatus, payment);
    }
}
