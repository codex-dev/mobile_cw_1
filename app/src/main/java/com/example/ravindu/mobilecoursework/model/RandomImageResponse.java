package com.example.ravindu.mobilecoursework.model;

import java.util.ArrayList;

/**
 * Created by Ravindu Fernando on 2020-03-07 at 08:32 PM
 */
public class RandomImageResponse {

    private String selectedBreed;
    private ArrayList<DogBreed> listSelectedDogImages;

    public String getSelectedBreed() {
        return selectedBreed;
    }

    public void setSelectedBreed(String selectedBreed) {
        this.selectedBreed = selectedBreed;
    }

    public ArrayList<DogBreed> getListSelectedDogImages() {
        return listSelectedDogImages;
    }

    public void setListSelectedDogImages(ArrayList<DogBreed> listSelectedDogImages) {
        this.listSelectedDogImages = listSelectedDogImages;
    }
}
