package com.rzagorski.materialinitialstest.base;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.rzagorski.materialinitials.MaterialInitialsDrawable;

import java.util.List;

/**
 * Created by Robert Zagórski on 2016-11-21.
 */

public class Adapter extends RecyclerView.Adapter<Holder> {
    int mLayoutResource;
    List<String[]> mList;

    public Adapter(int layoutResource, List<String[]> list) {
        this.mLayoutResource = layoutResource;
        this.mList = list;
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
        MaterialInitialsDrawable drawable = new MaterialInitialsDrawable(values);
        drawable.mutate();
        holder.image.setImageDrawable(drawable);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
