package com.example.papalouis.swapiapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.papalouis.swapiapp.Asynk.NameAsyncTask;

import java.util.ArrayList;

import static com.example.papalouis.swapiapp.MainActivity.myUrl;

public class Films extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String host = myUrl + "films/?page=";
        ArrayList<String> ListResult = new ArrayList<String>();
        ListView ListResultView;
        ProgressBar progress;
        TextView retour;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_films);

        retour = findViewById(R.id.retour);
        progress = findViewById(R.id.progress);
        ListResultView = findViewById(R.id.peopleView);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Films.this,
                android.R.layout.simple_list_item_1, android.R.id.text1, ListResult);
        ListResultView.setAdapter(adapter);

        NameAsyncTask task = new NameAsyncTask(progress);
        task.execute(host, retour, adapter, ListResult);
    }

}
