package com.example.ravindu.mobilecoursework.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ravindu.mobilecoursework.R;

import java.util.Objects;

public class ActCommon extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_common);
        setupActionbar("");

    }

    protected void setupActionbar(String title) {
        Objects.requireNonNull(getSupportActionBar()).setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.custom_action_bar);
        View actionbarView = getSupportActionBar().getCustomView();

        ImageView ivBack = actionbarView.findViewById(R.id.ivBack);
        TextView tvTitle = actionbarView.findViewById(R.id.tvTitle);

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        tvTitle.setText(title);
    }
}

/*
 * References -
 * https://stackoverflow.com/a/45632962 - Why should one use Objects.requireNonNull()?
 * */

