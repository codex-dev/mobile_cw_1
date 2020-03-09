package com.example.ravindu.mobilecoursework.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
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
            tvTitle.setPadding(120, 0, 0, 0);
            tvTitle.setGravity(Gravity.START);
        }
        tvTitle.setText(title);
    }

    public void hideKeyboard(View view, final Activity activity) {
        // Set up touch listener for non-text box views to hide keyboard.
        if (!(view instanceof EditText)) {
            if (view == null) return;
            view.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    hideSoftKeyboard(activity);
                    return false;
                }
            });
        }

        //If a layout container, iterate over children and seed recursion.
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View innerView = ((ViewGroup) view).getChildAt(i);
                hideKeyboard(innerView, activity);
            }
        }
    }

    private static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(
                Activity.INPUT_METHOD_SERVICE);
        if (activity.getCurrentFocus() != null) {
            Objects.requireNonNull(inputMethodManager).hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
        }
    }
}

/*
 * References -
 * https://stackoverflow.com/a/45632962 - Why should one use Objects.requireNonNull()?
 * https://stackoverflow.com/a/11656129 - How to hide soft keyboard on android after clicking outside EditText?
 *
 *  */

