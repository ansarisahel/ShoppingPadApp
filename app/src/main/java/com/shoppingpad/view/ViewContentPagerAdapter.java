package com.shoppingpad.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by bridgelabz on 26/3/16.
 */
public class ViewContentPagerAdapter extends FragmentStatePagerAdapter {


    public ViewContentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if(position == 0)
            return new ViewContentFragment1();
        if (position == 1)
            return new ViewContentFragment2();
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
