package com.myapplication.healthylife.model;

public class Dish {
    private int ID;
    private String Name;
    private String Type;
    private String description;
    private String tutorial;
    private String note;
    private String ingredients;
    private int image;
    private int video;

    public Dish(int id, String Name, String Type,
                String description, String tutorial, String note,
                String ingredients, int image, int video){
        this.ID=id;
        this.Name= Name;
        this.Type=Type;
        this.description = description;
        this.tutorial = tutorial;
        this.note = note;
        this.ingredients = ingredients;
        this.image = image;
        this.video = video;
    }

    public String getType() {
        return Type;
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
