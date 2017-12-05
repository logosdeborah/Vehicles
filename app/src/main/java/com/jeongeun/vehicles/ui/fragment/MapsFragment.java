package com.jeongeun.vehicles.ui.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.jeongeun.vehicles.R;
import com.jeongeun.vehicles.data.model.Vehicle;
import com.jeongeun.vehicles.util.MapUtil;
import com.jeongeun.vehicles.viewmodel.MainViewModel;

import java.util.List;

import timber.log.Timber;

/**
 * Created by JeongEun on 2017-11-29.
 */

public class MapsFragment extends Fragment implements OnMapReadyCallback {

    private int MAP_ZOOM_LEVEL = 15;
    private MainViewModel mViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View contentView = inflater.inflate(R.layout.fragment_maps, container, false);
        Toolbar toolbar = contentView.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        supportMapFragment.getMapAsync(this);
        return contentView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        mViewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        // observe a selected marker
        mViewModel.getSelected().observe(this,  vehicle -> {
            updateCameraToSelectedVehicle(googleMap, vehicle);
        });

        mViewModel.getVehicles().observe(this, vehicles -> {
            createMarkers(googleMap, vehicles);
        });
    }

    /**
     * Create markers and add to the map. Only selected vehicle information should be displayed.
     * @param vehicles
     * @return marker list
     */
    public void createMarkers(GoogleMap map, List<Vehicle> vehicles) {
        vehicles.forEach(vehicle -> {
            Marker marker = map.addMarker(new MarkerOptions()
                    .position(MapUtil.convertToLatLng(vehicle))
                    .title(vehicle.name())
                    .snippet(vehicle.address())
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
            if (mViewModel.isSelected(vehicle)) {
                marker.showInfoWindow();
            }
        });
    }

    /**
     * To update camera whenever the selected vehicle changes.
     * @param map
     * @param vehicle
     */
    public void updateCameraToSelectedVehicle(GoogleMap map, Vehicle vehicle) {
        CameraUpdate cameraUpdate = CameraUpdateFactory
                .newLatLngZoom(MapUtil.convertToLatLng(vehicle), MAP_ZOOM_LEVEL);
        map.moveCamera(cameraUpdate);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home :
                Timber.d("Home key pressed");
                getActivity().onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);

    }
}
