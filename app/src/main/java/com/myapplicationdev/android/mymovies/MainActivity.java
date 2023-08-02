package com.myapplicationdev.android.mymovies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.Spliterator;

public class MainActivity extends AppCompatActivity {

    Button btnInsert, btnGetSongs;
    TextView tvResults;

    EditText YearInput;
    EditText MovieInput;
    EditText GenreInput;
    Spinner RatingInput;
    ArrayList spinneroptions = new ArrayList();
    ArrayAdapter<Spinner> adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInsert = findViewById(R.id.btnInsert);
        btnGetSongs = findViewById(R.id.btnGetTasks);
        //List View->
        YearInput=findViewById(R.id.insertYear);
        MovieInput=findViewById(R.id.insertTitle);
        GenreInput=findViewById(R.id.insertGenre);
        RatingInput=findViewById(R.id.spinner);
        spinneroptions.add("G");
        spinneroptions.add("PG");
        spinneroptions.add("PG13");
        spinneroptions.add("M18");
        spinneroptions.add("R21");

        // Link the field to the ListView
        // Create an ArrayAdapter
        ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_dropdown_item, spinneroptions);
        // Link the ArrayAdapter to the ListView
        RatingInput.setAdapter(adapter);

        RatingInput.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        // handle selection for item 0
                        break;
                    case 1:
                        // handle selection for item 1
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // do not delete this method even if empty
            }
        });



        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper db = new DBHelper(MainActivity.this);
                String title=MovieInput.getText().toString();
                String genre= GenreInput.getText().toString();
                int year=Integer.parseInt(YearInput.getText().toString());
                String rating = RatingInput.getSelectedItem().toString();


                db.insertMovie(title,genre,year,rating);

                MovieInput.setText("");
                GenreInput.setText("");
                YearInput.setText("");

                Toast.makeText(MainActivity.this, "Movie '" + title + "' added to the database.", Toast.LENGTH_SHORT).show();
            }
        });

        btnGetSongs.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // Create the DBHelper object, passing in the
                // activity's Context

                // Insert a task
                Intent intent = new Intent(MainActivity.this, MovieList.class);
                //Label the actual editactivity EditActivity2
                startActivity(intent);


            }
        });
    }



}




