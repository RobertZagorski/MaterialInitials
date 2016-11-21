package com.rzagorski.materialinitialstest.base;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Robert Zagórski on 2016-11-21.
 */

public class MIAdapter extends RecyclerView.Adapter<MIHolder> {
    int mLayoutResource;
    List<String[]> mList;

    public MIAdapter(int layoutResource, List<String[]> list) {
        this.mLayoutResource = layoutResource;
        this.mList = list;
    }

    @Override
    public MIHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MIHolder(LayoutInflater.from(parent.getContext()).inflate(mLayoutResource, parent, false));
    }

    @Override
    public void onBindViewHolder(MIHolder holder, int position) {
        String[] values = mList.get(position);
        String outputText = "";
        for (int i = 0; i < values.length; ++i) {
            if (i < values.length - 1) {
                outputText += ",";
            }
            outputText += values[i];
        }
        holder.textView.setText(outputText);
        holder.image.setTexts(values);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
