package com.epam.entity;

public abstract class BaseEntity {
    private int id;

    BaseEntity(){}

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }
}
