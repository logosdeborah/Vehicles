package com.jeongeun.vehicles.util;

import com.google.android.gms.maps.model.LatLng;
import com.jeongeun.vehicles.data.model.Vehicle;

/**
 * Created by JeongEun on 2017-11-29.
 */

public class MapUtil {

    /**
     * Convert vehicle coordinates to LatLat.
     * @param vehicle
     * @return
     */
    public static LatLng convertToLatLng(Vehicle vehicle) {
        LatLng latLng = null;

        if (vehicle != null
                && vehicle.coordinates() != null
                && vehicle.coordinates().size() > 2) {
            latLng = new LatLng(vehicle.coordinates().get(1),
                    vehicle.coordinates().get(0));
        }

        return latLng;
    }
}
