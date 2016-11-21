package com.rzagorski.materialinitialstest.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rzagorski.materialinitialstest.R;
import com.rzagorski.materialinitialstest.helper.SampleListCreator;

/**
 * Created by Robert Zagórski on 2016-11-21.
 */

public class BaseFragment extends android.support.v4.app.Fragment implements InitialsFragment {
    RecyclerView mRecyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_layout, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        mRecyclerView.setAdapter(new MIAdapter(getItemLayout(), SampleListCreator.populateList(100, 1)));
    }

    @Override
    public String getTitle() {
        return "1";
    }

    protected int getItemLayout() {
        return R.layout.layout_with_material_initials;
    }
}
