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
package com.rzagorski.materialinitialstest.lists;

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

public class FragmentRotated extends BaseFragment {

    @Override
    public String getTitle() {
        return "Text color";
    }

    protected RecyclerView.Adapter getAdapter() {
        return new MIAdapter(R.layout.layout_with_material_initials,
                SampleListCreator.populateList(100, 1, 2),
                new ImageHandler<MIHolder>() {
                    @Override
                    public void onBindImage(MIHolder holder, String[] values) {
                        holder.image.setTexts(values);
                        holder.image.setTextRotation(20);
                    }
                });
    }
}
