package com.shoppingpad.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.List;

/**
 * Created by bridgelabz on 4/4/16.
 */
public class ViewContentPagePagerAdapter extends FragmentStatePagerAdapter {

    ViewPager viewPager;
    String[] mImagePath;
    String mContentId;
    List<Fragment> mFragments;
    public ViewContentPagePagerAdapter(FragmentManager fm, ViewPager viewPager,String[] imagePath,String contentId,List<Fragment> fragments) {
        super(fm);
        this.viewPager = viewPager;
        this.mImagePath = imagePath;
        this.mContentId = contentId;
        this.mFragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return new ViewContentPageFragment(viewPager, mImagePath,mContentId,mFragments);
    }

    @Override
    public int getCount() {
        return 1;
    }
}
