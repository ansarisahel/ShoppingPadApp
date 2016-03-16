package com.shoppingpad.model;

import android.database.Cursor;

/**
 * Created by bridgelabz on 15/3/16.
 */
public class ContentViewModel1 {
    public int mNumberOfViews;
    public String mLastViewedDateTime;
    public String mDisplayProfile;
    public String mEmail;
    public String mMobile;
    public String mLastName;
    public String mFirstName;
    public int mUserId;
    public int mContent_id;
    public int mUserAdminId;
    public int mUserContentId;


    public void setContentViewModelInstance(Cursor contentViewData)
    {
        mNumberOfViews = contentViewData.getInt(0);
        mLastViewedDateTime = contentViewData.getString(1);
        mDisplayProfile = contentViewData.getString(2);
        mEmail = contentViewData.getString(3);
        mMobile = contentViewData.getString(4);
        mLastName = contentViewData.getString(5);
        mFirstName = contentViewData.getString(6);
        mUserId= contentViewData.getInt(7);
        mContent_id = contentViewData.getInt(8);
        mUserAdminId = contentViewData.getInt(8);
        mUserContentId = contentViewData.getInt(9);
    }
}
