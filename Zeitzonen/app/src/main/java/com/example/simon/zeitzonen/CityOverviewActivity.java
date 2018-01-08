package com.example.simon.zeitzonen;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.transition.Explode;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.TimeZone;

public class CityOverviewActivity extends Activity {

    private HashMap<String, Date> times = new HashMap<String, Date>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_overview);

        Explode x = new Explode();
        getWindow().setExitTransition(x);
    }

    public void showCurrentTime(View sender) {

        String timezonecode = null;
        int timeshift = 0;
        SharedPreferences prefs = getSharedPreferences("Timeshifts", MODE_PRIVATE);

        switch (((Button)sender).getText().toString()) {
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
        ((TextView)findViewById(R.id.t1)).setText(calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE));
    }

    private Date getTimeByCityName(String cityname) {
        return times.get(cityname);
    }

    public void GoToWartung(View sender) {
        Intent i = new Intent(this, WartungActivity.class);
        startActivity(i);
    }
}
