package com.shoppingpad.view;

import android.content.Context;
import android.media.Image;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import com.larvalabs.svgandroid.SVG;
import com.larvalabs.svgandroid.SVGParser;
import com.shoppingpad.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by bridgelabz on 2/4/16.
 */
public class GridViewAdapter extends BaseAdapter {

    int[] images = new int[] {R.raw.computer,R.raw.ic,R.raw.ic_open_with_black_24px};
    FragmentActivity context;
    ViewPager viewPager;
    String[] mImagePath;
    String mContentId;
    List<Fragment> mFragments;

    public GridViewAdapter(FragmentActivity context, ViewPager viewPager,String[] imagePath,String contentId,List<Fragment> fragments) {
        this.context = context;
        this.viewPager = viewPager;
        this.mImagePath = imagePath;
        this.mContentId = contentId;
        this.mFragments = fragments;
    }

    @Override
    public int getCount() {
        return mImagePath.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.grid_view_row_view,viewGroup,false);
        ImageView imageView = (ImageView) view.findViewById(R.id.gridViewImageView);
        File imageFile = new File(Environment.getExternalStorageDirectory()+"/Zip Files Extracted1/ContentId"+mContentId+"/Content/"+mImagePath[i]);
        try {
            FileInputStream fileInputStream = new FileInputStream(imageFile);
            SVG svgImage = SVGParser.getSVGFromInputStream(fileInputStream);
            imageView.setImageDrawable(svgImage.createPictureDrawable());
            imageView.setLayerType(View.LAYER_TYPE_SOFTWARE,null);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    viewPager.setAdapter(new ViewContentImagePagerAdapter(context.getSupportFragmentManager(),mFragments));
                    viewPager.setCurrentItem(i);
                }
            });
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return view;
    }
}
