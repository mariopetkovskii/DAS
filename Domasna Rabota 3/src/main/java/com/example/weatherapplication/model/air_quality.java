package com.example.weatherapplication.model;

import lombok.Data;

@Data
public class air_quality {
    //"co": 300.3999938964844,
    //      "no2": 6,
    //      "o3": 43.29999923706055,
    //      "so2": 1,
    //      "pm2_5": 6.699999809265137,
    //      "pm10": 7.400000095367432,
    private Double co;
    private Double no2;
    private Double o3;
    private Double so2;
    private Double pm2_5;
    private Double pm10;

    public String getAirQuality(){
        return String.format("%.0f", (co + no2 + o3 + so2 + pm2_5 + pm10) / 8);
    }

    public Double getNo2() {
        return no2;
    }
}
