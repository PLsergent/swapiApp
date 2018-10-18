package com.example.papalouis.swapiapp.Asynk;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;


import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class NameAsyncTask extends AsyncTask<Object, Void, ArrayList> {
    private TextView retour;
    private ProgressBar progress;
    private String host;
    public ArrayList ListResult;
    public ArrayAdapter adapter;

    public NameAsyncTask(ProgressBar prog){
        progress = prog;
    }

    protected void onPreExecute() {
        progress.setVisibility(View.VISIBLE);
    }

    @Override
    protected ArrayList<String> doInBackground(Object... params) {
        BufferedReader input;
        ListResult = (ArrayList) params[3];
        adapter = (ArrayAdapter) params[2];
        retour = (TextView) params[1];
        host = (String) params[0] + "1";
        JSONObject jsonObject = null;
        JSONArray jArray = null;
        JSONObject item = null;
        String name;
        String count = null;
        String field = "name";
        int j;
        try {
            URL url = new URL(host);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                Log.d("Connection réussi", "true");
                input = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder builder = new StringBuilder();
                builder.append(input.readLine());

                jsonObject = new JSONObject(builder.toString());
                jArray = jsonObject.getJSONArray("results");
                item = jArray.getJSONObject(0);
                try {item.getString("name");}catch(Exception e){field = "title";}finally {
                input.close();
                urlConnection.disconnect();
            }
            j = 1;
            count = "whatever";
            while (count != "null") {
                String js = "" + j;
                host = (String) params[0] + js;
                url = new URL(host);
                urlConnection = (HttpURLConnection) url.openConnection();
                if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    Log.d("Connection réussi", "true");
                    input = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    builder = new StringBuilder();
                    builder.append(input.readLine());

                    jsonObject = new JSONObject(builder.toString());
                    jArray = jsonObject.getJSONArray("results");
                    for (int i = 0; i < jArray.length(); i++) {
                        item = jArray.getJSONObject(i);
                        name = item.getString(field);
                        Log.d("name", name);
                        ListResult.add(name);
                    }
                    count = jsonObject.getString("next");
                    input.close();
                    urlConnection.disconnect();
                }
                j++;
            }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return ListResult;
    }

    protected void onPostExecute(ArrayList result) {
        super.onPostExecute(result);
        if (result != null){
            retour.setText("SWAPI is ready");
            Log.d("name", result.toString());
            adapter.notifyDataSetChanged();
        }else{
            Log.d("Asynk", "Chaine vide");
        }
        progress.setVisibility(View.GONE);
    }
}
