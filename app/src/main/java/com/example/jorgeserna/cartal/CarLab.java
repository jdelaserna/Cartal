package com.example.jorgeserna.cartal;

import android.content.Context;
import android.util.Log;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jorgeserna on 2018-03-07.
 */

public class CarLab {

    private static CarLab sCarLab;
    private List<ParseObject> mCarList;
    private Context mContext;

    private CarLab(Context context) {
        mContext = context.getApplicationContext();
        mCarList = new ArrayList<>();
    }

    public static CarLab get(Context context) {
        if (sCarLab == null) {
            sCarLab = new CarLab(context);
        }
        return sCarLab;
    }

    public void loadCars(String mWhereString) {
//        final CarListFragment fragment = frag;

        ParseQuery<Car> query = ParseQuery.getQuery("cars");
        query.whereContains("city", mWhereString);
        query.setCachePolicy(ParseQuery.CachePolicy.NETWORK_ELSE_CACHE);
        query.findInBackground(new FindCallback<Car>() {
            public void done(List<Car> cars, ParseException e) {
                if (e == null) {
                    mCarList.clear();
                    mCarList.addAll(cars);
//                    fragment.updateDate();
                    Log.d("Cars", "Retrieved " + cars.size());
                } else {
                    Log.d("Cars", "Error: " + e.getMessage());
                }

            }
        });
    }

    public ParseObject getCar(int pos) {
        return mCarList.get(pos);
    }

    public List<ParseObject> getCarList() {
        return mCarList;
    }

}
