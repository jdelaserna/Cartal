package com.example.jorgeserna.cartal;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.parse.ParseObject;

import java.util.List;

public class CarActivity extends FragmentActivity {

//    private List<ParseObject> carsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.car_list);

        final String mWhereString = getIntent().getStringExtra(MainActivity.mThirdField);
//        CarLab.get(this).loadCars(mWhereString);
//        carsList = CarLab.get(this).getCarList();

//        CarLab.get(this).loadCars(mWhereString);
//        final List<ParseObject> listOfCars = CarLab.get(this).getCarList();

        FragmentManager fragmentManager = getSupportFragmentManager();
        CarListFragment carListFragment = CarListFragment.newInstance(mWhereString);
        fragmentManager.beginTransaction().replace(R.id.car_list_activity, carListFragment).commit();

    }
}
