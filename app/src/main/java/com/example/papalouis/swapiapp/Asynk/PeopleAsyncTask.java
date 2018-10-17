package com.example.papalouis.swapiapp.Asynk;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.papalouis.swapiapp.People;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class PeopleAsyncTask extends AsyncTask<Object, Void, ArrayList> {
    private TextView retour;
    private ProgressBar progress;
    private String host;
    public ListView ListResultView;
    public ArrayAdapter adapter;

    public PeopleAsyncTask(ProgressBar prog){
        progress = prog;
    }

    protected void onPreExecute() {
        progress.setVisibility(View.VISIBLE);
    }

    @Override
    protected ArrayList<String> doInBackground(Object... params) {
        BufferedReader input;
        ListResultView = (ListView) params[3];
        adapter = (ArrayAdapter) params[2];
        retour = (TextView) params[1];
        host = (String) params[0];
        JSONObject jsonObject = null;
        JSONArray jArray = null;
        JSONObject people = null;
        String name;
        ArrayList<String> ListResult = new ArrayList<>();
        try {
            URL url = new URL(host);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK){
                Log.d("Connection réussi", "true");
                input = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder builder = new StringBuilder();
                builder.append(input.readLine());

                jsonObject = new JSONObject(builder.toString());
                jArray = jsonObject.getJSONArray("results");

                for (int i = 0; i < jArray.length(); i++){
                    people = jArray.getJSONObject(i);
                    name = people.getString("name");
                    Log.d("name", name);
                    ListResult.add(name);
                }

                input.close();
            }
            urlConnection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ListResult;
    }

    protected void onPostExecute(ArrayList result) {
        super.onPostExecute(result);
        if (result != null){
            retour.setText("SWAPI is ready");
            Log.d("name", result.toString());
            ListResultView.setAdapter(adapter);
        }else{
            Log.d("Asynk", "Chaine vide");
        }
        progress.setVisibility(View.GONE);
    }
}
