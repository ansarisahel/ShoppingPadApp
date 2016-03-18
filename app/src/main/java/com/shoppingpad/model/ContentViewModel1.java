package com.shoppingpad.model;

import android.database.Cursor;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by bridgelabz on 15/3/16.
 Purpose: This class is used to be populated retrieved from JSON
 This class contains the attributes of ContentViewTbl*/
public class ContentViewModel1 {
    public String mNumberOfViews;
    public String mLastViewedDateTime;
    public String mDisplayProfile;
    public String mEmail;
    public String mLastName;
    public String mFirstName;
    public String mUserId;
    public String mContent_id;
    public String mUserAdminId;
    public String mUserContentId;
    public String mNumberOfParticipants;
    public String mAction;


    public void setContentViewModelInstance(JSONObject contentViewData)
    {
        try {
            mAction = contentViewData.getString("action");
            mNumberOfParticipants = contentViewData.getString("numberofparticipant");
            mNumberOfViews = contentViewData.getString("numberOfViews");
            mLastViewedDateTime = contentViewData.getString("lastViewedDateTime");
            mDisplayProfile = contentViewData.getString("displayProfile");
            mEmail = contentViewData.getString("email");
            mLastName = contentViewData.getString("lastName");
            mFirstName = contentViewData.getString("firstName");
            mUserId = contentViewData.getString("userId");
            mContent_id = contentViewData.getString("contentId");
            mUserAdminId = contentViewData.getString("userAdminId");
            mUserContentId = contentViewData.getString("userContentId");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
