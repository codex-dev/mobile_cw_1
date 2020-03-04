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

    private int count; // only for testing purpose

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
            Toast.makeText(this, "Timer Enabled", Toast.LENGTH_SHORT).show(); // only for testing purpose
        } else {
            Toast.makeText(this, "Timer Disabled", Toast.LENGTH_SHORT).show(); // only for testing purpose
        }

        showDogImage();
    }

    private void intViews() {
        ivDogImage = findViewById(R.id.ivDogImage);
        btnSubmit = findViewById(R.id.btnSubmit);
    }

    private void setListeners() {
        btnSubmit.setOnClickListener(this);
    }

    private void showDogImage() {
        int imageDrawable = getRandomImage();
        if (imageDrawable != -1) {
            ivDogImage.setImageResource(imageDrawable);
            Toast.makeText(this, "Showing image " + (++count), Toast.LENGTH_SHORT).show(); // only for testing purpose
        } else {
            Toast.makeText(this, "End of List Reached", Toast.LENGTH_SHORT).show(); // only for testing purpose
        }
    }

    private int getRandomImage() {
        for (DogBreed dogBreed : breedTypes.getListDogBreeds()) {
            for (int i = 0; i < dogBreed.getImageList().size(); i++) {
                DogImage dogImage = dogBreed.getImageList().get(i);
                if (!dogImage.isHasAppeared()) {
                    dogImage.setHasAppeared(true);
                    return dogImage.getImageDrawable();
                }
            }
        }
        return -1; // all images have been appeared
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSubmit:
                showDogImage();
                break;
        }
    }
}
