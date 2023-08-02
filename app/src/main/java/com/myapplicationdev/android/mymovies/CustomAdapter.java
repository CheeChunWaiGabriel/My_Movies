package com.myapplicationdev.android.mymovies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {
    private Context parent_context;
    private int layout_id;
    private ArrayList<Movie> MovieList2;

    public CustomAdapter(Context context, int resource, ArrayList<Movie> objects) {
        super(context, resource, objects);
        parent_context = context;
        layout_id = resource;
        MovieList2 = objects;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater =(LayoutInflater) parent_context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView=inflater.inflate(layout_id, parent, false);

        TextView tvName=rowView.findViewById(R.id.textViewTitle);
        TextView tvGenre=rowView.findViewById(R.id.textViewGenre);
        TextView tvYear=rowView.findViewById(R.id.textViewYear);
        ImageView ivRating=rowView.findViewById(R.id.imageViewRating);
        Movie currentItem=MovieList2.get(position);
        tvName.setText(currentItem.getTitle());
        tvGenre.setText("+"+ currentItem.getGenre());
        tvYear.setText(String.valueOf(currentItem.getYear()));
        if (currentItem.getRating()=="G"){
            ivRating.setImageResource(R.drawable.rating_g);
        }
        else if(currentItem.getRating()=="PG"){
            ivRating.setImageResource(R.drawable.rating_pg);
        }
        else if(currentItem.getRating()=="PG13"){
            ivRating.setImageResource(R.drawable.rating_pg13);
        }
        else if(currentItem.getRating()=="NC16"){
            ivRating.setImageResource(R.drawable.rating_nc16);
        }
        else if(currentItem.getRating()=="M18"){
            ivRating.setImageResource(R.drawable.rating_m18);
        } else if (currentItem.getRating()=="R21") {
            ivRating.setImageResource(R.drawable.rating_r21);
        }
        return rowView;


    }}
