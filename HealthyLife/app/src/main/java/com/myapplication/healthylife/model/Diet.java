package com.myapplication.healthylife.model;

import java.util.ArrayList;

public class Diet {
    private int ID;
    private String Name;
    private String Description;
    private String Note;
    private int Calories;
    private int[] Types;
    private boolean isAssigned;
    private boolean isRecommended;
    private boolean isFatAllowed;
    private boolean isCarbAllowed;
    private boolean isVegan;
    private int image;


    public Diet (int ID, String Name, String Description, String Note, int Calories,int[] Types,
                 boolean isAssigned, boolean isCarbAllowed, boolean isFatAllowed, boolean isVegan, int image){

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
        this.image = image;
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
    public int getImage() {
        return image;
    }
}


