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

    public Diet (int ID, String Name, String Description, int Calories,int[] Types, boolean isAssigned){
        this.ID=ID;
        this.Name=Name;
        this.Description=Description;
        this.Calories = Calories;
        this.Types = Types;
        this.isAssigned = isAssigned;
    }

    public String getName() {
        return Name;
    }

    public String getDescription() {
        return Description;
    }

    public int getID() {
        return ID;
    }

    public int getCalories() {
        return Calories;
    }

    public int[] getTypes() {
        return Types;
    }

    public ArrayList<Dish> getDishes() {
        return Dishes;
    }

    public boolean isAssigned() {
        return isAssigned;
    }
}


