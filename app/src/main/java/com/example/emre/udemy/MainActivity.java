package com.example.emre.udemy;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.emre.udemy.R;
import com.example.emre.udemy.Restaurant.Restaurant;
import com.example.emre.udemy.Restaurant.RestaurantAdapter;
import com.example.emre.udemy.Restaurant.RestaurantService;
import com.example.emre.udemy.Restaurant.RestaurantServiceInterface;

import org.w3c.dom.Text;

import java.util.List;

public class MainActivity extends AppCompatActivity implements RestaurantServiceInterface {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        RestaurantService service = new RestaurantService(MainActivity.this);
        service.execute("http://opentable.herokuapp.com/api/restaurants?city=Chicago");


    }



    public void updateUI(final List<Restaurant> restaurants) {
        if (!restaurants.isEmpty()){



            final RestaurantAdapter adapter = new RestaurantAdapter(MainActivity.this,R.layout.restaurant_items,restaurants);
            final ListView listView = (ListView) findViewById(R.id.restaurant_listview);
            listView.setAdapter(adapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                String restaurantName = listView.getItemAtPosition(i).toString();
                String restaurantAddress = listView.getItemAtPosition(i).toString();


                    TextView textView = (TextView) view.findViewById(R.id.restaurant_name);
                    TextView textView1 = (TextView) view.findViewById(R.id.restaurant_address);


                    String resName= textView.getText().toString();
                    String resAddress= textView1.getText().toString();





                    Intent intent = new Intent(MainActivity.this, RestaurantView.class);

                    // Sending value to another activity using intent.
                    intent.putExtra("RestaurantName", resName);
                    intent.putExtra("RestaurantAddress", resAddress);
                    startActivity(intent);






                }

            });

        }
}
}
