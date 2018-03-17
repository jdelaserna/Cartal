package com.example.jorgeserna.cartal;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by jorgeserna on 2018-02-27.
 */

@ParseClassName("Car")
public class Car extends ParseObject {

    private String BRAND = "brand";
    private String MODEL = "model";
    private String YEAR = "year";
    private String COLOR = "color";
    private String KM = "year";
    private String PRICE = "price";

    public Car() {

    }

    public Car(String brand, String model, String year, String color, String km, String price) {
        this.BRAND = brand;
        this.MODEL = model;
        this.YEAR = year;
        this.COLOR = color;
        this.KM = km;
        this.PRICE = price;
    }

    public String getBrand() {
        String brand = getString(BRAND);
        if (brand != null) {
            return brand;
        } else {
            return "";
        }
    }

    public void setBrand(String brand) {
        put(BRAND, brand);
    }

    public String getModel() {
        String model = getString(MODEL);
        if (model != null) {
            return model;
        } else {
            return "";
        }
    }

    public void setModel(String model) {
        put(MODEL, model);
    }

    public String getYear() {
        String year = getString(YEAR);
        if (year != null) {
            return year;
        } else {
            return "";
        }
    }

    public void setYear(String year) {
        put(YEAR, year);
    }

    public String getColor() {
        String color = getString(COLOR);
        if (color != null) {
            return color;
        } else {
            return "";
        }
    }

    public void setColor(String color) {
        put(COLOR, color);
    }

    public String getKm() {
        String km = getString(KM);
        if (km != null) {
            return km;
        } else {
            return "";
        }
    }

    public void setKm(String km) {
        put(KM, km);
    }

    public String getPrice() {
        String price = getString(PRICE);
        if (price != null) {
            return price;
        } else {
            return "";
        }
    }

    public void setPrice(String price) {
        put(PRICE, price);
    }

}
