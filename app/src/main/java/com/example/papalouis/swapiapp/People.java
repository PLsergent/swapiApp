package com.example.papalouis.swapiapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.papalouis.swapiapp.Asynk.NameAsyncTask;
import com.example.papalouis.swapiapp.Asynk.PeopleAsyncTask;

import java.util.ArrayList;

import static com.example.papalouis.swapiapp.MainActivity.myUrl;

public class People extends AppCompatActivity {
        private String name;
        private String height;
        private String mass;
        private String gender;
        private String birth_year;

        public People(){};

        public People(String name, String height, String mass, String gender, String birth_year){
            this.name = name;
            this.height = height;
            this.mass = mass;
            this.gender = gender;
            this.birth_year = birth_year;
        }

        public String getName(){
            return this.name;
        }

        public String getInfo(){
            return this.height + " " +
                   this.mass  + " " +
                   this.gender  + " " +
                   this.birth_year;
        }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String host = myUrl + "people/?page=";
        ArrayList<String> ListResult = new ArrayList<String>();
        ListView ListResultView;
        ProgressBar progress;
        TextView retour;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people);

        retour = findViewById(R.id.retour);
        progress = findViewById(R.id.progress);
        ListResultView = findViewById(R.id.peopleView);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(People.this,
                android.R.layout.simple_list_item_1, android.R.id.text1, ListResult);
        ListResultView.setAdapter(adapter);

        NameAsyncTask task = new NameAsyncTask(progress);
        task.execute(host, retour, adapter, ListResult);
    }

}
