package com.example.weitlos.mobilesharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SensorManager sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> sensorList = sm.getSensorList(Sensor.TYPE_ALL);

        String sensorInfo = "";
        for (Sensor s : sensorList) {
            sensorInfo += s.getName() + "\n";
        }

        sensorInfo += "\n" + sensorList.size() + " Sensoren gefunden!";

        ((TextView) findViewById(R.id.textView_SensorInfo)).setText(sensorInfo);
    }

    @Override
    protected void onStop() {
        super.onStop();

        SharedPreferences prefs = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor e = prefs.edit();
        e.putString("s1", ((EditText)findViewById(R.id.editText)).getText().toString());
        e.putString("s2", ((EditText)findViewById(R.id.editText2)).getText().toString());
        e.putString("s3", ((EditText)findViewById(R.id.editText3)).getText().toString());
        e.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences prefs = getPreferences(MODE_PRIVATE);
        ((EditText)findViewById(R.id.editText)).setText(prefs.getString("s1", "..."));
        ((EditText)findViewById(R.id.editText2)).setText(prefs.getString("s2", "..."));
        ((EditText)findViewById(R.id.editText3)).setText(prefs.getString("s3", "..."));
    }
}
