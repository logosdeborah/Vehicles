package com.jeongeun.vehicles.util;

import com.google.gson.TypeAdapterFactory;
import com.ryanharter.auto.value.gson.GsonTypeAdapterFactory;

/**
 * Created by JeongEun on 2017-11-28.
 */

@GsonTypeAdapterFactory
public abstract class MyGsonTypeAdapterFactory implements TypeAdapterFactory {

    public static TypeAdapterFactory create() {
        return new AutoValueGson_MyGsonTypeAdapterFactory();
    }
}
