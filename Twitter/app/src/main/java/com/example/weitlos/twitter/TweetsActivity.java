package com.example.weitlos.twitter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TweetsActivity extends Activity {

    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweets);

        linearLayout = findViewById(R.id.linearLayout);

        Intent i = getIntent();
        String[] tweets = i.getStringArrayExtra("tweets");

        for (String tweet:tweets) {
            TextView t = new TextView(this);
            t.setText(tweet);
            linearLayout.addView(t);
        }
    }
}
