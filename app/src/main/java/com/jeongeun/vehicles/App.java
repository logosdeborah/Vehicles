package com.jeongeun.vehicles;

import android.app.Application;

import timber.log.Timber;

/**
 * It's just for logging at the moment.
 * Created by JeongEun on 2017-11-29.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }
}
