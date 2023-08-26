package com.example.thinkifylabsmachinecodingassignment.model;


public abstract class BaseModel {
    private int id;

    protected BaseModel() {
    }

    protected BaseModel(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
