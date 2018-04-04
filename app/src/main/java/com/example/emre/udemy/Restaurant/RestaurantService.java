package com.example.emre.udemy.Restaurant;


import android.content.Context;
import android.os.AsyncTask;

import com.example.emre.udemy.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class RestaurantService extends AsyncTask<String,Integer, List<Restaurant>>{


    private RestaurantServiceInterface activity;
    public RestaurantService(RestaurantServiceInterface activity){

        super();
        this.activity=  activity;
    }


    @Override
    protected void onPostExecute(List<Restaurant> restaurants) {
        super.onPostExecute(restaurants);
    this.activity.updateUI(restaurants);
            // We need to publish this list to UI




    }

    @Override
    protected List<Restaurant> doInBackground(String... urls) {

        URL url = null;
        List<Restaurant> restaurantList = new ArrayList<Restaurant>();


        try {
            url = new URL(urls[0]);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.connect();


            InputStream inputStream = connection.getInputStream();
            // Convert the inputStream into String.
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line + "\n");
            }

            String json = stringBuilder.toString();

            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonRestaurants = jsonObject.getJSONArray("restaurants");



            for (int i=0; i < jsonRestaurants.length(); i++) {
                JSONObject jsonRestaurant = jsonRestaurants.getJSONObject(i);

                Restaurant restaurant = new Restaurant();
                restaurant.setName(jsonRestaurant.getString("name"));
                restaurant.setAddress(jsonRestaurant.getString("address"));
             //   restaurant.setArea(jsonRestaurant.getString("area"));
             //   restaurant.setId(jsonRestaurant.getString("id"));
                restaurant.setImageUrl(jsonRestaurant.getString("image_url"));


        restaurantList.add(restaurant);

            }
return restaurantList;


        } catch (MalformedURLException e) {

                    e.printStackTrace();
        }
            catch (IOException e) {
                e.printStackTrace();

            } catch (JSONException e) {
            e.printStackTrace();
        }

        return  restaurantList;
    }
}
