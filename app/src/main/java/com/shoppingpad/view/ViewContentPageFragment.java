package com.shoppingpad.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import com.shoppingpad.R;

import java.util.List;

/**
 * Created by bridgelabz on 2/4/16.
 */
public class ViewContentPageFragment extends Fragment {

    ViewPager viewPager;
    String[] mImagePath;
    String mContentId;
    List<Fragment> mFragments;
    TextView mTextPageNumber;

    public ViewContentPageFragment(ViewPager viewPager,String[] imagePath,String contentId,List<Fragment> fragments,TextView textView) {
        this.viewPager = viewPager;
        this.mImagePath = imagePath;
        this.mContentId = contentId;
        this.mFragments = fragments;
        this.mTextPageNumber = textView;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.view_content_page_fragment,container,false);
        GridView gridView = (GridView) view.findViewById(R.id.gridView);
        gridView.setAdapter(new GridViewAdapter(getActivity(),viewPager,mImagePath,mContentId,mFragments,mTextPageNumber));
        return view;
    }
}
