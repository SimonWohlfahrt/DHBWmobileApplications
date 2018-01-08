package com.example.weitlos.twitter;

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
 * Created by D064042 on 21.12.2017.
 */

public class TwitterLoader extends AsyncTask<String, String, JSONArray> {

    public interface AsyncResponse {
        void processFinish(JSONArray output);
    }

    public AsyncResponse delegate = null;

    public TwitterLoader(AsyncResponse delegate){
        this.delegate = delegate;
    }

    @Override
    protected JSONArray doInBackground(String... params) {

        String jsonString = "";
        JSONObject object = null;
        JSONArray list = null;

        try {
            URL url = new URL("http://webtechlecture.appspot.com/timeline?screenname=" + params[0].toString());

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
            String line = "";

            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
                jsonString += line;
            }

            list = new JSONArray(jsonString);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    protected void onPostExecute(JSONArray o) {
        super.onPostExecute(o);
        delegate.processFinish(o);
    }
}
