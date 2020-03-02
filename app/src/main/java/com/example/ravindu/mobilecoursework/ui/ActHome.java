package com.example.ravindu.mobilecoursework.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ravindu.mobilecoursework.R;

public class ActHome extends ActCommon implements View.OnClickListener,
        CompoundButton.OnCheckedChangeListener {

    private TextView btnIdentifyBreed, btnIdentifyDog, btnSearchDogBreeds;
    private Switch switchTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_home);
        getSupportActionBar().hide(); // hide action bar in home activity

        initViews();
        setListeners();
    }

    private void initViews() {
        btnIdentifyBreed = findViewById(R.id.btnIdentifyBreed);
        btnIdentifyDog = findViewById(R.id.btnIdentifyDog);
        btnSearchDogBreeds = findViewById(R.id.btnSearchBreeds);
        switchTimer = findViewById(R.id.switchTimer);
    }

    private void setListeners() {
        btnIdentifyBreed.setOnClickListener(this);
        btnIdentifyDog.setOnClickListener(this);
        btnSearchDogBreeds.setOnClickListener(this);
        switchTimer.setOnCheckedChangeListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent;

        switch (view.getId()) {
            case R.id.btnIdentifyBreed:
                intent = new Intent(ActHome.this, ActIdentifyBreed.class);
                startActivity(intent);
                break;

            case R.id.btnIdentifyDog:
                intent = new Intent(ActHome.this, ActIdentifyDog.class);
                startActivity(intent);
                break;

            case R.id.btnSearchBreeds:
                intent = new Intent(ActHome.this, ActSearchBreeds.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton switchTimer, boolean isChecked) {
        if (switchTimer.isChecked()) {
            switchTimer.setText(getString(R.string.countdown_on));
            Toast.makeText(this, getString(R.string.countdown_on), Toast.LENGTH_SHORT).show();
        } else {
            switchTimer.setText(getString(R.string.countdown_off));
            Toast.makeText(this, getString(R.string.countdown_off), Toast.LENGTH_SHORT).show();
        }
    }
}
