package com.example.ravindu.mobilecoursework.model;

import com.example.ravindu.mobilecoursework.common.BreedTypes;

import java.util.ArrayList;

/**
 * Created by Ravindu Fernando on 2020-03-07 at 08:32 PM
 */
public class RandomPickResponse {

    private String selectedBreed;
    private ArrayList<DogImage> listSelectedDogImages;
    private BreedTypes breedTypes;

    public String getSelectedBreed() {
        return selectedBreed;
    }

    public void setSelectedBreed(String selectedBreed) {
        this.selectedBreed = selectedBreed;
    }

    public ArrayList<DogImage> getSelectedDogImagesList() {
        return listSelectedDogImages;
    }

    public void setSelectedDogImagesList(ArrayList<DogImage> listSelectedDogImages) {
        this.listSelectedDogImages = listSelectedDogImages;
    }

    public BreedTypes getBreedTypes() {
        return breedTypes;
    }

    public void setBreedTypes(BreedTypes breedTypes) {
        this.breedTypes = breedTypes;
    }
}
