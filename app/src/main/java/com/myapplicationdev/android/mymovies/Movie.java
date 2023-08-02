package com.myapplicationdev.android.mymovies;

import java.io.Serializable;

public class Movie implements Serializable {
    String title;
    String Genre;
    int Year;
  String rating;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    int id;
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return Genre;
    }

    public void setGenre(String genre) {
        Genre = genre;
    }

    public int getYear() {
        return Year;
    }

    public void setYear(int year) {
        Year = year;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public Movie(String title, String genre, int year, String rating) {
        this.title = title;
        Genre = genre;
        Year = year;
        this.rating = rating;
    }
    public Movie(int id,String title, String genre, int year, String rating) {
        this.id=id;
        this.title = title;
        Genre = genre;
        Year = year;
        this.rating = rating;
    }



    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", Genre='" + Genre + '\'' +
                ", Year=" + Year +
                ", rating=" + rating +
                '}';
    }


}
