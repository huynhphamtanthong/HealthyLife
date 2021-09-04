package com.myapplication.healthylife.model;

public class Dish {
    private int ID;
    private String Name;
    private String Type;

    public Dish(int id, String Name, String Type){
        this.ID=id;
        this.Name= Name;
        this.Type=Type;
    }

    public String getType() {
        return Type;
    }

    public String getName() {
        return Name;
    }
}
