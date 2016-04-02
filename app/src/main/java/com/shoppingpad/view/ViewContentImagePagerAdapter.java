package com.shoppingpad.view;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.shoppingpad.viewModelHandel.ViewContentHandler;

import java.util.List;

/**
 * Created by bridgelabz on 26/3/16.
 */
public class ViewContentImagePagerAdapter extends FragmentStatePagerAdapter {

    List<Fragment> fragments;
    public ViewContentImagePagerAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }


}
