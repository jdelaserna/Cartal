package com.example.jorgeserna.cartal;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseObject;

/**
 * Created by jorgeserna on 2018-03-15.
 */

public class CarFragment extends Activity {

    private TextView mBrand;
    private TextView mYear;
    private TextView mColor;
    private TextView mKm;
    private TextView mPrice;
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.car_fragment);

        mBrand = (TextView) findViewById(R.id.car_brand);
        mYear = (TextView) findViewById(R.id.car_year);
        mColor = (TextView) findViewById(R.id.car_color);
        mKm = (TextView) findViewById(R.id.car_km);
        mPrice = (TextView) findViewById(R.id.car_price);
        mButton = (Button) findViewById(R.id.submit_button);

        int pos = getIntent().getIntExtra("pos", 0);
        ParseObject c = CarLab.get(getBaseContext()).getCar(pos);

        mBrand.setText("Brand:      " + c.getString("brand") + " " + c.getString("model"));
        mYear.setText("Year:        " + String.valueOf(c.getInt("year")));
        mColor.setText("Color:      " + c.getString("color"));
        mKm.setText("Km:        " + String.valueOf(c.getInt("km")));
        mPrice.setText("Price:      CAD$ " + String.valueOf(c.getInt("price")) + "/day");

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "The reservation was successful. Thank you for booking with us", Toast.LENGTH_LONG).show();
            }
        });
    }
}
