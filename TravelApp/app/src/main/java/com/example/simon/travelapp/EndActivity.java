package com.example.simon.travelapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class EndActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);

        setTitle("We got it!");
        Intent intent = getIntent();
        TextView textView_email = findViewById(R.id.textView_emailAdress);
        textView_email.setText(intent.getStringExtra("email"));
    }

    public void button_Back_onClick(View sender) {
        Intent intent = new Intent(this, StartActivity.class);
        startActivity(intent);
        finish();
    }
}
