package com.myapplicationdev.android.mymovies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MovieList extends AppCompatActivity {
    ListView lv;
    ArrayList<Movie> moviesList;
    //ArrayAdapter<song> adapter;
    CustomAdapter adapter;
    Button PG13movies;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        lv = findViewById(R.id.list_view);
        PG13movies = findViewById(R.id.btnShowPG13Movies);
        DBHelper db = new DBHelper(MovieList.this);
        moviesList=db.getAllMovies();

        //ArrayAdapter<song> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, songs);


        adapter = new CustomAdapter(this, R.layout.row, moviesList); // Remove the CustomAdapter declaration here
        lv.setAdapter(adapter);


        PG13movies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Movie> BestSongs = db.getPG13Movies();
                adapter.clear();
                adapter.addAll(BestSongs);
                adapter.notifyDataSetChanged();
            }
        });
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int
                    position, long id) {
                Movie ClickedMovie = moviesList.get(position);
                Intent i = new Intent(MovieList.this,
                        EditMovie.class);
                i.putExtra("movie", ClickedMovie);
                startActivity(i);
            }
        });
    }

}
