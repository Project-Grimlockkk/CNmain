package com.example.rr;

public class RoomDetailsModel {
    int mainImg, vacancyImg, distanceImg;
    String apName, distance, vacancy, price;

    public RoomDetailsModel(int mainImg, int vacancyImg, int distanceImg, String apName, String distance, String vacancy, String price){
        this.mainImg=mainImg;
        this.vacancyImg=vacancyImg;
        this.distanceImg=distanceImg;
        this.apName=apName;
        this.distance=distance;
        this.vacancy=vacancy;
        this.price=price;
    }

}
