package com.example.emre.udemy.Restaurant;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.emre.udemy.R;

import java.util.List;

public class RestaurantAdapter extends ArrayAdapter
    {

    public RestaurantAdapter(Context context,int resource, List<Restaurant> restaurantList){
        super(context,resource,restaurantList);

    }

    @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        //STEP1
        Restaurant restaurant = (Restaurant) getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.restaurant_items, parent,false);
        }

        //STEP2
        TextView textViewName = convertView.findViewById(R.id.restaurant_name);
        TextView textViewAdress = convertView.findViewById(R.id.restaurant_address);
        ImageView imageView = convertView.findViewById(R.id.restaurant_image);

        //STEP3
        textViewName.setText(restaurant.getName());
        textViewAdress.setText(restaurant.getAddress());
        Glide.with(getContext()).load(restaurant.getImageUrl()).into(imageView);


        return convertView;

    }



    }
