package com.epam.criteria;

import com.epam.entity.Payment;
import com.epam.entity.RequestStatus;

import java.time.LocalDateTime;

public class RequestCriteria extends BaseCriteria{
    private int numberOfSeats = Integer.valueOf(null);
    private LocalDateTime start = null;
    private LocalDateTime end = null;
    private int userId = Integer.valueOf(null);
    private RequestStatus request_status = null;

    RequestCriteria(){}

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
}
