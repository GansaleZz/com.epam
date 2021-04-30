package com.epam.entity;

public class BaseEntity {
    private int id;

    BaseEntity(int id){
        this.id = id;
    }

    BaseEntity(){}

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }
}
