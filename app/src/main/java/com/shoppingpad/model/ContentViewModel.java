package com.shoppingpad.model;

import android.database.Cursor;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by bridgelabz on 15/3/16.
 Purpose: This class is used to be populated retrieved from JSON
 This class contains the attributes of ContentViewTbl*/
public class ContentViewModel {

    public String mAction;
    public String mNumberOfParticipants;
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

    // this method will fetch the data form the JSONObject passed in the argument and
    // populate the contentViewModelInstance
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
        }

        catch (JSONException e) {
            e.printStackTrace();
        }
    }

    // this method will fetch the data form the table row passed in the argument and
    // populate the contentViewModelInstance
    public void setContentViewModelInstance(Cursor contentViewData)
    {
        try {
            mAction = contentViewData.getString(contentViewData.getColumnIndex("action"));
            mNumberOfParticipants = contentViewData.getString(contentViewData.getColumnIndex("numberOfParticipant"));
            mNumberOfViews = contentViewData.getString(contentViewData.getColumnIndex("numberOfViews"));
            mLastViewedDateTime = contentViewData.getString(contentViewData.getColumnIndex("lastViewedDateTime"));
            mDisplayProfile = contentViewData.getString(contentViewData.getColumnIndex("displayProfile"));
            mEmail = contentViewData.getString(contentViewData.getColumnIndex("email"));
            mLastName = contentViewData.getString(contentViewData.getColumnIndex("lastName"));
            mFirstName = contentViewData.getString(contentViewData.getColumnIndex("firstName"));
            mUserId = contentViewData.getString(contentViewData.getColumnIndex("userId"));
            mContent_id = contentViewData.getString(contentViewData.getColumnIndex("contentId"));
            mUserAdminId = contentViewData.getString(contentViewData.getColumnIndex("userAdminId"));
            mUserContentId = contentViewData.getString(contentViewData.getColumnIndex("userContentId"));
        }

        catch (Exception e) {
            e.printStackTrace();
        }
    }

}
