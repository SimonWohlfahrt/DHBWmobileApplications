package com.example.weitlos.chat;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by D064042 on 22.12.2017.
 */

public class ChatConnector extends AsyncTask<String, Integer, JSONArray> {

    public AsyncResponse delegate = null;

    public interface AsyncResponse {
        void processFinish(JSONArray output);
    }

    public ChatConnector(AsyncResponse delegate) {
        this.delegate = delegate;
    }

    @Override
    protected JSONArray doInBackground(String... strings) {
        JSONArray a = null;
        try {
            URL url = new URL(strings[0]);
            BufferedReader rd = new BufferedReader(new InputStreamReader(url.openStream()));

            String line = "", jsonString = "";

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
