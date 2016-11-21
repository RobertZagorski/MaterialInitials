package com.rzagorski.materialinitialstest;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.rzagorski.materialinitialstest.base.BaseFragment;

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
                        new BaseFragment()
                })
        );
        tabLayout.setupWithViewPager(viewPager);
    }
}
