package com.epam.entity;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

public class Request extends Entity{
    private int numberOfSeats;
    private Date start;
    private Date end;
    private User user;
    private Room room;
    private RequestStatus requestStatus;
    private Payment payment;
    private int id;

    public Request(int numberOfSeats, Date start, Date end, User user,int id, RequestStatus requestStatus, Room room){
        this.numberOfSeats = numberOfSeats;
        this.start = start;
        this.end = end;
        this.user = user;
        this.id = id;
        this.requestStatus = requestStatus;
        this.room = room;
    }

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
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Request request = (Request) o;
        return numberOfSeats == request.numberOfSeats && id == request.id && start.equals(request.start) && end.equals(request.end) && user.equals(request.user) && room.equals(request.room) && requestStatus == request.requestStatus && payment.equals(request.payment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numberOfSeats, start, end, user, room, requestStatus, payment, id);
    }
}
