package com.example.papalouis.swapiapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.papalouis.swapiapp.Asynk.PeopleAsyncTask;

import java.util.ArrayList;

import static com.example.papalouis.swapiapp.MainActivity.myUrl;

public class People extends AppCompatActivity {
    private String host = myUrl + "people/?page=1";
    private ArrayList<String> ListResult = new ArrayList<String>();
    private ListView ListResultView;
    private ProgressBar progress;
    private TextView retour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people);

        retour = findViewById(R.id.retour);
        progress = findViewById(R.id.progress);
        ListResultView = findViewById(R.id.peopleView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(People.this,
                android.R.layout.simple_list_item_1, ListResult);

        PeopleAsyncTask task = new PeopleAsyncTask(progress);
        task.execute(host, retour, adapter, ListResultView);
    }

}
