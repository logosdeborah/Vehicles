package com.jeongeun.vehicles.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by JeongEun on 2017-11-29.
 */

public class Placemarks {

    @SerializedName("placemarks")
    private List<Vehicle> vehicles;

    public Placemarks(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }
}
