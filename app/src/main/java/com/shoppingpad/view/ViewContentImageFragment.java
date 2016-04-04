package com.shoppingpad.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.larvalabs.svgandroid.SVG;
import com.larvalabs.svgandroid.SVGParser;
import com.shoppingpad.R;


/**
 * Created by bridgelabz on 29/3/16.
 */
public class ViewContentImageFragment extends Fragment {


    public static Fragment getFragments(int drawable)
    {
        ViewContentImageFragment f = new ViewContentImageFragment();
        Bundle bundle = new Bundle(1);
        bundle.putInt("drawable",drawable);
        f.setArguments(bundle);
        return f;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.view_content_image_fragment,container,false);
        int drawable = getArguments().getInt("drawable");
        ImageView imageView = (ImageView) view.findViewById(R.id.viewContentFragment1ImageView);
        SVG svgImage = SVGParser.getSVGFromResource(getResources(),drawable);
        imageView.setImageDrawable(svgImage.createPictureDrawable());
        imageView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        return view;
    }
}
