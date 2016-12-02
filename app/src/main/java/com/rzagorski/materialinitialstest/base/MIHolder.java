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
import android.view.View;
import android.widget.TextView;

import com.rzagorski.materialinitials.MaterialInitials;
import com.rzagorski.materialinitialstest.R;

public class MIHolder extends RecyclerView.ViewHolder {
    public MaterialInitials image;
    public TextView textView;

    public MIHolder(View itemView) {
        super(itemView);
        image = (MaterialInitials) itemView.findViewById(R.id.image);
        textView = (TextView) itemView.findViewById(R.id.text_view);
    }
}