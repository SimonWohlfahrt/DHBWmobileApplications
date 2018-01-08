package com.example.simon.bewegung;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;
import java.util.Random;

public class MainActivity extends Activity implements SensorEventListener{

    private final int init_target_value = 500;
    private final int increment = 200;
    private int target_value = init_target_value;
    private int level = 1;
    private boolean isNegative = false;
    private int axis = 0;
    private Sensor acc_sensor;
    private SensorManager sm;
    private ProgressBar p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        p = findViewById(R.id.progressBar3);
        p.setMax(target_value);
        p.setProgress(0, true);

        load();

        sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> s = sm.getSensorList(Sensor.TYPE_LINEAR_ACCELERATION);
        acc_sensor = s.get(0);
        sm.registerListener(this, acc_sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        int value = Integer.parseInt(Math.round(sensorEvent.values[axis] * 100) + "");
        ((TextView) findViewById(R.id.textView_currentSpeed)).setText(value + " ");

        if (isNegative) {
            if (value <= 0)
                p.setProgress(Math.abs(value), true);
            if (value < target_value)
                levelUp();
        }
        else {
            if (value >= 0)
                p.setProgress(Math.abs(value), true);
            if (value > target_value)
                levelUp();
        }
    }

    private void levelUp() {
        target_value = Math.abs(target_value) + increment;
        level += 1;
        p.setMax(target_value);
        axis = getRandomAxis();
        isNegative = isNegative();
        target_value = -Math.abs(target_value);

        save();
        setVisuals();
    }

    private void setVisuals() {
        ((TextView) findViewById(R.id.textView_value)).setText(level + ".");
        ((TextView) findViewById(R.id.textView_requiredSpeed)).setText(Math.round(target_value * 100) + " ");

        String axisString = "Achse";

        switch (axis) {
            case 0:
                axisString = "X-" + axisString;
                break;
            case 1:
                axisString = "Y-" + axisString;
                break;
            case 2:
                axisString = "Z-" + axisString;
                break;
        }

        ((TextView) findViewById(R.id.textView_axis)).setText(axisString);
    }

    public void reset(View sender) {
        SharedPreferences pref = getSharedPreferences("Game", MODE_PRIVATE);
        SharedPreferences.Editor e = pref.edit();
        e.clear();
        e.commit();

        load();
        setVisuals();
    }

    private int getRandomAxis() {
        Random rd = new Random();
        return rd.nextInt(3);
    }

    private boolean isNegative() {
        Random rd = new Random();
        return rd.nextInt(2) > 0;
    }

    private double speedInKmh(double speedInMs) {
        return Math.abs(Math.round(speedInMs*3.6*100)/100);
    }

    private void save() {
        SharedPreferences pref = getSharedPreferences("Game", MODE_PRIVATE);
        SharedPreferences.Editor e = pref.edit();
        e.putInt("level", level);
        e.putInt("target", target_value);
        e.putInt("axis", axis);
        e.putBoolean("isNegative", isNegative);
        e.commit();
    }

    private void load() {
        SharedPreferences pref = getSharedPreferences("Game", MODE_PRIVATE);
        level = pref.getInt("level", 1);
        target_value = pref.getInt("target", init_target_value);
        axis = pref.getInt("axis", 0);
        isNegative = pref.getBoolean("isNegative", false);

        if (isNegative == true)
                target_value = -Math.abs(target_value);

        p.setMax(target_value);

        setVisuals();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    protected void onPause() {
        super.onPause();
        save();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
