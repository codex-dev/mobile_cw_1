package com.example.ravindu.mobilecoursework.common;

import com.example.ravindu.mobilecoursework.R;
import com.example.ravindu.mobilecoursework.model.DogBreed;
import com.example.ravindu.mobilecoursework.model.DogImage;

import java.util.ArrayList;

/**
 * Created by Ravindu Fernando on 2020-03-02 at 04:43 PM
 */
public class BreedTypes {

    private final DogBreed beagle = new DogBreed("n02088364", "Beagle",
            new ArrayList<DogImage>() {{
                add(new DogImage(R.drawable.img_beagle_1));
                add(new DogImage(R.drawable.img_beagle_2));
                add(new DogImage(R.drawable.img_beagle_3));
                add(new DogImage(R.drawable.img_beagle_4));
                add(new DogImage(R.drawable.img_beagle_5));
            }});


    private final DogBreed chihuahua = new DogBreed("n02085620", "Chihuahua",
            new ArrayList<DogImage>() {{
                add(new DogImage(R.drawable.img_chihuahua_1));
                add(new DogImage(R.drawable.img_chihuahua_2));
                add(new DogImage(R.drawable.img_chihuahua_3));
                add(new DogImage(R.drawable.img_chihuahua_4));
                add(new DogImage(R.drawable.img_chihuahua_5));
            }});


    private final DogBreed siberian_husky = new DogBreed("n02110185", "Siberian Husky",
            new ArrayList<DogImage>() {{
                add(new DogImage(R.drawable.img_siberian_husky_1));
                add(new DogImage(R.drawable.img_siberian_husky_2));
                add(new DogImage(R.drawable.img_siberian_husky_3));
                add(new DogImage(R.drawable.img_siberian_husky_4));
                add(new DogImage(R.drawable.img_siberian_husky_5));
            }});


    private final DogBreed doberman = new DogBreed("n02107142", "Doberman",
            new ArrayList<DogImage>() {{
                add(new DogImage(R.drawable.img_doberman_1));
                add(new DogImage(R.drawable.img_doberman_2));
                add(new DogImage(R.drawable.img_doberman_3));
                add(new DogImage(R.drawable.img_doberman_4));
                add(new DogImage(R.drawable.img_doberman_5));
            }});


    private final DogBreed golden_retriever = new DogBreed("n02099601", "Golden Retriever",
            new ArrayList<DogImage>() {{
                add(new DogImage(R.drawable.img_golden_retriever_1));
                add(new DogImage(R.drawable.img_golden_retriever_2));
                add(new DogImage(R.drawable.img_golden_retriever_3));
                add(new DogImage(R.drawable.img_golden_retriever_4));
                add(new DogImage(R.drawable.img_golden_retriever_5));
            }});


    private final DogBreed boxer = new DogBreed("n02108089", "Boxer",
            new ArrayList<DogImage>() {{
                add(new DogImage(R.drawable.img_boxer_1));
                add(new DogImage(R.drawable.img_boxer_2));
                add(new DogImage(R.drawable.img_boxer_3));
                add(new DogImage(R.drawable.img_boxer_4));
                add(new DogImage(R.drawable.img_boxer_5));
            }});


    private final DogBreed rottweiler = new DogBreed("n02106550", "Rottweiler",
            new ArrayList<DogImage>() {{
                add(new DogImage(R.drawable.img_rottweiler_1));
                add(new DogImage(R.drawable.img_rottweiler_2));
                add(new DogImage(R.drawable.img_rottweiler_3));
                add(new DogImage(R.drawable.img_rottweiler_4));
                add(new DogImage(R.drawable.img_rottweiler_5));
            }});


    private final DogBreed german_shepherd = new DogBreed("n02106662", "German Shepherd",
            new ArrayList<DogImage>() {{
                add(new DogImage(R.drawable.img_german_shepherd_1));
                add(new DogImage(R.drawable.img_german_shepherd_2));
                add(new DogImage(R.drawable.img_german_shepherd_3));
                add(new DogImage(R.drawable.img_german_shepherd_4));
                add(new DogImage(R.drawable.img_german_shepherd_5));
            }});


    private final DogBreed shetland_sheepdog = new DogBreed("n02105855", "Shetland Sheepdog",
            new ArrayList<DogImage>() {{
                add(new DogImage(R.drawable.img_shetland_sheepdog_1));
                add(new DogImage(R.drawable.img_shetland_sheepdog_2));
                add(new DogImage(R.drawable.img_shetland_sheepdog_3));
                add(new DogImage(R.drawable.img_shetland_sheepdog_4));
                add(new DogImage(R.drawable.img_shetland_sheepdog_5));
            }});


    private final DogBreed afghan_hound = new DogBreed("n02088094", "Afghan Hound",
            new ArrayList<DogImage>() {{
                add(new DogImage(R.drawable.img_afghan_hound_1));
                add(new DogImage(R.drawable.img_afghan_hound_2));
                add(new DogImage(R.drawable.img_afghan_hound_3));
                add(new DogImage(R.drawable.img_afghan_hound_4));
                add(new DogImage(R.drawable.img_afghan_hound_5));
            }});


    private final DogBreed french_bulldog = new DogBreed("n02088094", "French Bulldog",
            new ArrayList<DogImage>() {{
                add(new DogImage(R.drawable.img_french_bulldog_1));
                add(new DogImage(R.drawable.img_french_bulldog_2));
                add(new DogImage(R.drawable.img_french_bulldog_3));
                add(new DogImage(R.drawable.img_french_bulldog_4));
                add(new DogImage(R.drawable.img_french_bulldog_5));
            }});


    private final DogBreed silky_terrier = new DogBreed("n02097658", "Silky Terrier",
            new ArrayList<DogImage>() {{
                add(new DogImage(R.drawable.img_silky_terrier_1));
                add(new DogImage(R.drawable.img_silky_terrier_2));
                add(new DogImage(R.drawable.img_silky_terrier_3));
                add(new DogImage(R.drawable.img_silky_terrier_4));
                add(new DogImage(R.drawable.img_silky_terrier_5));
            }});


    private final ArrayList<DogBreed> listDogBreeds = new ArrayList<DogBreed>() {{
        add(afghan_hound);
        add(beagle);
        add(boxer);
        add(chihuahua);
        add(doberman);
        add(french_bulldog);
        add(german_shepherd);
        add(golden_retriever);
        add(rottweiler);
        add(shetland_sheepdog);
        add(siberian_husky);
        add(silky_terrier);
    }};


    public ArrayList<DogBreed> getListDogBreeds() {
        return listDogBreeds;
    }

    public int getAllImagesCount() {
        int imageCount = 0;
        for (DogBreed dogBreed : listDogBreeds) {
            imageCount += dogBreed.getImageList().size();
        }
        return imageCount;
    }
}

/*
 * References -
 * https://stackoverflow.com/a/1005083 - Initialize an ArrayList in one line.
 * */
