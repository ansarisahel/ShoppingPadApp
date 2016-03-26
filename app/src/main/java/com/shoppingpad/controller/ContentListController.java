package com.shoppingpad.controller;

import android.content.Context;

import com.shoppingpad.database.ContentListDatabase;
import com.shoppingpad.model.ContentInfoModel;
import com.shoppingpad.model.ContentViewModel;
import com.shoppingpad.rest.ContentListRest;
import com.shoppingpad.viewModelHandel.ContentModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bridgelabz on 13/3/16.
 * Purpose:
 * 1: This class will get called from view Model
 * 2: This class is responsible for getting the data for the view model
 * 3: This class will fetch the data either from REST or database.
 * 4: This class will check for the internet connection.
 * 5: If the device is connected to the internet then It will call the REST service
 *    to fetch the data from the server and updatate the database from the latest data
 * 6: If the device is not connected to the internet the it willdirectly fetch the
 *    data from Database.
*/

public class ContentListController {
    private static final boolean UNIT_TEST = false;
    private List<ContentModel> mContentListControllerList;
    private List<ContentInfoModel> mContentInfoModelList;
    private List<ContentViewModel> mContentViewModelList;
    ContentListDatabase mDatabase;
    ContentListRest mContentListRestInstance;

    public ContentListController(Context context) {
        if (UNIT_TEST)
            mContentListControllerList = controllerDummyData();
        else {
            // calling to the database
            mDatabase = new ContentListDatabase(context,ContentListController.this);

            // calling to the REST service to get the JSONDATA
            mContentListRestInstance = new ContentListRest();
        }
    }

    // Returns the list containing the ContentInfoModel object with it's
    // all field initialized
    public List<ContentInfoModel> getContentInfoData() {
        List<ContentInfoModel> contentInfoModelList = new ArrayList<>();

        String contentInfoData = mContentListRestInstance.getContentInfoDataFromREST();
        try {
            JSONArray jsonArray = new JSONArray(contentInfoData);
            for(int i = 0; i < jsonArray.length(); i++)
            {
                ContentInfoModel contentInfoModelInstance = new ContentInfoModel();
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                contentInfoModelInstance.setContentInfoModelInstance(jsonObject);
                contentInfoModelList.add(contentInfoModelInstance);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return contentInfoModelList;
    }


    // Returns the list containing the ContentModel object with it's
    // all field initialized
    public List<ContentViewModel> getContentViewData() {
        List<ContentViewModel> contentViewModelList = new ArrayList<>();

        String contentViewData = mContentListRestInstance.getContentViewDataFromREST();
        try {
            JSONArray jsonArray = new JSONArray(contentViewData);
            for(int i = 0; i < jsonArray.length(); i++)
            {
                ContentViewModel contentViewModelInstance = new ContentViewModel();
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                contentViewModelInstance.setContentViewModelInstance(jsonObject);
                contentViewModelList.add(contentViewModelInstance);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return  contentViewModelList;
    }


    // Returns the size of the mContentInfoModelList
    public int getContentInfoListSize()
    {
        return mContentInfoModelList.size();
    }

    // Returns the size of the mContentViewModelList
    public int getContentViewListSize()
    {
        return mContentViewModelList.size();
    }

    // Returns the ContentInfoModel Object stored in the list at a given position
    public ContentInfoModel getContentInfoDataFromList(int position)
    {
        return mContentInfoModelList.get(position);
    }

    // Returns the ContentModel Object stored in the list at a given position
    public ContentViewModel getContentViewDataFromList(int position)
    {
        return mContentViewModelList.get(position);
    }

    // inserting data into ContenteInfoTbl
    public void insertContentInfoData()
    {
        for(int i = 0; i < mContentInfoModelList.size(); i++)
        mDatabase.insertIntoContentInfoTbl(mContentInfoModelList.get(i));
    }

    // Inserting data into ContentInfoTbl
    public void insertContentViewData() {
        for (int i = 0; i < mContentViewModelList.size(); i++) {
            mDatabase.insertIntoContentViewTbl(mContentViewModelList.get(i));
        }
    }

    // this method is for populating dummy data into the list
    private List<ContentModel> controllerDummyData() {
        List<ContentModel> contentListControllerList = new ArrayList<>();

        for (int i = 0; i < 5; i++)
        {
            ContentModel contentModelInstance = new ContentModel();
            contentModelInstance.mTitle = "shahruk khan";
            //contentModelInstance.mImage = R.drawable.shahruk_khan;
            contentModelInstance.mLastSeen = "11:00 AM";
            contentModelInstance.mNoOfParticipants = "1000";
            contentModelInstance.mNoOfViews = "2000";
            contentListControllerList.add(contentModelInstance);
        }
        return contentListControllerList;
    }

}
