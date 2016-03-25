package com.shoppingpad.controller;

import android.content.Context;

import com.shoppingpad.database.ContentListDatabase;
import com.shoppingpad.model.ContentInfoModel;
import com.shoppingpad.model.ContentViewModel1;
import com.shoppingpad.rest.ContentListRest;
import com.shoppingpad.viewModelHandel.ContentViewModel;

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
    private List<ContentViewModel> mContentListControllerList;
    private List<ContentInfoModel> mContentInfoModelList;
    private List<ContentViewModel1> mContentViewModelList;
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


    // Returns the list containing the ContentViewModel object with it's
    // all field initialized
    public List<ContentViewModel1> getContentViewData() {
        List<ContentViewModel1> contentViewModelList = new ArrayList<>();

        String contentViewData = mContentListRestInstance.getContentViewDataFromREST();
        try {
            JSONArray jsonArray = new JSONArray(contentViewData);
            for(int i = 0; i < jsonArray.length(); i++)
            {
                ContentViewModel1 contentViewModelInstance = new ContentViewModel1();
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

    // Returns the ContentViewModel Object stored in the list at a given position
    public ContentViewModel1 getContentViewDataFromList(int position)
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
    private List<ContentViewModel> controllerDummyData() {
        List<ContentViewModel> contentListControllerList = new ArrayList<>();

        for (int i = 0; i < 5; i++)
        {
            ContentViewModel contentViewModelInstance = new ContentViewModel();
            contentViewModelInstance.mTitle = "shahruk khan";
            //contentViewModelInstance.mImage = R.drawable.shahruk_khan;
            contentViewModelInstance.mLastSeen = "11:00 AM";
            contentViewModelInstance.mNoOfParticipants = "1000";
            contentViewModelInstance.mNoOfViews = "2000";
            contentListControllerList.add(contentViewModelInstance);
        }
        return contentListControllerList;
    }

}
