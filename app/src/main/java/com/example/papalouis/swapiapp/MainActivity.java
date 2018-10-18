package com.example.papalouis.swapiapp;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.papalouis.swapiapp.Asynk.MyAsyncTask;

import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity {
    static final String myUrl = "https://swapi.co/api/";
    ProgressBar progress;
    TextView retour;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img = findViewById(R.id.imageView2);

        ConnectivityManager cm =
                (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        if (isConnected){
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(getApplicationContext(), "Connecté à internet", duration);
            toast.show();
        }else{
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(getApplicationContext(), "Pas de connexion", duration);
            toast.show();
        }

        retour = findViewById(R.id.retour);
        progress = findViewById(R.id.progress);

        MyAsyncTask task = new MyAsyncTask(progress);
        task.execute(myUrl, retour);

        Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            public void run() {
                Intent myIntent = new Intent(MainActivity.this, ListTheme.class);
                startActivity(myIntent);            }
        }, 1000);
    }
}
