package com.epam.entity;

import java.time.LocalDateTime;

public class Request extends Entity{
    private int numberOfSeats;
    private LocalDateTime start;
    private LocalDateTime end;
    private int userId;
    private RequestStatus request_status = RequestStatus.INPROGRESS;
    private Payment payment;
    private int id;

    public Request(int numberOfSeats, LocalDateTime start, LocalDateTime end, int userId, Payment payment){
        this.numberOfSeats = numberOfSeats;
        this.start = start;
        this.end = end;
        this.userId = userId;
        this.payment = payment;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public RequestStatus getRequest_status() {
        return request_status;
    }

    public void setRequest_status(RequestStatus request_status) {
        this.request_status = request_status;
    }

    @Override
    public String toString() {
        return "Request{" +
                "numberOfSeats=" + numberOfSeats +
                ", start=" + start +
                ", end=" + end +
                ", userId=" + userId +
                ", request_status=" + request_status +
                ", payment=" + payment +
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
