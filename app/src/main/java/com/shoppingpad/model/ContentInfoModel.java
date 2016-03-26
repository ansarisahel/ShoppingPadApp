package com.shoppingpad.model;

import android.database.Cursor;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by bridgelabz on 15/3/16.
 Purpose: This class is used to be populated retrieved from JSON
          This class contains the attributes of ContentInfoTbl*/



public class ContentInfoModel {
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
    public String mZip;

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
}

