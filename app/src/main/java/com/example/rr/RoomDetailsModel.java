package com.example.rr;

import android.net.Uri;

public class RoomDetailsModel {
    int mainImg, vacancyImg, distanceImg;
    private String apName, distance, vacancy, gender, price;

    private int apImageResource;

    public RoomDetailsModel(int apImageResource, String apName, String distance, String vacancy, String price, String gender){
        this.apImageResource = apImageResource;

        this.apName=apName;
        this.distance=distance;
        this.vacancy=vacancy;
        this.price=price;
        this.gender=gender;
    }

    public int getApImageResource() {
        return apImageResource;
    }

    public String getApName() {
        return apName;
    }

    public String getDistance() {
        return distance;
    }

    public String getVacancy() {
        return vacancy;
    }

    public String getPrice() {
        return price;
    }

    public String getGender() {
        return gender;
    }
}
