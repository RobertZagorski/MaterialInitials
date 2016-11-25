package com.rzagorski.materialinitialstest.lists;

import android.support.v4.content.ContextCompat;
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

public class FragmentDifferentTextColor extends BaseFragment {

    @Override
    public String getTitle() {
        return "Text color";
    }

    protected RecyclerView.Adapter getAdapter() {
        return new MIAdapter(R.layout.layout_with_material_initials_modified,
                SampleListCreator.populateList(100, 1, 2),
                new ImageHandler<MIHolder>() {
                    @Override
                    public void onBindImage(MIHolder holder, String[] values) {
                        holder.image.setTexts(values);
                    }
                });
    }
}
