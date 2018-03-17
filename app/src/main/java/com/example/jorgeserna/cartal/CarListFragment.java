package com.example.jorgeserna.cartal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parse.ParseObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jorgeserna on 2018-03-07.
 */

public class CarListFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{

    public static final String ARG_CITY_NAME = "city";
    private RecyclerView mCarRecyclerView;
    private CarAdapter mAdapter;
    private List<ParseObject> mCarList;
    private String carId;

    public static CarListFragment newInstance(String cityName) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_CITY_NAME, cityName);

        CarListFragment fragment = new CarListFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        loadCars();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_car, container, false);
        mCarRecyclerView = view.findViewById(R.id.car_recycler_view);
        mCarRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    public void loadCars() {
        mCarList = new ArrayList<>();

        Bundle args = getArguments();
        String city = (String) args.getSerializable(ARG_CITY_NAME);

        CarLab carLab = CarLab.get(getActivity());
        carLab.loadCars(city);
        mCarList = CarLab.get(getActivity()).getCarList();

        //updateDate();
    }


    public void updateDate() {
        mAdapter.notifyDataSetChanged();
    }

    public void updateUI() {
        if (mAdapter == null) {
            mAdapter = new CarAdapter(mCarList);
            mCarRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.setCars(mCarList);
            mAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public void onRefresh() {

    }

    private class CarAdapter extends RecyclerView.Adapter<CarHolder> {

        private List<ParseObject> mCars;

        public CarAdapter(List<ParseObject> cars) {
            mCars = cars;
        }

        public void setCars(List<ParseObject> cars) {
            mCars = cars;
        }

        @Override
        public CarHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

            return new CarHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(CarHolder holder, int position) {
            ParseObject car = mCars.get(position);
            holder.bind(car);
        }

        @Override
        public int getItemCount() {
            return mCars.size();
        }
    }

    /**
     * Holder
     */
    private class CarHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private Car mCar;
        private TextView mBrandTextView;
        private TextView mModelTextView;
        private TextView mPriceTextView;

        public CarHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_car, parent, false));
            itemView.setOnClickListener(this);

            mBrandTextView = itemView.findViewById(R.id.car_brand);
            mModelTextView = itemView.findViewById(R.id.car_model);
            mPriceTextView = itemView.findViewById(R.id.car_price);
        }

        public void bind(ParseObject car) {
            mBrandTextView.setText(car.getString("brand"));
            mModelTextView.setText(car.getString("model"));
            mPriceTextView.setText("CAD$ " + String.valueOf(car.getInt("price")) + "/day");
        }

        @Override
        public void onClick(View v) {
            int pos = getAdapterPosition();
            if (pos != RecyclerView.NO_POSITION){

                ParseObject clickedDataItem = mCarList.get(pos);
                carId = clickedDataItem.getObjectId();

                Intent intent = new Intent(getContext(), CarFragment.class);
                intent.putExtra("pos", pos);
                startActivity(intent);
            }
        }
    }

}
