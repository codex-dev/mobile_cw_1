package com.example.ravindu.mobilecoursework.ui;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ravindu.mobilecoursework.R;
import com.example.ravindu.mobilecoursework.common.BreedTypes;
import com.example.ravindu.mobilecoursework.model.DogBreed;
import com.example.ravindu.mobilecoursework.model.DogImage;
import com.example.ravindu.mobilecoursework.util.RandomPick;

import java.util.ArrayList;
import java.util.Objects;

public class ActSearchBreeds extends ActCommon implements View.OnClickListener, TextView.OnEditorActionListener {

    private BreedTypes breedTypes;
    private Handler handler;
    private Animation animEnterExit;

    private LinearLayout lytSlideshow;
    private EditText etSearchText;
    private ImageView ivDogImage;
    private TextView btnSubmit, btnStop;

    private ArrayList<DogImage> imagesList;

    int previousIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_search_breeds);
        setupActionbar(getString(R.string.title_search_dog_breeds), true);
        hideKeyboard(findViewById(R.id.uiSearchBreed), this);

        breedTypes = new BreedTypes();
        handler = new Handler();
        animEnterExit = AnimationUtils.loadAnimation(ActSearchBreeds.this, R.anim.slider_animation);

        initViews();
        setEventListeners();
    }

    private void initViews() {
        etSearchText = findViewById(R.id.etSearchText);
        btnSubmit = findViewById(R.id.btnSubmit);
        lytSlideshow = findViewById(R.id.lytSlideshow);
        ivDogImage = findViewById(R.id.ivDogImage);
        btnStop = findViewById(R.id.btnStop);
    }


    private void setEventListeners() {
        btnSubmit.setOnClickListener(this);
        btnStop.setOnClickListener(this);
        etSearchText.setOnEditorActionListener(this);
    }

    private boolean validateBreedName() {
        if (etSearchText.getText() == null || etSearchText.getText().toString().trim().length() == 0) {
            Toast.makeText(this, "Please input dog breed", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            String[] breedNames = getResources().getStringArray(R.array.array_dog_breeds);
            for (String breed : breedNames) {
                if (breed.equalsIgnoreCase(etSearchText.getText().toString().trim())) {
                    return true;
                }
            }
            Toast.makeText(this, "No matching dog breed found.", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private void fetchResults(CharSequence text) {
        // hide soft input keyboard if still visible
        etSearchText.clearFocus();
        InputMethodManager in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        Objects.requireNonNull(in).hideSoftInputFromWindow(etSearchText.getWindowToken(), 0);
        Toast.makeText(this, "Images Fetched for \"" + text + "\"", Toast.LENGTH_SHORT).show(); // for testing purpose

        // load relevant breed images as the result
        for (DogBreed dogBreed : breedTypes.getListDogBreeds()) {
            if (etSearchText.getText().toString().trim().equalsIgnoreCase(dogBreed.getBreedName())) {
                imagesList = dogBreed.getImageList();
                break;
            }
        }

        if (imagesList != null) {
            startSlideshow();
        }
    }

    private void startSlideshow() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                //show images in a random order
                int selectedIndex = RandomPick.generateRandomNumber(0, (imagesList.size() - 1));
                while (previousIndex == selectedIndex) { // to avoid setting same image for the next image
                    selectedIndex = RandomPick.generateRandomNumber(0, (imagesList.size() - 1));
                }
                previousIndex = selectedIndex;

                ivDogImage.startAnimation(animEnterExit);
                ivDogImage.setImageResource(imagesList.get(selectedIndex).getImageDrawable());

                handler.postDelayed(this, 5000);
            }
        };

        handler.postDelayed(runnable, 0); // start runnable without any delay

        etSearchText.setEnabled(false);
        btnSubmit.setEnabled(false);
        btnStop.setEnabled(true);
        btnStop.setBackground(getDrawable(R.drawable.btn_selector));
        btnSubmit.setBackground(getDrawable(R.drawable.bg_btn_disabled));
        lytSlideshow.setVisibility(View.VISIBLE);
    }

    private void stopSlideshow() {
        handler.removeCallbacksAndMessages(null);

        etSearchText.setEnabled(true);
        btnSubmit.setEnabled(true);
        btnStop.setEnabled(false);
        btnStop.setBackground(getDrawable(R.drawable.bg_btn_disabled));
        btnSubmit.setBackground(getDrawable(R.drawable.btn_selector));
        ivDogImage.clearAnimation();

        previousIndex = -1;
        Toast.makeText(this, "Slideshow Stopped", Toast.LENGTH_SHORT).show(); // for testing purpose
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSubmit:
                if (validateBreedName()) {
                    fetchResults(etSearchText.getText());
                }
                break;
            case R.id.btnStop:
                stopSlideshow();
                break;
        }
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        // perform search when search key pressed on soft input keyboard
        if (actionId == EditorInfo.IME_ACTION_SEARCH && validateBreedName()) {
            fetchResults(v.getText());
            return true;
        }
        return false;
    }
}

/*
 * References -
 * https://stackoverflow.com/a/3205405 - Android: how to make keyboard enter button say “Search” and handle its click?
 * https://stackoverflow.com/a/42829176
 * https://stackoverflow.com/a/22719065 - Stop handler.postDelayed()
 * https://stackoverflow.com/a/3919813 - Android: How can I stop an infinite animation applied on an ImageView?
 * https://stackoverflow.com/a/4112620 - How to stop an animation (cancel() does not work)
 * */
