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
import android.widget.Toast;

import com.example.ravindu.mobilecoursework.R;
import com.example.ravindu.mobilecoursework.common.BreedTypes;
import com.example.ravindu.mobilecoursework.util.RandomPick;

public class ActIdentifyBreed extends ActCommon implements View.OnClickListener {

    private static BreedTypes breedTypes;

    private ImageView ivDogImage;
    private TextView tvTimer, tvResult, tvAnswer, btnSubmit;
    private Spinner spnBreed;
    private String questionBreedName; // selected breed name for the question
    private LinearLayout lytResult;

    private CountDownTimer countDownTimer;
    private boolean timerEnabled; // whether countdown timer is enabled
    private long remainingTime; // remaining time to continue countdown (when resumed)
    private final long timerResetValue = 11000; // default countdown time period

    private int nextClickCount = 0; // next button click count

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_identify_breed);
        setupActionbar(getString(R.string.title_identify_the_breed), true);

        intViews();
        setEventListeners();

        breedTypes = new BreedTypes();
        remainingTime = timerResetValue;
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
        btnSubmit = findViewById(R.id.btnNext);

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

                    tvTimer.setText(String.format(getString(R.string.remaining_time) + " %02d:%02d", 0, seconds));
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
        if (view.getId() == R.id.btnNext) {
            if (btnSubmit.getText().equals(getString(R.string.btn_submit))) {
                btnSubmit.setText(getString(R.string.btn_next));
                spnBreed.setEnabled(false);
                if (timerEnabled && countDownTimer != null) {
                    tvTimer.setText(getString(R.string.remaining_time) + " 00:00");
                    countDownTimer.cancel(); // stop currently running countdown timer. otherwise 2 or more CDTs would run in parallel.
                }
                evaluateAnswer();

            } else if (btnSubmit.getText().equals(getString(R.string.btn_next))) {
                if (setRandomImage()) {
                    btnSubmit.setText(getString(R.string.btn_submit));
                    spnBreed.setEnabled(true);
                    spnBreed.setSelection(0); // reset spinner
                    lytResult.setVisibility(View.GONE);

                    remainingTime = timerResetValue;  // reset timer
                    startCountDown();
                }
            }
//            setRandomImage(); // for testing purpose
        }
    }

    // pick and set random dog image
    private boolean setRandomImage() {
        if (nextClickCount < breedTypes.getAllImagesCount()) {
            nextClickCount++;
            RandomPick.Response response = RandomPick.pickRandomImage(breedTypes);
            questionBreedName = response.getSelectedBreedNamesList().get(0);
            breedTypes = response.getBreedTypes();
            ivDogImage.setImageResource(response.getSelectedDogImagesList().get(0).getImageDrawable());

//            Toast.makeText(this, "Image No. " + nextClickCount, Toast.LENGTH_SHORT).show(); // for testing purpose
            return true; // return true if setting next random image is successful.
        } else {
            Toast.makeText(this, getString(R.string.no_images_left), Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    // check whether answer is correct or wrong
    private void evaluateAnswer() {
        lytResult.setVisibility(View.VISIBLE);

        if (spnBreed.getSelectedItem().equals(questionBreedName)) { // correct answer
            tvResult.setText(getString(R.string.msg_correct));
            tvResult.setTextColor(Color.GREEN);
            tvResult.setCompoundDrawablesRelativeWithIntrinsicBounds(getDrawable(R.drawable.ic_correct), null, null, null);
            tvAnswer.setVisibility(View.GONE);

        } else { // wrong answer
            tvResult.setText(getString(R.string.msg_wrong));
            tvResult.setTextColor(Color.RED);
            tvResult.setCompoundDrawablesRelativeWithIntrinsicBounds(getDrawable(R.drawable.ic_wrong), null, null, null);
            tvAnswer.setVisibility(View.VISIBLE);
            tvAnswer.setText(getString(R.string.correct_answer) + " " + questionBreedName);
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
