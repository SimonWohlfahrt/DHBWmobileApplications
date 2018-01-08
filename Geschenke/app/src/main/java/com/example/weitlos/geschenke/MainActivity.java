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

    private void setIdle() {
        l.setBackgroundColor(Color.parseColor("#ffffff"));
    }

    private void setSetting() {

    }

    private void setwaiting() {
        l.setBackgroundColor(Color.parseColor("#BB5500"));
    }

    private void setWelcome() {
        l.setBackgroundColor(Color.parseColor("#00BB22"));
    }

    public void setup(View sender) {
        Intent i = new Intent(this, setupActivity.class);
        startActivity(i);
    }
}
