package com.shoppingpad.view;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.larvalabs.svgandroid.SVG;
import com.larvalabs.svgandroid.SVGParser;
import com.shoppingpad.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;


/**
 * Created by bridgelabz on 29/3/16.
 */
public class ViewContentImageFragment extends Fragment {


    public static Fragment getFragments(String imageUri,String contentId)
    {
        ViewContentImageFragment f = new ViewContentImageFragment();
        Bundle bundle = new Bundle(2);
        bundle.putString("imageUri",imageUri);
        bundle.putString("contentId",contentId);
        f.setArguments(bundle);
        return f;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.view_content_image_fragment,container,false);
        String imageUri = getArguments().getString("imageUri");
        String contentId = getArguments().getString("contentId");
        ImageView imageView = (ImageView) view.findViewById(R.id.viewContentFragment1ImageView);
        try {
            String imageUriOnSDCard = Environment.getExternalStorageDirectory()+"/Zip Files Extracted1/ContentId"+contentId+"/Content/"+imageUri;
            if(imageUriOnSDCard.substring(imageUriOnSDCard.lastIndexOf(".")+1).equals("svg")) {
                FileInputStream fileInputStream = new FileInputStream(new File(imageUriOnSDCard));
                SVG svgImage = SVGParser.getSVGFromInputStream(fileInputStream);
                imageView.setImageDrawable(svgImage.createPictureDrawable());
                imageView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
            }
            if(imageUriOnSDCard.substring(imageUriOnSDCard.lastIndexOf(".")+1).equals("png")) {
                Glide.with(getActivity()).load(imageUriOnSDCard).into(imageView);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return view;
    }
}
