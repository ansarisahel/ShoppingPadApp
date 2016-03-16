package com.shoppingpad.model;

import android.database.Cursor;

/**
 * Created by bridgelabz on 15/3/16.
 */
public class ContentInfoModel {
    public String mModified_at;
    public String mCreated_at;
    public String mSyncDateTime;
    public String mDescription;
    public String mContentLink;
    public String mImagesLink;
    public String mDisplay_name;
    public String mUrl;
    public int mTitle;
    public String mContentType;
    public int mContentId;

    public void setContentInfoModelInstance(Cursor contentInfoData) {
        mModified_at = contentInfoData.getString(0);
        mCreated_at = contentInfoData.getString(1);
        mSyncDateTime = contentInfoData.getString(2);
        mDescription = contentInfoData.getString(3);
        mContentLink = contentInfoData.getString(4);
        mImagesLink = contentInfoData.getString(5);
        mDisplay_name = contentInfoData.getString(6);
        mUrl = contentInfoData.getString(7);
        mTitle= contentInfoData.getInt(8);
        mContentType= contentInfoData.getString(9);
        mContentId = contentInfoData.getInt(10);

    }
}

