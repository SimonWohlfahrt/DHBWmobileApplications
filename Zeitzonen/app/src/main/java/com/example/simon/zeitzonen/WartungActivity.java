package com.example.simon.zeitzonen;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;
import java.util.TimeZone;

public class WartungActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wartung);
    }

    public void SubTimeshift(View sender) {

        SharedPreferences prefs = getSharedPreferences("Timeshifts", MODE_PRIVATE);
        SharedPreferences.Editor e = prefs.edit();
        String city = sender.getContentDescription().toString();

        e.putInt(city, prefs.getInt(city, 0) - 1);
        e.commit();

        ((TextView)findViewById(R.id.t)).setText(getCurrentTime(city));
    }

    public void AddTimeshift(View sender) {

        SharedPreferences prefs = getSharedPreferences("Timeshifts", MODE_PRIVATE);
        SharedPreferences.Editor e = prefs.edit();
        String city = sender.getContentDescription().toString();

        e.putInt(city, prefs.getInt(city, 0) + 1);
        e.commit();

        ((TextView)findViewById(R.id.t)).setText(getCurrentTime(city));
    }

    public String getCurrentTime(String city) {

        String timezonecode = null;
        int timeshift = 0;
        SharedPreferences prefs = getSharedPreferences("Timeshifts", MODE_PRIVATE);

        switch (city) {
            case "New York":
                timezonecode = "America/New_York";
                timeshift = prefs.getInt("New York", 0);
                break;
            case "Seoul":
                timezonecode = "Asia/Seoul";
                timeshift = prefs.getInt("Seoul", 0);
                break;
            case "Paris":
                timezonecode = "Europe/Paris";
                timeshift = prefs.getInt("Paris", 0);
                break;
            case "Sydney":
                timezonecode = "Australia/Sydney";
                timeshift = prefs.getInt("Sydney", 0);
                break;
            case "Moskau":
                timezonecode = "Europe/Moscow";
                timeshift = prefs.getInt("Moskau", 0);
                break;
        }
        TimeZone timezone = TimeZone.getTimeZone(timezonecode);
        Calendar calendar = Calendar.getInstance(timezone);

        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), (calendar.get(Calendar.HOUR_OF_DAY) + timeshift), calendar.get(Calendar.MINUTE));
        return "" + calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE);
    }
}
