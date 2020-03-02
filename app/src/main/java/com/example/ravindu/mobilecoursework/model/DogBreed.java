package com.example.ravindu.mobilecoursework.model;

import java.util.ArrayList;

/**
 * Created by Ravindu Fernando on 2020-03-02 at 04:07 PM
 */
public class DogBreed {

    private String breedId;
    private String breedName;
    private ArrayList<Integer> imageList;

    public DogBreed(String breedId, String breedName, ArrayList<Integer> imageList) {
        this.breedId = breedId;
        this.breedName = breedName;
        this.imageList = imageList;
    }

    public String getBreedId() {
        return breedId;
    }

    public void setBreedId(String breedId) {
        this.breedId = breedId;
    }

    public String getBreedName() {
        return breedName;
    }

    public void setBreedName(String breedName) {
        this.breedName = breedName;
    }

    public ArrayList<Integer> getImageList() {
        return imageList;
    }

    public void setImageList(ArrayList<Integer> imageList) {
        this.imageList = imageList;
    }
}
