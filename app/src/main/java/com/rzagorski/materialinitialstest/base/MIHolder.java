package com.rzagorski.materialinitialstest.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.rzagorski.materialinitials.MaterialInitials;
import com.rzagorski.materialinitialstest.R;

public class MIHolder extends RecyclerView.ViewHolder {
    MaterialInitials image;
    TextView textView;

    public MIHolder(View itemView) {
        super(itemView);
        image = (MaterialInitials) itemView.findViewById(R.id.image);
        textView = (TextView) itemView.findViewById(R.id.text_view);
    }
}