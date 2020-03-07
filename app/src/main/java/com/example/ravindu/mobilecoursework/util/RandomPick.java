package com.example.ravindu.mobilecoursework.util;

import com.example.ravindu.mobilecoursework.common.BreedTypes;
import com.example.ravindu.mobilecoursework.model.DogImage;
import com.example.ravindu.mobilecoursework.model.RandomPickResponse;

import java.util.ArrayList;

/**
 * Created by Ravindu Fernando on 2020-03-04 at 11:18 PM
 */
public class RandomPick {
    public static int generateRandomNumber(int min, int max) {
        double value = (Math.random() * ((max - min) + 1)) + min;
        return (int) value;
    }

    public static RandomPickResponse pickRandomImage(BreedTypes breedTypes) {
        /* --- LOGIC ---
         * for the length of all images {
         *   generate 2 random numbers within each range; 1<=x<=12, ,1<=y<=5
         *   get image
         *   check whether appeared before
         *   if has appeared, generate another 2 numbers and try again.
         *   if not, show that image and flag it as appeared
         * }
         * */

        RandomPickResponse randomPickResponse = null;

        int indexBreed = generateRandomNumber(0, breedTypes.getListDogBreeds().size() - 1);
        int indexImage = generateRandomNumber(0, breedTypes.getListDogBreeds().get(indexBreed)
                .getImageList().size() - 1);
        boolean hasAppeared = true;

        while (hasAppeared) {
            DogImage dogImage = breedTypes.getListDogBreeds().get(indexBreed) // dog breed
                    .getImageList().get(indexImage); // dog image
            hasAppeared = dogImage.isHasAppeared();

            if (hasAppeared) {
                indexBreed = generateRandomNumber(0, breedTypes.getListDogBreeds().size() - 1);
                indexImage = generateRandomNumber(0, breedTypes.getListDogBreeds().get(indexBreed)
                        .getImageList().size() - 1);
            } else {
                dogImage.setHasAppeared(true);
                String breedName = breedTypes.getListDogBreeds().get(indexBreed).getBreedName();

                randomPickResponse = new RandomPickResponse();
                randomPickResponse.setSelectedBreed(breedName);
                randomPickResponse.setSelectedDogImagesList(new ArrayList<DogImage>(){{
                    add(dogImage);
                }});
                randomPickResponse.setBreedTypes(breedTypes);
            }
        }
        return randomPickResponse;
    }
}
