package com.shoppingpad.controller;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;

import com.shoppingpad.database.ContentListDatabase;
import com.shoppingpad.model.ContentInfoModel;
import com.shoppingpad.model.ContentViewModel;
import com.shoppingpad.rest.ContentListRest;
import com.shoppingpad.viewModelHandel.ContentModel;
import com.shoppingpad.zip.ZipUtility;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
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
    Context context;

    public ContentListController(Context context) {
        this.context = context;
        if (UNIT_TEST)
            mContentListControllerList = controllerDummyData();
        else {
            // calling to the database
            mDatabase = new ContentListDatabase(context,ContentListController.this);

            // calling to the REST service to get the JSONDATA
            mContentListRestInstance = new ContentListRest(context);
        }
    }

    // Returns the list containing the ContentInfoModel object with it's
    // all field initialized
    public List<ContentInfoModel> getContentInfoData() {
        List<ContentInfoModel> contentInfoModelList = new ArrayList<>();

        // if Internet connection is available
        if(internetConnection()) {

            // get ContentInfoData from REST
            String contentInfoData = mContentListRestInstance.getContentInfoDataFromREST();

            if (contentInfoData != null) {

                try {
                    JSONArray jsonArray = new JSONArray(contentInfoData);

                    // get the contentInfoTableData
                    Cursor checkIfTableIsNull = mDatabase.getContentInfoDataFromTbl();

                    // check if the contentInfoTable has entry
                    if (checkIfTableIsNull.getCount() > 0) {

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            boolean entrySame = mDatabase.checkSyncDateTimeEntry(jsonObject);

                            // if data is there in the table is not same as the REST data then update the entry in the table
                            if (!entrySame)
                                mDatabase.updateContentInfoTblEntry(jsonObject);
                        }
                    }

                    // if the table has no entry then insert the REST data into the table
                    else {
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            mDatabase.insertIntoContentInfoTbl(jsonObject);
                        }
                    }

                    // retrieve data from the table and populate the Model ContentInfoModel and add in the list
                    getContentInfoDataFromTable(contentInfoModelList);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            // if the REST data is null then retrieve data from the table
            else
                getContentInfoDataFromTable(contentInfoModelList);
        }

        // if internet connection is not available then get the data from the database
        else
            getContentInfoDataFromTable(contentInfoModelList);

        return contentInfoModelList;
    }


    // Returns the list containing the ContentModel object with it's
    // all field initialized
    public List<ContentViewModel> getContentViewData() {
        List<ContentViewModel> contentViewModelList = new ArrayList<>();

        // if internet connection is available
        if(internetConnection()) {

            // get the data from the REST
            String contentViewData = mContentListRestInstance.getContentViewDataFromREST();
            if (contentViewData != null) {

                // get the data from the content info table
                Cursor cursor = mDatabase.getContentViewDataFromTbl();

                // if table is empty then insert the REST data into the table
                if(cursor.getCount() == 0) {
                    try {
                        JSONArray jsonArray = new JSONArray(contentViewData);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            mDatabase.insertIntoContentViewTbl(jsonObject);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

                // retrieve the data from the table and populate the contentViewModel and the add in the list
                getContentViewDataFromTable(contentViewModelList);
            }

            // if internet connection is not available then get the data from the database
            else
                getContentViewDataFromTable(contentViewModelList);
        }
        return  contentViewModelList;
    }


    // Retrieving particular record from the ContentInfoTbl by passing ContentId
    public ContentInfoModel getContentInfoDataFromTable(String mContentId)
    {
        ArrayList<Cursor> sdcardDB;
//        ArrayList<String> sdCardData = new ArrayList<>();
        String zipUrl = null;
        String sdCardDBUri = null;
        String zipTargetLocation;
        String zipExtractedLocation;

        // make contentInfo Model to populate data get from database
        ContentInfoModel contentInfoModelInstance = new ContentInfoModel();

        // get data from the contentInfoTbl to check whether the contentlink column of a particular
        // contentId has got proper entry or not
        Cursor dataFromContentInfoTbl = mDatabase.getSpecificDataFromContentInfoTbl(mContentId);

        // check if the data got from the contentInfoTbl is not null
        if(dataFromContentInfoTbl != null) {

            // if the data is not null moved to that data
            while (dataFromContentInfoTbl.moveToNext()) {

                // get the contentLink column data
                String contentLink = dataFromContentInfoTbl.getString(dataFromContentInfoTbl.getColumnIndex("contentLink"));

                // check if the contentLink column data is not null and and doesn't have the proper entry
                if (contentLink != null && contentLink.startsWith("http://"))
                {
                    // get the zipUrl from the table to download the zip file
                    zipUrl = dataFromContentInfoTbl.getString(dataFromContentInfoTbl.getColumnIndex("zip"));

                    // create the target location on the external SD card where the downloaded zip file will get stored
                    zipTargetLocation = Environment.getExternalStorageDirectory().getPath()+"/Zip Files/View_Content";

                    // create the location on the external SD card where the zip file will be extracted in
                    zipExtractedLocation = Environment.getExternalStorageDirectory().getPath()+"/Zip Files Extracted1/ContentId"+mContentId;

                    // if the folder where the zip file will be extracted doesn't exist then make the one
                    File file = new File(zipExtractedLocation);
                    if(!file.isDirectory())
                        file.mkdir();

                    // download the zip file and store the downloaded zip file in location passed in the parameter of this method
                    mContentListRestInstance.downloadZip(zipUrl,zipTargetLocation);

                    // extract the zip file and store the extracted zip file
                    new ZipUtility().unZip(zipTargetLocation,zipExtractedLocation);

                    // once the file is downloaded and stored on the sd card, store that path in a local database so that
                    // we can retrieve the path from the database
                    mDatabase.updateContentInfoTblEntry(mContentId,zipExtractedLocation);
                }
            }
            // after updating the path in the database, get the data from the database of a particular contentId
            Cursor updatedDatafromContentInfoTbl = mDatabase.getSpecificDataFromContentInfoTbl(mContentId);

            // if table has the data
            if(updatedDatafromContentInfoTbl.moveToFirst())
            sdCardDBUri = updatedDatafromContentInfoTbl.getString(updatedDatafromContentInfoTbl
                                                                .getColumnIndex("contentLink"))+"/Content/data/database.sqlite";

            sdcardDB = mDatabase.getDataFromSDCardDatabase(sdCardDBUri);
            Cursor svgImages = sdcardDB.get(0);
            Cursor pngImages = sdcardDB.get(1);

            // populate the contentInfoModelInstance with the data got uptil now
            contentInfoModelInstance.setContentInfoModelInstance(updatedDatafromContentInfoTbl,svgImages,pngImages);
        }
        return contentInfoModelInstance;
    }


    // Retrieving particular record from the ContentViewTbl by passing ContentId
    public ContentViewModel getContentViewDataFromTable(String mContentId)
    {
        ContentViewModel contentViewModelInstance = new ContentViewModel();
        Cursor dataFromContentViewTbl = mDatabase.getSpecificDataFromContentViewTbl(mContentId);
        if(dataFromContentViewTbl != null) {
            while (dataFromContentViewTbl.moveToNext())
                contentViewModelInstance.setContentViewModelInstance(dataFromContentViewTbl);
        }
        return contentViewModelInstance;
    }


    // retrieve all the contentInfoData from the table and populate the ContentInfoModel
    // and add it into the list
    public void getContentInfoDataFromTable(List<ContentInfoModel> contentInfoModelList)
    {
        Cursor cursor = mDatabase.getContentInfoDataFromTbl();
        while (cursor.moveToNext())
        {
            ContentInfoModel contentInfoModelInstance = new ContentInfoModel();
            contentInfoModelInstance.setContentInfoModelInstance(cursor,null,null);
            contentInfoModelList.add(contentInfoModelInstance);
        }
    }


    // retrieve all the contentViewData from the table and populate the ContentViewModel
    // and add it into the list
    public void getContentViewDataFromTable(List<ContentViewModel> contentViewModelList)
    {
        Cursor getContentViewData = mDatabase.getContentViewDataFromTbl();
        while (getContentViewData.moveToNext())
        {
            ContentViewModel contentViewModelInstance = new ContentViewModel();
            contentViewModelInstance.setContentViewModelInstance(getContentViewData);
            contentViewModelList.add(contentViewModelInstance);
        }
    }


    // checking internet connection
    private boolean internetConnection() {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Activity.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnectedOrConnecting())
            return true;
        else
            return false;
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
