/*
 * Copyright (C) 2016 Robert Zagórski.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
