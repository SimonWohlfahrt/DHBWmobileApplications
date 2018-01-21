package com.example.weitlos.prv_prototype_one;

import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        ConstraintLayout bg = findViewById(R.id.constraintLayout);
        bg.setBackgroundColor(Color.parseColor("#E6E6E6")); //Dark: #232930 Light: #E6E6E6
    }
}
