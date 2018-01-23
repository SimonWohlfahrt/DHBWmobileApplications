package com.example.weitlos.livecoding02;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements SensorEventListener {

    private final int[] directions = new int[]{ 0, 1, 0, 1, 0, 1 }; // 0 = links, 1 = rechts
    private final float[] seconds = new float[]{ 2f, 3f, 4f, 2f, 3f, 4f };
    private int index = 0;

    private TextView t;
    private CountDownTimer cTimer;

    private Timer timer = new Timer(new Timer.AsyncResponse() {
        @Override
        public void processFinish(Float time) {
            levelUp();
        }

        @Override
        public void progressUpdate(Float time) {
            TextView t = findViewById(R.id.textView_time);
            t.setText(time + " Sekunden verbleiben");
        }
    });

    private Sensor rotator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SensorManager sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> s = sm.getSensorList(Sensor.TYPE_ROTATION_VECTOR);
        rotator = s.get(0);
        sm.registerListener(this, rotator, SensorManager.SENSOR_DELAY_NORMAL);

        //timer.doInBackground(seconds[index]);

        t = findViewById(R.id.textView_time);

        init();
    }

    private void init() {
        setVisualsForIndex(0);

        CountDownTimer cTimer = new CountDownTimer((long)seconds[0] * 1000, (long)10) {
            public void onTick(long millisUntilFinished) {
                t.setText("seconds remaining: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                t.setText("done!");
                levelUp();
            }};
    }

    private int levelUp() {
        if (index < 5)
            index++;

        setVisualsForIndex(index);

        CountDownTimer cTimer = new CountDownTimer((long)seconds[index] * 1000, (long)10) {
            public void onTick(long millisUntilFinished) {
                t.setText("seconds remaining: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                t.setText("next round!");
                levelUp();
            }};

        return index;
    }

    private void setVisualsForIndex(int index) {
        int dir = directions[index];
        float sec = seconds[index];

        ConstraintLayout c = findViewById(R.id.background);
        if (dir == 0)
            c.setBackgroundColor(Color.YELLOW);
        else
            c.setBackgroundColor(Color.BLUE);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.values[2] > 0) //rotation rechts
        {
            if(directions[index] == 0)
                try {
                    cTimer.cancel();
                } catch (Exception e) {

                }

        }
        else if (sensorEvent.values[2] < 0) //rotation links
        {
            if(directions[index] == 1)
                try {
                    cTimer.cancel();
                } catch (Exception e) {

                }
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
