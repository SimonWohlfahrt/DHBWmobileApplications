package com.example.weitlos.geschenke;

import android.content.Intent;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private ConstraintLayout l;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        l = (ConstraintLayout)findViewById(R.id.layout);

    }

    public void setup(View sender) {
        Intent i = new Intent(this, setupActivity.class);
        startActivity(i);
    }
}
