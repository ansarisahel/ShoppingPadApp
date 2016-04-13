package com.shoppingpad.database;

/**
 * Created by bridgelabz on 13/4/16.
 */
public class ImagesURIModel {
    String[] mSvgImages;
    String[] mPngImages;

    public ImagesURIModel(){
        mSvgImages = new String[2];
        mPngImages = new String[2];
    }

    public ImagesURIModel(String[] mSvgImages, String[] mPngImages) {
        this.mSvgImages = mSvgImages;
        this.mPngImages = mPngImages;
    }

    public String[] getmSvgImages() {
        return mSvgImages;
    }

    public void setmSvgImages(String[] mSvgImages) {
        this.mSvgImages = mSvgImages;
    }

    public String[] getmPngImages() {
        return mPngImages;
    }

    public void setmPngImages(String[] mPngImages) {
        this.mPngImages = mPngImages;
    }
}
