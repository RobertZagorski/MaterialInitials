package com.rzagorski.materialinitialstest.lists;

import android.support.v7.widget.RecyclerView;

import com.rzagorski.materialinitials.MaterialInitialsDrawable;
import com.rzagorski.materialinitialstest.R;
import com.rzagorski.materialinitialstest.base.Adapter;
import com.rzagorski.materialinitialstest.base.BaseFragment;
import com.rzagorski.materialinitialstest.base.Holder;
import com.rzagorski.materialinitialstest.helper.ImageHandler;
import com.rzagorski.materialinitialstest.helper.SampleListCreator;

/**
 * Created by Robert Zagórski on 2016-11-21.
 */

public class Fragment1View extends BaseFragment {

    @Override
    public String getTitle() {
        return "ImageView";
    }

    protected RecyclerView.Adapter getAdapter() {
        return new Adapter(R.layout.layout_with_simple_image,
                SampleListCreator.populateList(100, 1, 2),
                new ImageHandler<Holder>() {
                    @Override
                    public void onBindImage(Holder holder, String[] values) {
                        MaterialInitialsDrawable drawable = new MaterialInitialsDrawable(values);
                        drawable.mutate();
                        holder.image.setImageDrawable(drawable);
                    }
                });
    }
}
