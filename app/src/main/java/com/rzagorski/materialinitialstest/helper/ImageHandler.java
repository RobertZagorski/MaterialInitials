package com.rzagorski.materialinitialstest.helper;

import android.support.v7.widget.RecyclerView;

/**
 * Created by Robert Zagórski on 2016-11-24.
 */
public interface ImageHandler<T extends RecyclerView.ViewHolder> {

    void onBindImage(T holder, String[] values);
}
