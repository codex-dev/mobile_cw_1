package com.example.ravindu.mobilecoursework.ui;

import android.os.Bundle;

import com.example.ravindu.mobilecoursework.R;

public class ActIdentifyBreed extends ActCommon {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_identify_breed);
        setupActionbar(getString(R.string.title_identify_the_breed));

    }

}
