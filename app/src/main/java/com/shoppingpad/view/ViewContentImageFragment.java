package com.shoppingpad.view;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.larvalabs.svgandroid.SVG;
import com.larvalabs.svgandroid.SVGParser;
import com.shoppingpad.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;


/**
 * Created by bridgelabz on 29/3/16.
 */
public class ViewContentImageFragment extends Fragment {


    public static Fragment getFragments(String imageUri)
    {
        ViewContentImageFragment f = new ViewContentImageFragment();
        Bundle bundle = new Bundle(1);
        bundle.putString("imageUri",imageUri);
        f.setArguments(bundle);
        return f;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.view_content_image_fragment,container,false);
        String imageUri = getArguments().getString("imageUri");
        ImageView imageView = (ImageView) view.findViewById(R.id.viewContentFragment1ImageView);
        try {
            File imageUriOnSDCard = new File(Environment.getExternalStorageDirectory()+"/Zip Files Extracted1/ContentId1/Content/"+imageUri);
            FileInputStream fileInputStream = new FileInputStream(imageUriOnSDCard);
            SVG svgImage = SVGParser.getSVGFromInputStream(fileInputStream);
            imageView.setImageDrawable(svgImage.createPictureDrawable());
            imageView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return view;
    }
}
