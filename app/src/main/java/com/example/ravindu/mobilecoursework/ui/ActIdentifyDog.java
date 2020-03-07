package com.example.ravindu.mobilecoursework.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ravindu.mobilecoursework.R;

public class ActIdentifyDog extends ActCommon implements View.OnClickListener{

    private TextView tvBreedName, tvTimer, tvResult, btnTimer;
    private ImageView ivDogImage1, ivDogImage2, ivDogImage3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_identify_dog);
        setupActionbar(getString(R.string.title_identify_the_dog));

        initViews();
        setEventListeners();
    }

    private void initViews() {
    }

    private void setEventListeners() {
    }

    @Override
    public void onClick(View view) {

    }
}
