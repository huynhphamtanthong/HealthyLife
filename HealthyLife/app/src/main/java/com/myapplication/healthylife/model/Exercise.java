package com.myapplication.healthylife.model;

public class Exercise {
    private String name;
    private String level;
    private int duration;
    private int progress;
    private int image;
    private boolean isFinished;

    public Exercise(String name, String level, int duration, int image) {
        this.name = name;
        this.level = level;
        this.duration = duration;
        this.progress = 0;
        this.image = image;
        this.isFinished = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }
}
