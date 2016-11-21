package com.rzagorski.materialinitialstest.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.rzagorski.materialinitialstest.R;

public class Holder extends RecyclerView.ViewHolder {
    ImageView image;
    TextView textView;

    public Holder(View itemView) {
        super(itemView);
        image = (ImageView) itemView.findViewById(R.id.image);
        textView = (TextView) itemView.findViewById(R.id.text_view);
    }
}