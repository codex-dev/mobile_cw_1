package com.example.ravindu.mobilecoursework.ui;

import android.os.Bundle;
import android.view.Gravity;
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
        setupActionbar("", false);

    }

    protected void setupActionbar(String title, boolean showBackIcon) {
        Objects.requireNonNull(getSupportActionBar()).setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.custom_action_bar);
        View actionbarView = getSupportActionBar().getCustomView();

        ImageView ivBack = actionbarView.findViewById(R.id.ivBack);
        TextView tvTitle = actionbarView.findViewById(R.id.tvTitle);

        if (showBackIcon) {
            ivBack.setImageResource(R.drawable.ico_back_arrow);
            tvTitle.setPadding(0, 0, 0, 0);
            tvTitle.setGravity(Gravity.CENTER);
            ivBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onBackPressed();
                }
            });
        } else { // home ui
            ivBack.setImageResource(R.drawable.ico_paw);
            tvTitle.setPadding(100, 0, 0, 0);
            tvTitle.setGravity(Gravity.START);
        }
        tvTitle.setText(title);
    }
}

/*
 * References -
 * https://stackoverflow.com/a/45632962 - Why should one use Objects.requireNonNull()?
 * */

