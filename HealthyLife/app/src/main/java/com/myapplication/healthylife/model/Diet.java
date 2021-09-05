package com.myapplication.healthylife.model;

import java.util.ArrayList;

public class Diet {
    private int ID;
    private String Name;
    private String Description;
    private ArrayList<Dish> Dishes;
    private String Note;
    private int Calories;
    private int[] Types;
    private boolean isAssigned;
    private boolean isRecommended;
    private boolean isFatAllowed;
    private boolean isCarbAllowed;
    private boolean isVegan;

    public Diet (int ID, String Name, String Description, String Note, int Calories,int[] Types, boolean isFatAllowed, boolean isCarbAllowed,boolean isVegan, boolean isAssigned){
        this.ID=ID;
        this.Name=Name;
        this.Description=Description;
        this.Note=Note;
        this.Calories = Calories;
        this.Types = Types;
        this.isCarbAllowed=isCarbAllowed;
        this.isFatAllowed=isFatAllowed;
        this.isVegan=isVegan;
        this.isAssigned=isAssigned;
    }
    public Diet (int ID, String Name, String Description,String Note,  int Calories,int[] Types, boolean isFatAllowed, boolean isCarbAllowed, boolean isVegan){
        this.ID=ID;
        this.Name=Name;
        this.Description=Description;
        this.Calories = Calories;
        this.Types = Types;
        this.isCarbAllowed=isCarbAllowed;
        this.isFatAllowed=isFatAllowed;
        this.isVegan=isVegan;
    }

    public String getNote() {
        return Note;
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

    public boolean isVegan() {
        return isVegan;
    }

    public boolean isCarbAllowed() {
        return isCarbAllowed;
    }

    public boolean isFatAllowed() {
        return isFatAllowed;
    }

    public boolean isRecommended() {
        return isRecommended;
    }
}


