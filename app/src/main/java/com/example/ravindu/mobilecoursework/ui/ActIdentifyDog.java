package com.example.ravindu.mobilecoursework.ui;

import android.os.Bundle;

import com.example.ravindu.mobilecoursework.R;

public class ActIdentifyDog extends ActCommon {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_identify_dog);
        setTitle(R.string.title_identify_the_dog);
    }
}
