package com.shoppingpad.view;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.shoppingpad.viewModelHandel.ViewContentHandler;

/**
 * Created by bridgelabz on 26/3/16.
 */
public class ViewContentPagerAdapter extends FragmentStatePagerAdapter {

    public Context context;
    public ViewContentHandler mViewContentHandlerInstance;

    public ViewContentPagerAdapter(FragmentManager fm,Context viewContent,ViewContentHandler instance) {
        super(fm);
        this.context = viewContent;
        this.mViewContentHandlerInstance = instance;
    }

    @Override
    public Fragment getItem(int position) {
        if(position == 0) {
            return new ViewContentFragment1();
        }
        if (position == 1) {
            return new ViewContentFragment2();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
