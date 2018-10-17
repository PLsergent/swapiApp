package com.example.papalouis.swapiapp.Asynk;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MyAsyncTask extends AsyncTask<Object, Void, JSONObject> {
    private TextView retour;
    private ProgressBar progress;
    private String host;

    public MyAsyncTask(ProgressBar prog){
        progress = prog;
    }

    protected void onPreExecute() {
        progress.setVisibility(View.VISIBLE);
    }

    @Override
    protected JSONObject doInBackground(Object... params) {
        BufferedReader input;
        retour = (TextView) params[1];
        host = (String) params[0];
        JSONObject jsonObject = null;
        try {
            URL url = new URL(host);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK){
                Log.d("Connection réussi", "true");
                input = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder builder = new StringBuilder();
                builder.append(input.readLine());

                jsonObject = new JSONObject(builder.toString());
                input.close();
            }
            urlConnection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    protected void onPostExecute(JSONObject result) {
        super.onPostExecute(result);
        if (result != null){
            retour.setText("SWAPI is ready");
        }else{
            Log.d("Asynk", "Chaine vide");
        }
        progress.setVisibility(View.GONE);
    }
}
