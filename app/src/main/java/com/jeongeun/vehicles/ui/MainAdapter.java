package com.jeongeun.vehicles.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jeongeun.vehicles.R;
import com.jeongeun.vehicles.data.model.Vehicle;
import com.jeongeun.vehicles.ui.callback.ItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by JeongEun on 2017-11-27.
 */

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private List<Vehicle> mVehicles;
    private ItemClickListener mItemClickListener;

    public MainAdapter(ItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
        mVehicles = new ArrayList<>();
    }

    public void setVehicles(List<Vehicle> Vehicles) {
        mVehicles = Vehicles;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                                    .inflate(R.layout.vehicle_item, parent, false);
        return new ViewHolder(view, mItemClickListener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Vehicle vehicle = mVehicles.get(position);
        holder.name.setText(vehicle.name());
        holder.address.setText(vehicle.address());
        holder.engine.setText(vehicle.engineType());
        holder.fuel.setText(String.valueOf(vehicle.fuel()));
        holder.exterior.setText(vehicle.exterior());
        holder.interior.setText(vehicle.interior());
    }

    @Override
    public int getItemCount() {
        return mVehicles == null ? 0 : mVehicles.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.text_name) TextView name;
        @BindView(R.id.text_address) TextView address;
        @BindView(R.id.text_engine) TextView engine;
        @BindView(R.id.text_fuel) TextView fuel;
        @BindView(R.id.text_exterior) TextView exterior;
        @BindView(R.id.text_interior) TextView interior;

        public ViewHolder(View view, ItemClickListener clickListener) {
            super(view);
            ButterKnife.bind(this, view);

            view.setOnClickListener(v -> {
                clickListener.onItemClicked(view, getAdapterPosition());
            });
        }
    }
}
