package com.shoppingpad.viewModelHandel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.shoppingpad.BR;

/**
 * Created by bridgelabz on 26/3/16.
 */
public class ViewContentViewModel extends BaseObservable {
    public String mImage;
    public String mTitle;
    public String mNoOfViews;
    public String mNoOfParticipants;
    private String[] mSvgImages;
    private String[] mPngImages;


    public ViewContentViewModel()
    {
    }

    public ViewContentViewModel(String mImage, String mTitle, String mNoOfViews, String mNoOfParticipants) {
        this.mImage = mImage;
        this.mTitle = mTitle;
        this.mNoOfViews = mNoOfViews;
        this.mNoOfParticipants = mNoOfParticipants;
    }

    @Bindable
    public String getmImage() {
        return mImage;
    }

    public void setmImage(String mImage) {
        this.mImage = mImage;
        notifyPropertyChanged(BR.mImage);
    }

    @Bindable
    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
        notifyPropertyChanged(BR.mTitle);
    }

    @Bindable
    public String getmNoOfParticipants() {
        return mNoOfParticipants;
    }

    public void setmNoOfParticipants(String mNoOfParticipants) {
        this.mNoOfParticipants = mNoOfParticipants;
        notifyPropertyChanged(BR.mNoOfParticipants);
    }

    @Bindable
    public String getmNoOfViews() {
        return mNoOfViews;
    }

    public void setmNoOfViews(String mNoOfViews) {
        this.mNoOfViews = mNoOfViews;
        notifyPropertyChanged(BR.mNoOfViews);
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
