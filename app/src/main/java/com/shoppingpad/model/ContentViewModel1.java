package com.shoppingpad.model;

import android.database.Cursor;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by bridgelabz on 15/3/16.
 */
public class ContentViewModel1 {
    public int mNumberOfViews;
    public String mLastViewedDateTime;
    public String mDisplayProfile;
    public String mEmail;
    public String mLastName;
    public String mFirstName;
    public int mUserId;
    public int mContent_id;
    public int mUserAdminId;
    public int mUserContentId;
    public int mNumberOfParticipants;
    public String mAction;


    public void setContentViewModelInstance(JSONObject contentViewData)
    {
        try {
            mAction = contentViewData.getString("action");
            mNumberOfParticipants = contentViewData.getInt("numberofparticipant");
            mNumberOfViews = contentViewData.getInt("numberOfViews");
            mLastViewedDateTime = contentViewData.getString("lastViewedDateTime");
            mDisplayProfile = contentViewData.getString("displayProfile");
            mEmail = contentViewData.getString("email");
            mLastName = contentViewData.getString("lastName");
            mFirstName = contentViewData.getString("firstName");
            mUserId = contentViewData.getInt("userId");
            mContent_id = contentViewData.getInt("contentId");
            mUserAdminId = contentViewData.getInt("userAdminId");
            mUserContentId = contentViewData.getInt("userContentId");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
