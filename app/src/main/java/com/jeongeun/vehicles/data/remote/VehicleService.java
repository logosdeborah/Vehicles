package com.jeongeun.vehicles.data.remote;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jeongeun.vehicles.data.model.Placemarks;
import com.jeongeun.vehicles.util.MyGsonTypeAdapterFactory;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

/**
 * Created by JeongEun on 2017-11-28.
 */

public interface VehicleService {

    String END_POINT = "http://redirect.mytaxi.net/";

    @GET("car2go/vehicles.json")
    Call<Placemarks> getPlacemarks();

    class Creator {

        public static VehicleService CreateVehicleService() {
            Gson gson = new GsonBuilder()
                    .registerTypeAdapterFactory(MyGsonTypeAdapterFactory.create())
                    .create();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(END_POINT)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
            return retrofit.create(VehicleService.class);
        }
    }
}
