package com.shoppingpad.model;

import android.database.Cursor;
import android.util.Log;

import com.shoppingpad.database.ImagesURIModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by bridgelabz on 15/3/16.
 Purpose: This class is used to be populated retrieved from JSON
          This class contains the attributes of ContentInfoTbl*/



public class ContentInfoModel {

    public String mZip;
    public String mModified_at;
    public String mCreated_at;
    public String mSyncDateTime;
    public String mDescription;
    public String mContentLink;
    public String mImagesLink;
    public String mDisplay_name;
    public String mUrl;
    public String mTitle;
    public String mContentType;
    public String mContentId;
    public String[] mSvgImages;
    public String[] mPngImages;

    // this method will fetch the data form the JSONObject passed in the argument and
    // populate the contentInfoModelInstance
    public void setContentInfoModelInstance(JSONObject contentInfoData) {
        try {
            mModified_at = contentInfoData.getString("modified_at");
            mCreated_at = contentInfoData.getString("created_at");
            mSyncDateTime = contentInfoData.getString("syncDateTime");
            mDescription = contentInfoData.getString("decription");
            mContentLink = contentInfoData.getString("contentLink");
            mImagesLink = contentInfoData.getString("imagesLink");
            mDisplay_name = contentInfoData.getString("display_name");
            mUrl = contentInfoData.getString("url");
            mTitle = contentInfoData.getString("title");
            mContentType = contentInfoData.getString("contentType");
            mContentId = contentInfoData.getString("content_id");
            mZip = contentInfoData.getString("zip");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    // this method will fetch the data form the table row passed in the argument and
    // populate the contentInfoModelInstance
    public void setContentInfoModelInstance(Cursor contentInfoData,ImagesURIModel images) {

            mModified_at = contentInfoData.getString(contentInfoData.getColumnIndex("modified_at"));
            mCreated_at = contentInfoData.getString(contentInfoData.getColumnIndex("created_at"));
            mSyncDateTime = contentInfoData.getString(contentInfoData.getColumnIndex("syncDateTime"));
            mDescription = contentInfoData.getString(contentInfoData.getColumnIndex("description"));
            mContentLink = contentInfoData.getString(contentInfoData.getColumnIndex("contentLink"));
            mImagesLink = contentInfoData.getString(contentInfoData.getColumnIndex("imagesLink"));
            mDisplay_name = contentInfoData.getString(contentInfoData.getColumnIndex("display_name"));
            mUrl = contentInfoData.getString(contentInfoData.getColumnIndex("url"));
            mTitle = contentInfoData.getString(contentInfoData.getColumnIndex("title"));
            mContentType = contentInfoData.getString(contentInfoData.getColumnIndex("contentType"));
            mContentId = contentInfoData.getString(contentInfoData.getColumnIndex("content_id"));
            mZip = contentInfoData.getString(contentInfoData.getColumnIndex("zip"));

            if(images != null) {
                mSvgImages = images.getmSvgImages();
                mPngImages = images.getmPngImages();
            }

    }
}

