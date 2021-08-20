package com.myapplication.healthylife.model;

public class User {
    private String name;
    private float height;
    private float weight;
    private double bmi;

    public User(String name, float height, float weight) {
        this.name = name;
        this.height = height;
        this.weight = weight;
        bmi = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public double getBmi() {
        return bmi;
    }

    public void setBmi(double bmi) {
        this.bmi = bmi;
    }
}
