package com.shoppingpad.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by bridgelabz on 2/4/16.
 */
public class ViewContentPagePagerAdapter extends FragmentStatePagerAdapter {


    public ViewContentPagePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return new ViewContentPageFragment();
    }

    @Override
    public int getCount() {
        return 1;
    }
}
