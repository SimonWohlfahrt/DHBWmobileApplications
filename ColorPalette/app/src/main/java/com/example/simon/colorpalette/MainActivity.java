package com.example.simon.colorpalette;

import android.app.Activity;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void colorize(View sender) {
        Connector conn = new Connector(new Connector.AsyncResponse() {
            @Override
            public void processFinish(JSONArray output) {
                try {
                    JSONObject o = output.getJSONObject(0);
                    setTitle(o.getString("title"));

                    JSONArray c = o.getJSONArray("colors");

                    ColorStateList primary = ColorStateList.valueOf(Color.parseColor("#" + c.get(0).toString()));
                    ColorStateList accent = ColorStateList.valueOf(Color.parseColor("#" + c.get(1).toString()));
                    ColorStateList three = ColorStateList.valueOf(Color.parseColor("#" + c.get(2).toString()));
                    ColorStateList four = ColorStateList.valueOf(Color.parseColor("#" + c.get(3).toString()));
                    ColorStateList five = ColorStateList.valueOf(Color.parseColor("#" + c.get(4).toString()));

                    Button b = findViewById(R.id.button);
                    b.setBackgroundTintList(primary);
                    b.setTextColor(three);

                    Switch s = findViewById(R.id.switch1);
                    s.setThumbTintList(three);
                    s.setTextColor(four);

                    TextView v = findViewById(R.id.textView);
                    v.setTextColor(four);

                    SeekBar k = findViewById(R.id.seekBar);
                    k.setProgressBackgroundTintList(primary);
                    k.setProgressTintList(primary);
                    k.setThumbTintList(primary);

                    RadioButton r1 = findViewById(R.id.radioButton);
                    r1.setButtonTintList(primary);

                    RadioButton r2 = findViewById(R.id.radioButton2);
                    r2.setButtonTintList(primary);

                    RadioButton r3 = findViewById(R.id.radioButton3);
                    r3.setButtonTintList(primary);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        conn.execute();
    }
}
