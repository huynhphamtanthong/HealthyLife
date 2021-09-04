package com.myapplication.healthylife.model;

public class Dish {
    private int ID;
    private String Name;
    private int Calories;
    private int Carb;
    private int Fat;
    private int Protein;
    private String description;
    private String tutorial;
    private String note;
    private String ingredients;
    private int image;
    private int video;

    public Dish(int id, String Name, int Calories, int Carb, int Fat, int Protein,
                String description, String tutorial, String note,
                String ingredients, int image, int video){
        this.ID=id;
        this.Name= Name;
        this.Calories=Calories;
        this.Carb=Carb;
        this.Fat=Fat;
        this.Protein=Protein;
        this.description = description;
        this.tutorial = tutorial;
        this.note = note;
        this.ingredients = ingredients;
        this.image = image;
        this.video = video;
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

    public String getDescription() {
        return description;
    }

    public String getTutorial() {
        return tutorial;
    }

    public String getNote() {
        return note;
    }

    public String getIngredients() {
        return ingredients;
    }

    public int getImage() {
        return image;
    }

    public int getVideo() {
        return video;
    }
}
