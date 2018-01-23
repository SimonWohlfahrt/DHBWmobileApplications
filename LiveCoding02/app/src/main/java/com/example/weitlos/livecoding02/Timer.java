package com.example.weitlos.livecoding02;

import android.os.AsyncTask;
import android.os.Handler;

import org.json.JSONArray;

/**
 * Created by D064042 on 09.01.2018.
 */

public class Timer extends AsyncTask<Float, Float, Float> implements Runnable  {

    @Override
    public void run() {

    }

    public interface AsyncResponse {
        void processFinish(Float time);

        void progressUpdate(Float time);
    }

    public AsyncResponse delegate = null;

    public Timer(AsyncResponse delegate) {
        this.delegate = delegate;
    }

    @Override
    protected Float doInBackground(Float... floats) {
        float remainingTime = floats[0];
        //while (remainingTime > 0) {
            Handler t = new Handler();
            t.postDelayed(this, (long)(remainingTime * 10) * 100);
            remainingTime -= 0.1;
            onProgressUpdate(remainingTime);
        //}
        return 0f;
    }

    @Override
    protected void onProgressUpdate(Float... values) {
        super.onProgressUpdate(values);
        delegate.progressUpdate(values[0]);
    }

    @Override
    protected void onPostExecute(Float v) {
        super.onPostExecute(v);
        delegate.processFinish(v);
    }
}
