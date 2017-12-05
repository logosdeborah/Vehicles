package com.jeongeun.vehicles.ui.callback;

/**
 * This is for detecting item click event for RecyclerView item.
 *
 * Created by JeongEun on 2017-11-28.
 */

import android.view.View;

public interface ItemClickListener {
    void onItemClicked(View v, int position);
}
