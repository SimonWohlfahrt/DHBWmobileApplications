package com.example.simon.colorpalette;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Simon on 21.01.2018.
 */

public class Connector extends AsyncTask<String, Integer, JSONArray> {

    public interface AsyncResponse {
        void processFinish(JSONArray output);
    }

    public AsyncResponse delegate = null;

    public Connector(AsyncResponse delegate) {
        this.delegate = delegate;
    }

    @Override
    protected JSONArray doInBackground(String... strings) {

        //http://www.colourlovers.com/api/palettes/random?format=json

        JSONArray a = null;
        try {
            URL url = new URL("http://www.colourlovers.com/api/palettes/random?format=json");
            BufferedReader rd = new BufferedReader(new InputStreamReader(url.openStream()));

            String line, jsonString = "";

            while ((line = rd.readLine()) != null) {
                System.out.println(line);
                jsonString += line;
            }

            a = new JSONArray(jsonString);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return a;
    }

    @Override
    protected void onPostExecute(JSONArray messages) {
        super.onPostExecute(messages);
        delegate.processFinish(messages);
    }
}
