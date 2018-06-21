package com.example.simon.chatlist;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Simon on 22.01.2018.
 */

public class ChatConnector extends AsyncTask<String, String, JSONArray> {

    interface AsyncResponse {
        void processFinish(JSONArray output);
    }

    private AsyncResponse delegate = null;

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
    protected void onPostExecute(JSONArray s) {
        super.onPostExecute(s);
        delegate.processFinish(s);
    }

    public boolean getRooms() {
        try {
            this.execute("https://roomsquare-prototype.appspot.com/room/list");
        } catch(Exception e) {
            return false;
        }
        return true;

    }
}
