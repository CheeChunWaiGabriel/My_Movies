package com.myapplicationdev.android.mymovies;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "movies.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_MOVIES = "movies";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_GENRE = "genre";
    private static final String COLUMN_YEAR = "released_year";
    private static final String COLUMN_RATING = "rating";
    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String dropTableSql = "DROP TABLE IF EXISTS " + TABLE_MOVIES;
        db.execSQL(dropTableSql);
        String createTableSql = "CREATE TABLE " + TABLE_MOVIES + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_TITLE + " TEXT NOT NULL, "
                + COLUMN_GENRE + " TEXT NOT NULL, "
                + COLUMN_YEAR + " INTEGER NOT NULL, "
                + COLUMN_RATING + " TEXT NOT NULL"
                + ");";
        db.execSQL(createTableSql);
        Log.i("info" ,"created tables");}



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MOVIES);
        // Create table(s) again
        onCreate(db);


    }
    public void insertMovie(String title, String genre, int year, String rating){

        // Get an instance of the database for writing
        SQLiteDatabase db = this.getWritableDatabase();
        // We use ContentValues object to store the values for
        //  the db operation
        ContentValues values = new ContentValues();
        // Store the column name as key and the description as value
        values.put(COLUMN_TITLE, title);
        values.put(COLUMN_GENRE, genre);
        values.put(COLUMN_YEAR, year);
        values.put(COLUMN_RATING, String.valueOf(rating));

        // Store the column name as key and the date as value

        // Insert the row into the TABLE_TASK
        db.insert(TABLE_MOVIES, null, values);
        // Close the database connection
        db.close();
    }
    public ArrayList<Movie> getAllMovies() {
        ArrayList<Movie> movielist = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        String[] columns= {COLUMN_ID, COLUMN_TITLE, COLUMN_GENRE,COLUMN_YEAR,COLUMN_RATING};

        Cursor cursor = db.query(TABLE_MOVIES, columns, null, null,
                null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String title=cursor.getString(1);
                String genre= cursor.getString(2);
                int year=cursor.getInt(3);
                String rating=cursor.getString(4);
                Movie movie = new Movie(id,title,genre,year,rating);
                movielist.add(movie);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return movielist;
    }
    public ArrayList<Movie> getPG13Movies() {
        ArrayList<Movie> PG13MovieList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        String[] columns= {COLUMN_ID, COLUMN_TITLE, COLUMN_GENRE,COLUMN_YEAR,COLUMN_RATING};
        String condition = COLUMN_RATING + "= ?";
        String[] args = {"PG13"};
        Cursor cursor = db.query(TABLE_MOVIES, columns, condition, args,
                null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String title=cursor.getString(1);
                String genre= cursor.getString(2);
                int year=cursor.getInt(3);
                String rating=cursor.getString(4);
                Movie movie = new Movie(id,title,genre,year,rating);
                PG13MovieList.add(movie);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return PG13MovieList;
    }
    public int updateMovie(Movie movie) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, movie.getTitle());
        values.put(COLUMN_GENRE, movie.getGenre());
        values.put(COLUMN_YEAR, movie.getYear());
        values.put(COLUMN_RATING, String.valueOf(movie.getRating()));
        String whereClause = COLUMN_ID + "=?";
        String[] whereArgs = {String.valueOf(movie.getId())};
        int result=db.update(TABLE_MOVIES, values, whereClause, whereArgs);

        if (result < 1) {
            Log.d("DBHelper", "Update failed");

            db.close();
            return result;
        }


        return result;
    }
    public void deleteMovie(int movieID) {
        SQLiteDatabase db = this.getWritableDatabase();
        String whereClause = COLUMN_ID + "=?";
        String[] whereArgs = {String.valueOf(movieID)};
        db.delete(TABLE_MOVIES, whereClause, whereArgs);
        db.close();
    }}
