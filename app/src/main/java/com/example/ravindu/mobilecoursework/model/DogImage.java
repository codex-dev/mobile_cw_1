package com.example.ravindu.mobilecoursework.model;

/**
 * Created by Ravindu Fernando on 2020-03-04 at 11:38 PM
 */
public class DogImage {

    private int imageDrawable;
    private boolean hasAppeared;

    public DogImage(int imageDrawable) {
        this.imageDrawable = imageDrawable;
        this.hasAppeared = false;
    }

    public DogImage(int imageDrawable, boolean hasAppeared) {
        this.imageDrawable = imageDrawable;
        this.hasAppeared = hasAppeared;
    }

    public int getImageDrawable() {
        return imageDrawable;
    }

    public void setImageDrawable(int imageDrawable) {
        this.imageDrawable = imageDrawable;
    }

    public boolean isHasAppeared() {
        return hasAppeared;
    }

    public void setHasAppeared(boolean hasAppeared) {
        this.hasAppeared = hasAppeared;
    }
}
