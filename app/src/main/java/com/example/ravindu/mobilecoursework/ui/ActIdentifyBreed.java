package com.example.ravindu.mobilecoursework.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ravindu.mobilecoursework.R;
import com.example.ravindu.mobilecoursework.common.BreedTypes;
import com.example.ravindu.mobilecoursework.model.DogImage;

public class ActIdentifyBreed extends ActCommon implements View.OnClickListener {

    private static BreedTypes breedTypes;
    private ImageView ivDogImage;
    private TextView tvResult, tvAnswer, btnSubmit;
    private Spinner spnBreed;
    private LinearLayout lytResult;

    private boolean timerEnabled;
    private String breedName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_identify_breed);
        setupActionbar(getString(R.string.title_identify_the_breed));

        intViews();
        setListeners();

        breedTypes = new BreedTypes();
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
        spnBreed = findViewById(R.id.spnBreed);
        lytResult = findViewById(R.id.lytResult);
        tvResult = findViewById(R.id.tvResult);
        tvAnswer = findViewById(R.id.tvAnswer);
        btnSubmit = findViewById(R.id.btnSubmit);

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item,
                getResources().getStringArray(R.array.array_dog_breeds));
        spnBreed.setAdapter(arrayAdapter);
    }

    private void setListeners() {
        btnSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSubmit:
                if (btnSubmit.getText().equals(getString(R.string.btn_submit))) {
                    btnSubmit.setText(getString(R.string.btn_next));
                    evaluateAnswer();
                } else if (btnSubmit.getText().equals(getString(R.string.btn_next))) {
                    btnSubmit.setText(getString(R.string.btn_submit));
                    lytResult.setVisibility(View.GONE);
                    setRandomImage();
                }
                break;
        }
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
                breedName = breedTypes.getListDogBreeds().get(indexBreed).getBreedName();
                dogImage.setHasAppeared(true);
            }
        }
    }

    private int generateRandomNumber(int min, int max) {
        double value = (Math.random() * ((max - min) + 1)) + min;
        return (int) value;
    }

    private void evaluateAnswer() {
        lytResult.setVisibility(View.VISIBLE);

        if (spnBreed.getSelectedItem().equals(breedName)) { // correct answer
            tvResult.setText(getString(R.string.msg_correct));
            tvResult.setTextColor(Color.GREEN);
            tvResult.setCompoundDrawablesRelativeWithIntrinsicBounds(getDrawable(R.drawable.ic_correct), null, null, null);
            tvAnswer.setVisibility(View.GONE);

        } else { // wrong answer
            tvResult.setText(getString(R.string.msg_wrong));
            tvResult.setTextColor(Color.RED);
            tvResult.setCompoundDrawablesRelativeWithIntrinsicBounds(getDrawable(R.drawable.ic_wrong), null, null, null);
            tvAnswer.setVisibility(View.VISIBLE);
            tvAnswer.setText(getString(R.string.correct_answer) + " " + breedName);
        }
    }
}

/*
 * References -
 *
 * https://dzone.com/articles/random-number-generation-in-java - generate random numbers within a given range
 *
 * */
