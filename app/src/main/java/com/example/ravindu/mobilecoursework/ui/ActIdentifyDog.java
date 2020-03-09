package com.example.ravindu.mobilecoursework.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ravindu.mobilecoursework.R;
import com.example.ravindu.mobilecoursework.common.BreedTypes;
import com.example.ravindu.mobilecoursework.util.RandomPick;

public class ActIdentifyDog extends ActCommon implements View.OnClickListener {

    private static BreedTypes breedTypes;

    private TextView tvBreedName, tvTimer, tvResult, btnNext;
    private ImageView ivDogImage1, ivDogImage2, ivDogImage3;

    private String questionBreedName; // selected breed name for the question
    private RandomPick.Response response;
    private int nextClickCCount = 0;
    private int totalUniqueTriples = 20;
    private boolean isClickedImg1, isClickedImg2, isClickedImg3;

    private CountDownTimer countDownTimer;
    private boolean timerEnabled; // whether countdown timer is enabled
    private long remainingTime; // remaining time to continue countdown (when resumed)
    private final long timerResetValue = 11000; // default countdown time period

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_identify_dog);
        setupActionbar(getString(R.string.title_identify_the_dog), true);

        initViews();
        setEventListeners();

        breedTypes = new BreedTypes();
        remainingTime = timerResetValue;
        timerEnabled = getIntent().getBooleanExtra("timerEnabled", false);

        setRandomImages();
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

    private void initViews() {
        tvBreedName = findViewById(R.id.tvBreedName);
        ivDogImage1 = findViewById(R.id.ivDogImage1);
        ivDogImage2 = findViewById(R.id.ivDogImage2);
        ivDogImage3 = findViewById(R.id.ivDogImage3);
        tvTimer = findViewById(R.id.tvTimer);
        tvResult = findViewById(R.id.tvResult);
        btnNext = findViewById(R.id.btnNext);
    }

    private void setEventListeners() {
        ivDogImage1.setOnClickListener(this);
        ivDogImage2.setOnClickListener(this);
        ivDogImage3.setOnClickListener(this);
        btnNext.setOnClickListener(this);
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
                    if (!isClickedImg1 && !isClickedImg2 && !isClickedImg3) { // user hasn't clicked on any image
                        evaluateAnswer("");
                    }
                }
            }.start();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivDogImage1:
                isClickedImg1 = true;
                ivDogImage1.setBackground(getDrawable(R.drawable.bg_outline_blue));
                evaluateAnswer(response.getSelectedBreedNamesList().get(0));
                break;
            case R.id.ivDogImage2:
                isClickedImg2 = true;
                ivDogImage2.setBackground(getDrawable(R.drawable.bg_outline_blue));
                evaluateAnswer(response.getSelectedBreedNamesList().get(1));
                break;
            case R.id.ivDogImage3:
                isClickedImg3 = true;
                ivDogImage3.setBackground(getDrawable(R.drawable.bg_outline_blue));
                evaluateAnswer(response.getSelectedBreedNamesList().get(2));
                break;
            case R.id.btnNext:
                if (tvResult.getVisibility() == View.VISIBLE && setRandomImages()) {
                    gotoNext();
                } else if (!isClickedImg1 && !isClickedImg2 && !isClickedImg3) {
                    evaluateAnswer("");
                }
                break;
        }
    }

    private boolean setRandomImages() {
        if (nextClickCCount < totalUniqueTriples) {
            nextClickCCount++;
            response = RandomPick.pick3RandomImages(breedTypes);
            questionBreedName = response.getSelectedBreedNamesList().
                    get(RandomPick.generateRandomNumber(0, response.getSelectedBreedNamesList().size() - 1));
            breedTypes = response.getBreedTypes();
            tvBreedName.setText(questionBreedName);
            ivDogImage1.setImageResource(response.getSelectedDogImagesList().get(0).getImageDrawable());
            ivDogImage2.setImageResource(response.getSelectedDogImagesList().get(1).getImageDrawable());
            ivDogImage3.setImageResource(response.getSelectedDogImagesList().get(2).getImageDrawable());

            return true;
        } else {
            Toast.makeText(this, getString(R.string.no_images_left), Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private void evaluateAnswer(String answer) {
        if (timerEnabled && countDownTimer != null) {
            tvTimer.setText(getString(R.string.remaining_time) + " 00:00");
            countDownTimer.cancel(); // stop currently running countdown timer. otherwise 2 or more CDTs would run in parallel.
        }
        ivDogImage1.setClickable(false);
        ivDogImage2.setClickable(false);
        ivDogImage3.setClickable(false);
        tvResult.setVisibility(View.VISIBLE);

        if (questionBreedName.equals(answer)) {
            tvResult.setText(getString(R.string.msg_correct));
            tvResult.setTextColor(Color.GREEN);
            tvResult.setCompoundDrawablesRelativeWithIntrinsicBounds(getDrawable(R.drawable.ic_correct), null, null, null);
        } else {
            tvResult.setText(getString(R.string.msg_wrong));
            tvResult.setTextColor(Color.RED);
            tvResult.setCompoundDrawablesRelativeWithIntrinsicBounds(getDrawable(R.drawable.ic_wrong), null, null, null);
        }
    }

    private void gotoNext() {
        ivDogImage1.setClickable(true);
        ivDogImage2.setClickable(true);
        ivDogImage3.setClickable(true);
        isClickedImg1 = false;
        isClickedImg2 = false;
        isClickedImg3 = false;
        ivDogImage1.setBackground(getDrawable(R.drawable.bg_outline_grey));
        ivDogImage2.setBackground(getDrawable(R.drawable.bg_outline_grey));
        ivDogImage3.setBackground(getDrawable(R.drawable.bg_outline_grey));
        tvResult.setVisibility(View.GONE);

        remainingTime = timerResetValue;  // reset timer
        startCountDown();
    }
}
