package com.dev.dormotory;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class DescriptionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);
        findViewById(R.id.back).setOnClickListener(v -> {
            onBackPressed();
            finish();
        });



    }
}
