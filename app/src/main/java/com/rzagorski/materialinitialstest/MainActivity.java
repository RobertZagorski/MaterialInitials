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
package com.rzagorski.materialinitialstest;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.rzagorski.materialinitialstest.lists.Fragment1MI;
import com.rzagorski.materialinitialstest.lists.Fragment1View;
import com.rzagorski.materialinitialstest.lists.Fragment2MI;
import com.rzagorski.materialinitialstest.lists.Fragment3MI;
import com.rzagorski.materialinitialstest.lists.Fragment3words;
import com.rzagorski.materialinitialstest.lists.Fragment4MI;
import com.rzagorski.materialinitialstest.lists.FragmentAlpha;
import com.rzagorski.materialinitialstest.lists.FragmentBackgroundColors;
import com.rzagorski.materialinitialstest.lists.FragmentDifferentTextColor;
import com.rzagorski.materialinitialstest.lists.FragmentRotated;

public class MainActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(),
                new Fragment[]{
                        new Fragment1MI(),
                        new Fragment1View(),
                        new Fragment2MI(),
                        new Fragment3MI(),
                        new Fragment4MI(),
                        new Fragment3words(),
                        new FragmentBackgroundColors(),
                        new FragmentAlpha(),
                        new FragmentDifferentTextColor(),
                        new FragmentRotated()
                })
        );
        tabLayout.setupWithViewPager(viewPager);
    }
}
