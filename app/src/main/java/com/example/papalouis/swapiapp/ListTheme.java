package com.example.papalouis.swapiapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class ListTheme extends AppCompatActivity {
    ListView mListView;
    private String[] themes = new String[]{
            "Planets", "Spaceships",
            "Vehicles", "People",
            "Films", "Species"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_theme);

        mListView = findViewById(R.id.listView);

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(ListTheme.this,
                android.R.layout.simple_list_item_1, themes);
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                String target = themes[position];
                if (target == "Planets"){
                    Intent myIntent = new Intent(ListTheme.this, Planets.class);
                    startActivity(myIntent);
                }else if(target == "Spaceships"){
                    Intent myIntent = new Intent(ListTheme.this, Spaceships.class);
                    startActivity(myIntent);
                }else if(target == "Vehicles"){
                    Intent myIntent = new Intent(ListTheme.this, Vehicles.class);
                    startActivity(myIntent);
                }else if(target == "People"){
                    Intent myIntent = new Intent(ListTheme.this, People.class);
                    startActivity(myIntent);
                }else if(target == "Films"){
                    Intent myIntent = new Intent(ListTheme.this, Films.class);
                    startActivity(myIntent);
                }else if(target == "Species"){
                    Intent myIntent = new Intent(ListTheme.this, Species.class);
                    startActivity(myIntent);
                }
            }
        });
    }
}
