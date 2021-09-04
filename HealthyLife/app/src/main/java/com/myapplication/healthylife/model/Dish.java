package com.myapplication.healthylife.model;

public class Dish {
    private int ID;
    private String Name;
    private int Calories;
    private int Carb;
    private int Fat;
    private int Protein;

    public Dish(int id, String Name, int Calories, int Carb, int Fat, int Protein){
        this.ID=id;
        this.Name= Name;
        this.Calories=Calories;
        this.Carb=Carb;
        this.Fat=Fat;
        this.Protein=Protein;
    }

    public int getCalories() {
        return Calories;
    }

    public int getFat() {
        return Fat;
    }

    public int getCarb() {
        return Carb;
    }

    public int getProtein() {
        return Protein;
    }

    public String getName() {
        return Name;
    }
}
