package com.shoppingpad.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
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

    FragmentActivity context;
    ViewPager viewPager;
    String[] mImagePath;
    String mContentId;
    List<Fragment> mFragments;
    TextView mTextPageNumber;

    public GridViewAdapter(FragmentActivity context, ViewPager viewPager,String[] imagePath,String contentId,List<Fragment> fragments,TextView textView) {
        this.context = context;
        this.viewPager = viewPager;
        this.mImagePath = imagePath;
        this.mContentId = contentId;
        this.mFragments = fragments;
        this.mTextPageNumber = textView;
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
        String imageUri = Environment.getExternalStorageDirectory()+"/Zip Files Extracted1/ContentId"+mContentId+"/Content/"+mImagePath[i];
            try {
                if(mImagePath[i].substring(mImagePath[i].lastIndexOf(".")+1).equals("svg")) {
                    FileInputStream fileInputStream = new FileInputStream(new File(imageUri));
                    SVG svgImage = SVGParser.getSVGFromInputStream(fileInputStream);
                    imageView.setImageDrawable(svgImage.createPictureDrawable());
                    imageView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
                }
                if(mImagePath[i].substring(mImagePath[i].lastIndexOf(".")+1).equals("png"))
                {
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inSampleSize = 2;
                    Bitmap bitmap = BitmapFactory.decodeFile(imageUri,options);
                    imageView.setImageBitmap(bitmap);
                }
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        viewPager.setAdapter(new ViewContentImagePagerAdapter(context.getSupportFragmentManager(), mFragments));
                        Toast.makeText(context, "image to be shown", Toast.LENGTH_SHORT).show();
                        viewPager.setCurrentItem(i);
                        mTextPageNumber.setText("page "+(viewPager.getCurrentItem()+1)+" of "+(mFragments.size()));
                    }
                });
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        return view;
    }
}
