package com.example.ravindu.mobilecoursework.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ravindu.mobilecoursework.R;
import com.example.ravindu.mobilecoursework.common.BreedTypes;
import com.example.ravindu.mobilecoursework.model.DogBreed;
import com.example.ravindu.mobilecoursework.model.DogImage;

public class ActIdentifyBreed extends ActCommon implements View.OnClickListener {

    private static BreedTypes breedTypes;
    private ImageView ivDogImage;
    private TextView btnSubmit;

    private boolean timerEnabled;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_identify_breed);
        setupActionbar(getString(R.string.title_identify_the_breed));

        breedTypes = new BreedTypes();
        intViews();
        setListeners();

        timerEnabled = getIntent().getBooleanExtra("timerEnabled", false);

        if (timerEnabled) {
            //TODO run timer
            Toast.makeText(this, "Timer Enabled", Toast.LENGTH_SHORT).show(); // only for testing purpose
        } else {
            Toast.makeText(this, "Timer Disabled", Toast.LENGTH_SHORT).show(); // only for testing purpose
        }

        setRandomImage();
    }

    private void intViews() {
        ivDogImage = findViewById(R.id.ivDogImage);
        btnSubmit = findViewById(R.id.btnSubmit);
    }

    private void setListeners() {
        btnSubmit.setOnClickListener(this);
    }

    private void setRandomImage() {

        /*
         * for the length of all images {
         *   generate 2 random numbers within each range; 1<=x<=12, ,1<=y<=5
         *   get image
         *   check whether appeared before
         *   if has appeared, generate another 2 numbers and try again.
         *   if not, show that image and flag it as appeared
         * }
         * */

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
                ivDogImage.setImageResource(dogImage.getImageDrawable());
                dogImage.setHasAppeared(true);
            }
        }
    }

    private int generateRandomNumber(int min, int max) {
        double value = (Math.random() * ((max - min) + 1)) + min;
        return (int) value;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSubmit:
                setRandomImage();
                break;
        }
    }
}

/*
 * References -
 *
 * https://dzone.com/articles/random-number-generation-in-java - generate random numbers within a given range
 *
 * */
