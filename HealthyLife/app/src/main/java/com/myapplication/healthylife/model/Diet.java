package com.myapplication.healthylife.model;

import java.util.ArrayList;

public class Diet {
    private int ID;
    private String Name;
    private String Description;
    private ArrayList<Dish> Dishes;
    private int Calories;
    private int[] Types;
    public boolean isAssigned;
    public boolean isRecommended;

    public Diet (int ID, String Name, String Description, int Calories,int[] Types){
        this.ID=ID;
        this.Name=Name;
        this.Description=Description;
        this.Calories = Calories;
        this.Types = Types;
    }

    public String getName() {
        return Name;
    }

    public String getDescription() {
        return Description;
    }
}


