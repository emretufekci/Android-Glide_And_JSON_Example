package com.example.emre.udemy;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by emre on 27.03.2018.
 */

public class RestaurantView extends Activity {



    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        TextView nameCafe = null;
        TextView nameAddress = null;



        super.onCreate(savedInstanceState);
        setContentView(R.layout.restaurant_view);

        nameCafe = (TextView)findViewById(R.id.cafename);
        nameAddress = (TextView)findViewById(R.id.rest);


        // Receiving value into activity using intent.
        String TempHolderName = getIntent().getStringExtra("RestaurantName");
        String TempHolderAddress = getIntent().getStringExtra("RestaurantAddress");



        // Setting up received value into EditText.
        nameCafe.setText(TempHolderName);
        nameAddress.setText(TempHolderAddress);

        }
    }

