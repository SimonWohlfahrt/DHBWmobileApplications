package com.example.weitlos.twitter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void loadTwitter(View sender) {
        String username = ((EditText)findViewById(R.id.editText)).getText().toString();
        TwitterLoader t = new TwitterLoader(new TwitterLoader.AsyncResponse() {

            @Override
            public void processFinish(JSONArray output) {
                    showTweets(output);
            }
        });

        t.execute(username);
    }

    public void showTweets(JSONArray o) {
        Intent i = new Intent(this, TweetsActivity.class);
        String[] tweets = new String[o.length()];
        for (int j = 0; j < o.length(); j++ )
            try {
                tweets[j] = o.optJSONObject(j).getString("text");
            } catch (JSONException e) {
                e.printStackTrace();
            }

        i.putExtra("tweets", tweets);
        startActivity(i);
    }
}
