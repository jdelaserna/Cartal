package com.example.jorgeserna.cartal;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;

import com.parse.Parse;
import com.parse.ParseInstallation;
import com.parse.ParseObject;

import java.util.Calendar;

public class MainActivity extends Activity {

    private static final String TAG = "MainActivity";
    public static final String mFirstField = "FirstField";
    public static final String mSecondField = "SecondField";
    public static final String mThirdField = "ThirdField";
    private AutoCompleteTextView mWhere;
    private Button mDeparture;
    private Button mReturn;
    private Button mSubmitButton;

    private DatePickerDialog.OnDateSetListener mDepartureDateSetListener;
    private DatePickerDialog.OnDateSetListener mReturnDateSetListener;

    private static final String cities[] = {"Amsterdam", "Las Vegas", "Madrid", "Miami", "New York", "San Francisco", "Toronto", "Vancouver"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ParseObject.registerSubclass(Car.class);
        Parse.initialize(this);
        ParseInstallation.getCurrentInstallation().saveInBackground();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, cities);
        mWhere = (AutoCompleteTextView) findViewById(R.id.where);
        mWhere.setAdapter(adapter);

        mDeparture = (Button) findViewById(R.id.departureDate);
        mReturn = (Button) findViewById(R.id.returnDate);

        mDeparture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        MainActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDepartureDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });


        mReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        MainActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mReturnDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDepartureDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                Log.d(TAG, "onDateSet: date: " + month + "/" + dayOfMonth + "/" + year);

                String date = month + "/" + dayOfMonth + "/" + year;
                mDeparture.setText(date);
            }
        };

        mReturnDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                Log.d(TAG, "onDateSet: date: " + month + "/" + dayOfMonth + "/" + year);

                String date = month + "/" + dayOfMonth + "/" + year;
                mReturn.setText(date);
            }
        };

        mSubmitButton = (Button) findViewById(R.id.submit);
        mSubmitButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CarActivity.class);

                String mDepartureString = mDeparture.getText().toString();
                String mReturnString = mReturn.getText().toString();
                String mWhereString = mWhere.getText().toString();

                intent.putExtra(mFirstField, mDepartureString);
                intent.putExtra(mSecondField, mReturnString);
                intent.putExtra(mThirdField, mWhereString);

                startActivity(intent);
            }
        });

    }

}
