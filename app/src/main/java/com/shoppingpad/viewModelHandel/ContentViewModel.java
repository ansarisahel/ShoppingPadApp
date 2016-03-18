package com.shoppingpad.viewModelHandel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.graphics.Bitmap;

import com.shoppingpad.BR;

/**
 * Created by bridgelabz on 13/3/16.
    This class is created so that it's object gets created and can be used in populating
    list and later in recycler view*/

public class ContentViewModel extends BaseObservable {
    //public int mImage;
    public Bitmap mImage;
    public String mTitle;
    public String mStatus;
    public String mLastSeen;
    public String mNoOfParticipants;
    public int mNoOfViews;

    public ContentViewModel()
    {
    }

    public ContentViewModel(String mTitle, String mLastSeen, String mStatus, String mNoOfParticipants, int mNoOfViews) {
        this.mImage = mImage;
        this.mTitle = mTitle;
        this.mLastSeen = mLastSeen;
        this.mStatus = mStatus;
        this.mNoOfParticipants = mNoOfParticipants;
        this.mNoOfViews = mNoOfViews;
    }

    @Bindable
    public Bitmap getmImage() {
        return mImage;
    }

    public void setmImage(Bitmap mImage) {
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
    public int getmNoOfViews() {
        return mNoOfViews;
    }

    public void setmNoOfViews(int mNoOfViews) {
        this.mNoOfViews = mNoOfViews;
        notifyPropertyChanged(BR.mNoOfViews);
    }
}
