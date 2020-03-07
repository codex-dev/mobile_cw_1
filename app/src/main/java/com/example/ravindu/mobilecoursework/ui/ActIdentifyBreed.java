package com.example.ravindu.mobilecoursework.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.ravindu.mobilecoursework.R;
import com.example.ravindu.mobilecoursework.common.BreedTypes;
import com.example.ravindu.mobilecoursework.model.DogImage;
import com.example.ravindu.mobilecoursework.util.RandomNumber;

public class ActIdentifyBreed extends ActCommon implements View.OnClickListener {

    private static BreedTypes breedTypes;

    private ImageView ivDogImage;
    private TextView tvTimer, tvResult, tvAnswer, btnSubmit;
    private Spinner spnBreed;
    private LinearLayout lytResult;
    private CountDownTimer countDownTimer;

    private boolean timerEnabled;
    private String breedName;
    private long remainingTime;
    private final long timerResetValue = 10000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_identify_breed);
        setupActionbar(getString(R.string.title_identify_the_breed));

        intViews();
        setEventListeners();

        remainingTime = timerResetValue;
        breedTypes = new BreedTypes();
        timerEnabled = getIntent().getBooleanExtra("timerEnabled", false);

        setRandomImage();
    }

    @Override
    protected void onResume() {
        super.onResume();
        startCountDown();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (timerEnabled && countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

    // initialize each view
    private void intViews() {
        ivDogImage = findViewById(R.id.ivDogImage);
        tvTimer = findViewById(R.id.tvTimer);
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

    // set event listeners for views
    private void setEventListeners() {
        btnSubmit.setOnClickListener(this);
    }

    // start countdown timer
    private void startCountDown() {
        if (timerEnabled && remainingTime > 0) {
            tvTimer.setVisibility(View.VISIBLE);

            countDownTimer = new CountDownTimer(remainingTime, 1000) {
                @Override
                public void onTick(long millisLeft) {
                    remainingTime = millisLeft;
                    int seconds = (int) (millisLeft / 1000);

                    tvTimer.setText(String.format(getString(R.string.remaining_time)
                            + " %02d:%02d", 0, seconds));
                }

                @Override
                public void onFinish() {
                    tvTimer.setText(getString(R.string.remaining_time) + " 00:00");
                    if (btnSubmit.getText().equals(getString(R.string.btn_submit))) {
                        btnSubmit.performClick();
                    }
                }
            }.start();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSubmit:
                if (btnSubmit.getText().equals(getString(R.string.btn_submit))) {
                    if (timerEnabled && countDownTimer != null) {
                        tvTimer.setText(getString(R.string.remaining_time) + " 00:00");
                        countDownTimer.cancel(); // stop currently running countdown timer. otherwise 2 or more CDTs would run in parallel.
                    }
                    btnSubmit.setText(getString(R.string.btn_next));
                    evaluateAnswer();
                } else if (btnSubmit.getText().equals(getString(R.string.btn_next))) {
                    btnSubmit.setText(getString(R.string.btn_submit));
                    lytResult.setVisibility(View.GONE);
                    spnBreed.setSelection(0); // reset spinner
                    setRandomImage();

                    remainingTime = timerResetValue;  // reset timer
                    startCountDown();
                }
                break;
        }
    }

    // pick and set random dog image
    private void setRandomImage() {
        /* --- LOGIC ---
         * for the length of all images {
         *   generate 2 random numbers within each range; 1<=x<=12, ,1<=y<=5
         *   get image
         *   check whether appeared before
         *   if has appeared, generate another 2 numbers and try again.
         *   if not, show that image and flag it as appeared
         * }
         * */

        int indexBreed = RandomNumber.generateRandomNumber(0, breedTypes.getListDogBreeds().size() - 1);
        int indexImage = RandomNumber.generateRandomNumber(0, breedTypes.getListDogBreeds().get(indexBreed)
                .getImageList().size() - 1);
        boolean hasAppeared = true;

        while (hasAppeared) {
            DogImage dogImage = breedTypes.getListDogBreeds().get(indexBreed) // dog breed
                    .getImageList().get(indexImage); // dog image
            hasAppeared = dogImage.isHasAppeared();

            if (hasAppeared) {
                indexBreed = RandomNumber.generateRandomNumber(0, breedTypes.getListDogBreeds().size() - 1);
                indexImage = RandomNumber.generateRandomNumber(0, breedTypes.getListDogBreeds().get(indexBreed)
                        .getImageList().size() - 1);
            } else {
                ivDogImage.setImageResource(dogImage.getImageDrawable());
                breedName = breedTypes.getListDogBreeds().get(indexBreed).getBreedName();
                dogImage.setHasAppeared(true);
            }
        }
    }

    // check whether answer is correct or wrong
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
 * https://stackoverflow.com/a/46382882 - Use string resources to load values to spinner
 * https://stackoverflow.com/a/10032406 - Countdown timer in Android
 * */
