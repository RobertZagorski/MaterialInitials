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
                        new FragmentAlpha()
                })
        );
        tabLayout.setupWithViewPager(viewPager);
    }
}
