package com.example.weitlos.geschenke;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

public class setupActivity extends Activity implements Runnable {

    private Handler timerHandler;
    private SeekBar b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);

        ConstraintLayout l = (ConstraintLayout)findViewById(R.id.layout);
        l.setBackgroundColor(Color.parseColor("#FFFF22"));

        b = findViewById(R.id.seekBar_seconds);
        b.setMax(119);
        b.setProgress(5);
        b.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean bool) {
                ((TextView) findViewById(R.id.textView_seconds)).setText((i + 1) + "");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        timerHandler = new Handler();
    }

    public void start(View sender) {
        start();
    }

    private void start() {
        timerHandler.postDelayed(this, (b.getProgress() + 1) * 1000);

    }

    @Override
    public void run() {
        Intent i = new Intent(this, supriseActivity.class);
        i.putExtra("time", ((SeekBar)findViewById(R.id.seekBar_seconds)).getProgress() + 1);
        startActivity(i);
    }
}
