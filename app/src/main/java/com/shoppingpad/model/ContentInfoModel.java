package com.shoppingpad.model;

import android.database.Cursor;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
            mTitle = contentInfoData.getInt("title");
            mContentType = contentInfoData.getString("contentType");
            mContentId = contentInfoData.getInt("content_id");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}

