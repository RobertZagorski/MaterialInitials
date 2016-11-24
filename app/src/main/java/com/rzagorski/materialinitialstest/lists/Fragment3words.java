package com.rzagorski.materialinitialstest.lists;

import android.support.v7.widget.RecyclerView;

import com.rzagorski.materialinitialstest.R;
import com.rzagorski.materialinitialstest.base.BaseFragment;
import com.rzagorski.materialinitialstest.base.MIAdapter;
import com.rzagorski.materialinitialstest.base.MIHolder;
import com.rzagorski.materialinitialstest.helper.ImageHandler;
import com.rzagorski.materialinitialstest.helper.SampleListCreator;

/**
 * Created by Robert Zagórski on 2016-11-21.
 */

public class Fragment3words extends BaseFragment {

    @Override
    public String getTitle() {
        return "3 words initials";
    }

    protected RecyclerView.Adapter getAdapter() {
        return new MIAdapter(R.layout.layout_with_material_initials,
                SampleListCreator.populateList(100, 1, 3),
                new ImageHandler<MIHolder>() {
                    @Override
                    public void onBindImage(MIHolder holder, String[] values) {
                        holder.image.setTexts(values);
                    }
                });
    }
}