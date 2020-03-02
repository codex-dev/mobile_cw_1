package com.example.ravindu.mobilecoursework.util;

import com.example.ravindu.mobilecoursework.R;
import com.example.ravindu.mobilecoursework.model.DogBreed;

import java.util.ArrayList;

/**
 * Created by Ravindu Fernando on 2020-03-02 at 04:43 PM
 */
public class BreedTypes {

    public static final DogBreed beagle = new DogBreed("n02088364","Beagle",
            new ArrayList<Integer>() {{
                add(R.drawable.img_beagle_1);
                add(R.drawable.img_beagle_2);
                add(R.drawable.img_beagle_3);
                add(R.drawable.img_beagle_4);
                add(R.drawable.img_beagle_5);
            }});

}

/*
* References -
* https://stackoverflow.com/a/1005083 - Initialize an ArrayList in one line.
* */
