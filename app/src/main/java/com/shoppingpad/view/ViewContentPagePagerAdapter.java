package com.shoppingpad.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.BaseAdapter;

import com.shoppingpad.R;

import java.util.List;

/**
 * Created by bridgelabz on 4/4/16.
 */
public class ViewContentPagePagerAdapter extends FragmentStatePagerAdapter {

    ViewPager viewPager;
    List<Fragment> fragments;
    public ViewContentPagePagerAdapter(FragmentManager fm, ViewPager viewPager,List<Fragment> fragments) {
        super(fm);
        this.viewPager = viewPager;
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return new ViewContentPageFragment(viewPager,fragments);
    }

    @Override
    public int getCount() {
        return 1;
    }
}
