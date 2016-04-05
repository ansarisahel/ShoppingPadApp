package com.shoppingpad.viewModelHandel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.shoppingpad.BR;

import java.io.InputStream;

/**
 * Created by bridgelabz on 26/3/16.
 */
public class ViewContentViewModel extends BaseObservable {
    public String mImage;
    public String mTitle;
    public String mNoOfViews;
    public String mNoOfParticipants;
    private String mSvgImage1;
    private String mSvgImage2;
    private String mPngImage1;
    private String mPngImage2;

    public String getmSvgImage1() {
        return mSvgImage1;
    }

    public void setmSvgImage1(String mSvgImage1) {
        this.mSvgImage1 = mSvgImage1;
    }

    public String getmPngImage2() {
        return mPngImage2;
    }

    public void setmPngImage2(String mPngImage2) {
        this.mPngImage2 = mPngImage2;
    }

    public String getmPngImage1() {
        return mPngImage1;
    }

    public void setmPngImage1(String mPngImage1) {
        this.mPngImage1 = mPngImage1;
    }

    public String getmSvgImage2() {
        return mSvgImage2;
    }

    public void setmSvgImage2(String mSvgImage2) {
        this.mSvgImage2 = mSvgImage2;
    }



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
}
