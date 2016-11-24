package com.rzagorski.materialinitialstest.base;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.rzagorski.materialinitialstest.helper.ImageHandler;

import java.util.List;

/**
 * Created by Robert Zagórski on 2016-11-21.
 */

public class Adapter extends RecyclerView.Adapter<Holder> {
    int mLayoutResource;
    List<String[]> mList;
    ImageHandler<Holder> mImageHandler;

    public Adapter(int layoutResource, List<String[]> list, ImageHandler imageHandler) {
        this.mLayoutResource = layoutResource;
        this.mList = list;
        mImageHandler = imageHandler;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(mLayoutResource, parent, false));
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        String[] values = mList.get(position);
        String outputText = "";
        for (int i = 0; i < values.length; ++i) {
            if (i > 0 && i < values.length - 1) {
                outputText += ", ";
            }
            outputText += values[i];
        }
        holder.textView.setText(outputText);
        mImageHandler.onBindImage(holder, values);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
