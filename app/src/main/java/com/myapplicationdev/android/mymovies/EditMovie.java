package com.myapplicationdev.android.mymovies;

import android.content.Intent;
import android.database.AbstractCursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class EditMovie extends AppCompatActivity {
    Button btnUpdate,btnCancel,btnDelete;
    EditText YearInput;
    EditText MovieInput;
    EditText GenreInput;
    Spinner RatingInput;
    ArrayList spinneroptions = new ArrayList();

    Movie selectedMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        DBHelper db = new DBHelper(EditMovie.this);
        Intent intent = getIntent();
        Movie clickedMovie = (Movie) intent.getSerializableExtra("movie");

        btnUpdate=findViewById(R.id.btnupdate);
        btnCancel=findViewById(R.id.btnCancel);
        btnDelete=findViewById(R.id.btndelete);
        YearInput=findViewById(R.id.insertYear);
        MovieInput=findViewById(R.id.insertTitle);
        GenreInput=findViewById(R.id.insertGenre);
        RatingInput=findViewById(R.id.spinner2);
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

        selectedMovie = clickedMovie;
        YearInput.setText(String.valueOf(clickedMovie.getYear()));
        MovieInput.setText(clickedMovie.getTitle());
        GenreInput.setText(clickedMovie.getGenre());
        String rating = clickedMovie.getRating();
        int positionOfRating = adapter.getPosition(String.valueOf(rating));
        RatingInput.setSelection(positionOfRating);


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickedMovie.setTitle(MovieInput.getText().toString());
                clickedMovie.setGenre(GenreInput.getText().toString());
                clickedMovie.setYear(Integer.parseInt(YearInput.getText().toString()));
                clickedMovie.setRating(RatingInput.getSelectedItem().toString());
                db.updateMovie(clickedMovie);
                db.close();
                finish();
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.deleteMovie(clickedMovie.getId());
                db.close();
                finish();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditMovie.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }



}
