package com.example.papalouis.swapiapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    static final String myUrl = "https://swapi.co/api/";
    ProgressBar progress;
    TextView retour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        retour = findViewById(R.id.retour);
        progress = findViewById(R.id.progress);

        Button btn = findViewById(R.id.button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, ListTheme.class);
                startActivity(myIntent);
            }
        });

        MyAsyncTask task = new MyAsyncTask(progress);
        task.execute(myUrl, retour);
    }
}
