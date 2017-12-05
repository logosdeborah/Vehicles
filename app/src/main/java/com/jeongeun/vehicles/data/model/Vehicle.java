package com.jeongeun.vehicles.data.model;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import java.util.List;

/**
 * POJO for vehicle information.
 *
 * Created by JeongEun on 2017-11-27.
 */

@AutoValue
public abstract class Vehicle implements Parcelable {

    public abstract String name();
    public abstract String address();
    public abstract List<Double> coordinates();
    public abstract String engineType();
    public abstract String exterior();
    public abstract Integer fuel();
    public abstract String interior();
    public abstract String vin();

    public static TypeAdapter<Vehicle> typeAdapter (Gson gson) {
        return new AutoValue_Vehicle.GsonTypeAdapter(gson);
    }

    public static Builder builder() {
        return new AutoValue_Vehicle.Builder();
    }

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder setName(String name);
        public abstract Builder setAddress(String address);
        public abstract Builder setCoordinates(List<Double> coordinates);
        public abstract Builder setEngineType(String engineType);
        public abstract Builder setExterior(String exterior);
        public abstract Builder setFuel(Integer fuel);
        public abstract Builder setInterior(String interior);
        public abstract Builder setVin(String vin);
        public abstract Vehicle build();
    }
}
