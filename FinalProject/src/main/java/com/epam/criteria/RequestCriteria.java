package com.epam.criteria;

import com.epam.entity.RequestStatus;

public class RequestCriteria {
    private int numberOfSeats = 0;
    private RequestStatus requestStatus = null;

    public RequestCriteria(){}

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public RequestStatus getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(RequestStatus requestStatus) {
        this.requestStatus = requestStatus;
    }
}
