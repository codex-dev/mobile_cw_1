package com.example.ravindu.mobilecoursework.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ravindu.mobilecoursework.R;

public class ActHome extends ActCommon implements View.OnClickListener,
        CompoundButton.OnCheckedChangeListener {

    private TextView btnIdentifyBreed, btnIdentifyDog, btnSearchDogBreeds;
    private Switch switchTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_home);
//        Objects.requireNonNull(getSupportActionBar()).hide(); // hide action bar in home activity
        setupActionbar(getString(R.string.app_name), false);

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
                intent.putExtra("timerEnabled", switchTimer.isChecked());
                startActivity(intent);
                break;

            case R.id.btnIdentifyDog:
                intent = new Intent(ActHome.this, ActIdentifyDog.class);
                intent.putExtra("timerEnabled", switchTimer.isChecked());
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

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.exit_warning_title))
                .setMessage(getString(R.string.exit_warning_message))
                .setNegativeButton(R.string.btn_yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        ActHome.super.onBackPressed();
                    }
                })
                .setPositiveButton(R.string.btn_no, null)
                .setIcon(R.drawable.ico_alert);

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        Button nbutton = alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE);
        nbutton.setTextColor(Color.RED);
    }
}

/*
 * References -
 * https://stackoverflow.com/a/45632962 - Why should one use Objects.requireNonNull()?
 * https://stackoverflow.com/a/2115770 - How do I display an alert dialog on Android?
 * */
