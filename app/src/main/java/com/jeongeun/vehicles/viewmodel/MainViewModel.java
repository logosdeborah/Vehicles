package com.jeongeun.vehicles.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.jeongeun.vehicles.data.model.Placemarks;
import com.jeongeun.vehicles.data.model.Vehicle;
import com.jeongeun.vehicles.data.remote.VehicleService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;


/**
 * ViewModel will be retained in spite of fragment changes. So no need for reloading vehicles.
 *
 * Created by JeongEun on 2017-11-27.
 */

public class MainViewModel extends ViewModel {

    private MutableLiveData<List<Vehicle>> mVehicles;
    private MutableLiveData<Vehicle> mSelectedVehicle;
    private VehicleService mVehicleService;

    public MainViewModel() {
        mVehicleService = VehicleService.Creator.CreateVehicleService();
        mSelectedVehicle = new MutableLiveData<>();
    }

    public LiveData<List<Vehicle>> getVehicles() {

        if (mVehicles == null) {
            mVehicles = new MutableLiveData<>();
            loadVehicles();

        }
        return mVehicles;
    }

    public void loadVehicles() {

       mVehicleService.getPlacemarks().enqueue(new Callback<Placemarks>() {
            @Override
            public void onResponse(Call<Placemarks> call, Response<Placemarks> response) {
                mVehicles.setValue(response.body().getVehicles());
            }

            @Override
            public void onFailure(Call<Placemarks> call, Throwable t) {
                Timber.d("onFailure = " + t.getMessage());
                mVehicles.setValue(null);
            }
        });

    }

    public void select(int position) {
        mSelectedVehicle.setValue(mVehicles.getValue().get(position));
    }

    public boolean isSelected(Vehicle vehicle) {
        return mSelectedVehicle == null ? false : mSelectedVehicle.getValue().equals(vehicle);
    }

    public LiveData<Vehicle> getSelected() {
        return mSelectedVehicle;
    }

}
