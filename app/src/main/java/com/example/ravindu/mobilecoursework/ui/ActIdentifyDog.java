package com.example.ravindu.mobilecoursework.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ravindu.mobilecoursework.R;
import com.example.ravindu.mobilecoursework.common.BreedTypes;
import com.example.ravindu.mobilecoursework.model.DogBreed;
import com.example.ravindu.mobilecoursework.model.DogImage;

public class ActIdentifyDog extends ActCommon implements View.OnClickListener {

    private static BreedTypes breedTypes;
    private DogBreed[] arrBreeds; // breeds of randomly picked images
    private String selectedBreed; // selected breed for the question

    private TextView tvBreedName, tvTimer, tvResult, btnSubmit;
    private ImageView ivDogImage1, ivDogImage2, ivDogImage3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_identify_dog);
        setupActionbar(getString(R.string.title_identify_the_dog));

        initViews();
        setEventListeners();

        breedTypes = new BreedTypes();
        arrBreeds = new DogBreed[3];

        setRandomImages();
    }

    private void initViews() {
        tvBreedName = findViewById(R.id.tvBreedName);
        ivDogImage1 = findViewById(R.id.ivDogImage1);
        ivDogImage2 = findViewById(R.id.ivDogImage2);
        ivDogImage3 = findViewById(R.id.ivDogImage3);
        tvTimer = findViewById(R.id.tvTimer);
        tvResult = findViewById(R.id.tvResult);
        btnSubmit = findViewById(R.id.btnSubmit);
    }

    private void setEventListeners() {
        ivDogImage1.setOnClickListener(this);
        ivDogImage2.setOnClickListener(this);
        ivDogImage3.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivDogImage1:
                break;
            case R.id.ivDogImage2:
                break;
            case R.id.ivDogImage3:
                break;
            case R.id.btnSubmit:
                break;
        }
    }


    private void setRandomImages() {
/*
        for (DogBreed dogBreed : breedTypes.getListDogBreeds()) {

            boolean imageAppeared = false;

            for (int i=0; i<dogBreed.getImageList().size(); i++) {

                if (dogBreed.getImageList().get(i).isHasAppeared()) {
                    imageAppeared = true;
                } else {
                    imageAppeared = false;
                }
            }

            if(imageIndex)
        }*/
    }
}
