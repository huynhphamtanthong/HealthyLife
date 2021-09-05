package com.myapplication.healthylife.model;

public class Dish {
    private int ID;
    private String Name;
    private int[] Types;
    private String description;
    private String tutorial;
    private String note;
    private String ingredients;
    private int image;
    private int video;
    private boolean isCarb;
    private boolean isFat;
    private boolean isVegan;
    private boolean isBreakfast;
    private boolean isLunch;
    private boolean isDinner;

    public Dish(int id, String Name, int[] Types,
                String description, String tutorial, String note,
                String ingredients, int image, int video,
                boolean isBreakfast, boolean isLunch, boolean isDinner){
        this.ID=id;
        this.Name= Name;
        this.Types = Types;
        this.description = description;
        this.tutorial = tutorial;
        this.note = note;
        this.ingredients = ingredients;
        this.image = image;
        this.video = video;
        this.isBreakfast = isBreakfast;
        this.isLunch = isLunch;
        this.isDinner = isDinner;
    }

    public int getID() {
        return ID;
    }

    public int[] getTypes() {
        return Types;
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

    public boolean isBreakfast() {
        return isBreakfast;
    }

    public boolean isLunch() {
        return isLunch;
    }

    public boolean isDinner() {
        return isDinner;
    }
}
