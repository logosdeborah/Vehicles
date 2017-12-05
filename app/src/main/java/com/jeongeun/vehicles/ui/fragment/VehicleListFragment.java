package com.jeongeun.vehicles.ui.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.jeongeun.vehicles.R;
import com.jeongeun.vehicles.ui.MainActivity;
import com.jeongeun.vehicles.ui.MainAdapter;
import com.jeongeun.vehicles.ui.callback.ItemClickListener;
import com.jeongeun.vehicles.viewmodel.MainViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by JeongEun on 2017-11-29.
 */

public class VehicleListFragment extends Fragment implements ItemClickListener {

    @BindView(R.id.progressBar) ProgressBar mProgressBar;
    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;
    private MainViewModel mMainViewModel;
    private MainAdapter mMainAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_vehicle_list, container, false);
        ButterKnife.bind(this, view);

        mRecyclerView.setAdapter(mMainAdapter);

        // Action bar setup
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMainViewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);
        mMainViewModel.getVehicles().observe(this, vehicles -> {
            // update UI
            mMainAdapter.setVehicles(vehicles);
            mMainAdapter.notifyDataSetChanged();
            mProgressBar.setVisibility(View.GONE);
        });
        mMainAdapter = new MainAdapter(this);
    }

    @Override
    public void onItemClicked(View v, int position) {
        mMainViewModel.select(position);
        ((MainActivity)getActivity()).displayMap();
    }

}
