package com.shoppingpad.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import java.util.List;

/**
 * Created by bridgelabz on 4/4/16.
 */
public class ViewContentPagePagerAdapter extends FragmentStatePagerAdapter {

    ViewPager viewPager;
    String[] mImagePath;
    String mContentId;
    List<Fragment> mFragments;
    TextView mTextPageNumber;
    public ViewContentPagePagerAdapter(FragmentManager fm, ViewPager viewPager,String[] imagePath,
                                       String contentId,List<Fragment> fragments,TextView textView) {
        super(fm);
        this.viewPager = viewPager;
        this.mImagePath = imagePath;
        this.mContentId = contentId;
        this.mFragments = fragments;
        this.mTextPageNumber = textView;
    }

    @Override
    public Fragment getItem(int position) {
        return new ViewContentPageFragment(viewPager, mImagePath,mContentId,mFragments,mTextPageNumber);
    }

    @Override
    public int getCount() {
        return 1;
    }
}
