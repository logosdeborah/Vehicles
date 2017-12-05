package com.jeongeun.vehicles.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.jeongeun.vehicles.R;
import com.jeongeun.vehicles.ui.fragment.MapsFragment;
import com.jeongeun.vehicles.ui.fragment.VehicleListFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Add the vehicle list fragment if this is the first creation
        if (savedInstanceState == null) {
            VehicleListFragment fragment = new VehicleListFragment();

            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }
    }

    /**
     * Display the map fragment
     **/
    public void displayMap() {
        MapsFragment mapsFragment = new MapsFragment();

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container, mapsFragment)
                .addToBackStack(null)
                .commit();
    }
}
