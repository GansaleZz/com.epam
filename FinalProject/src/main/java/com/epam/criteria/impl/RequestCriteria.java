package com.epam.criteria.impl;

import com.epam.criteria.BaseCriteria;
import com.epam.entity.Payment;
import com.epam.entity.Request;
import com.epam.entity.RequestStatus;
import com.epam.entity.Room;
import com.epam.entity.RoomClass;
import com.epam.entity.User;

import java.util.Date;

public class RequestCriteria extends BaseCriteria<Request> {
    private int numberOfSeats = 0;
    private RequestStatus requestStatus = null;

    /**
     * Realisation of builder pattern for {@link Request}
     */
    public static class Builder extends BaseBuilder<Request>{

        public Builder newBuilder() {
            actualClass = new Request();
            return this;
        }

        public Builder withId(int id){
            actualClass.setId(id);
            return this;
        }

        public Builder withNumberOfSeats(int numberOfSeats){
            actualClass.setNumberOfSeats(numberOfSeats);
            return this;
        }

        public Builder withStart(Date start){
            actualClass.setStart(start);
            return this;
        }

        public Builder withEnd(Date end){
            actualClass.setEnd(end);
            return this;
        }

        public Builder withUser(User user){
            actualClass.setUser(user);
            return this;
        }

        public Builder withRoom(Room room){
            actualClass.setRoom(room);
            return this;
        }

        public Builder withRequestStatus(RequestStatus requestStatus){
            actualClass.setRequestStatus(requestStatus);
            return this;
        }

        public Builder withPayment(Payment payment){
            actualClass.setPayment(payment);
            return this;
        }

        public Builder withRoomClass(RoomClass roomClass){
            actualClass.setRoomClass(roomClass);
            return this;
        }

        @Override
        protected Request getActual() {
            return actualClass;
        }
    }

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
