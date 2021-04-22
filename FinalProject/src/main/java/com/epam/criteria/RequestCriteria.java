package com.epam.criteria;

import com.epam.entity.Payment;
import com.epam.entity.RequestStatus;

import java.time.LocalDateTime;

public class RequestCriteria extends BaseCriteria{
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
