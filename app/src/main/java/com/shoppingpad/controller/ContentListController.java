package com.shoppingpad.controller;

import android.content.Context;
import android.database.Cursor;

import com.shoppingpad.R;
import com.shoppingpad.database.ContentListDatabase;
import com.shoppingpad.model.ContentInfoModel;
import com.shoppingpad.model.ContentListModel;
import com.shoppingpad.model.ContentViewModel1;
import com.shoppingpad.rest.ContentListRest;
import com.shoppingpad.viewmodelHandel.ContentViewModel;

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
    public List<ContentViewModel> mContentListControllerList;
    public List<ContentInfoModel> mContentInfoModelList;
    public List<ContentViewModel1> mContentViewModelList;
    ContentListModel mContentListModelInstance;
    ContentListDatabase mDatabase;
    ContentListRest mContentListRestInstance;

    public ContentListController(Context context) {
        mContentListModelInstance = new ContentListModel(context);
        if (UNIT_TEST)
            mContentListControllerList = controllerDummyData();
        else {
            mDatabase = new ContentListDatabase(context);
            mContentListRestInstance = new ContentListRest();
            mContentInfoModelList = getContentInfoData();
            mContentViewModelList = getContentViewData();
        }
    }


    // get ContentInfoTbl data and populate it into the ContentInfoModel
    private List<ContentInfoModel> getContentInfoData() {
        List<ContentInfoModel> contentInfoModelList = new ArrayList<>();
       /* Cursor contentInfoData = database.getContentInfoData();
        if(contentInfoData.getCount() != 0)
        {
            while (contentInfoData.moveToNext())
            {
                ContentInfoModel contentInfoModelInstance = new ContentInfoModel();
                contentInfoModelInstance.setContentInfoModelInstance(contentInfoData);

                contentInfoModelList.add(contentInfoModelInstance);

            }
        }*/

        String contentInfoData = mContentListRestInstance.mContentInfoData;
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


    // get ContentViewTbl data and populate it into the ContentViewModel
    private List<ContentViewModel1> getContentViewData() {
        List<ContentViewModel1> contentViewModelList = new ArrayList<>();
       /* Cursor contentViewData = database.getContentViewData();
        if (contentViewData.getCount() != 0)
        {
            while (contentViewData.moveToNext())
            {
                ContentViewModel1 contentViewModelInstance = new ContentViewModel1();
                contentViewModelInstance.setContentViewModelInstance(contentViewData);

                contentViewModelList.add(contentViewModelInstance);
            }
        }*/

        String contentViewData = mContentListRestInstance.mContentViewData;
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
            contentViewModelInstance.mNoOfViews = 2000;
            contentListControllerList.add(contentViewModelInstance);
        }
        return contentListControllerList;
    }
}
