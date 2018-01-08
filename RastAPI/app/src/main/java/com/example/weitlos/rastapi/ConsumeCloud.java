package com.example.weitlos.rastapi;

import android.os.AsyncTask;
import android.provider.Settings;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by D064042 on 19.12.2017.
 */

public class ConsumeCloud extends AsyncTask<String, Integer, String> {

    @Override
    protected String doInBackground(String... urls) {
        String jsonString = "";
        for (String urlString : urls) {

            try {
                URL url = new URL(urlString);
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
                String line = "";

                while ((line = bufferedReader.readLine()) != null) {
                    System.out.println(line);
                    jsonString += line;
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return jsonString;
    }

    @Override
    protected void onPostExecute(String s) {
        System.out.println("onPostExecute enthält: " + s);
    }
}
