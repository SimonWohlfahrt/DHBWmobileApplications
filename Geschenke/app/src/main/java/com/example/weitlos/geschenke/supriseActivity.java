package com.example.weitlos.geschenke;

import android.app.Activity;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.opengl.Visibility;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class supriseActivity extends Activity implements SensorEventListener {

    private SensorManager sm;
    private ConstraintLayout l;
    private int environment = 1000;
    int time = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suprise);

        l = (ConstraintLayout)findViewById(R.id.layout);
        l.setBackgroundColor(Color.parseColor("#CC0022"));

        SensorManager sm = (SensorManager)getSystemService(SENSOR_SERVICE);
        List<Sensor> lightSensors = sm.getSensorList(Sensor.TYPE_LIGHT);
        Sensor s = lightSensors.get(0);

        sm.registerListener(this, s, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        if (sensorEvent.values[0] > environment) {
            l.setBackgroundColor(Color.parseColor("#00CC22"));
            findViewById(R.id.textView_target).setVisibility(View.INVISIBLE);
            findViewById(R.id.textView_current).setVisibility(View.INVISIBLE);
        }
        else
            environment = Math.round((environment + sensorEvent.values[0]) / 2) + 50;

        ((TextView) findViewById(R.id.textView_target)).setText("Schwelle: " + environment);
        ((TextView) findViewById(R.id.textView_current)).setText("Aktuell: " + sensorEvent.values[0]);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
