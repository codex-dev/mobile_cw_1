package com.example.ravindu.mobilecoursework.ui;

import android.os.Bundle;

import com.example.ravindu.mobilecoursework.R;

public class ActSearchBreeds extends ActCommon {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_search_breeds);
        setupActionbar(getString(R.string.title_search_dog_breeds), true);
    }
}
