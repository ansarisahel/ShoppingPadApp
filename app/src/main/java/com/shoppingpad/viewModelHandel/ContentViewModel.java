package com.shoppingpad.viewModelHandel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import com.shoppingpad.BR;

/**
 * Created by bridgelabz on 13/3/16.
    This class is created so that it's object gets created and can be used in populating
    list and later in recycler view*/

public class ContentViewModel extends BaseObservable {
    //public int mImage;
    public String mImage;
    public String mTitle;
    public String mStatus;
    public String mLastSeen;
    public String mNoOfParticipants;
    public String mNoOfViews;

    public ContentViewModel()
    {
    }

    public ContentViewModel(String title, String lastSeen, String status,
                            String noOfParticipants, String noOfViews, String image) {
        this.mImage = image;
        this.mTitle = title;
        this.mLastSeen = lastSeen;
        this.mStatus = status;
        this.mNoOfParticipants = noOfParticipants;
        this.mNoOfViews = noOfViews;
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
    public String getmStatus() {
        return mStatus;
    }

    public void setmStatus(String mStatus) {
        this.mStatus = mStatus;
        notifyPropertyChanged(BR.mStatus);
    }

    @Bindable
    public String getmLastSeen() {
        return mLastSeen;
    }

    public void setmLastSeen(String mLastSeen) {
        this.mLastSeen = mLastSeen;
        notifyPropertyChanged(BR.mLastSeen);
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
