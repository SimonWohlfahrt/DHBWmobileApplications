package com.example.weitlos.rastapi;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ConsumeCloud cloudConsumer = new ConsumeCloud();

        cloudConsumer.execute("http://webtechlecture.appspot.com/personen/list");



    }
}