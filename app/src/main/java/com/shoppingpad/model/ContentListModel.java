package com.shoppingpad.model;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.widget.Toast;

import com.shoppingpad.R;
import com.shoppingpad.database.ContentListDatabase;
import com.shoppingpad.viewmodelHandel.ContentViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bridgelabz on 13/3/16.
 *
 * Purpose:
 * 1: This is model class which will store the data passed by the controller
 * and also pass the data back to the controller
 */
public class ContentListModel {
    public List<ContentViewModel> mContentViewModelList;
    private static final boolean UNIT_TEST = false;
    ContentListDatabase mDatabase;
    int count;
    Context context;
    public ContentListModel(Context context) {
        this.context = context;
        if (UNIT_TEST)
        {
            mContentViewModelList = modelDummyData();
        }
        else
        {
            mDatabase = new ContentListDatabase(context);
            if(false) {
                mDatabase.addRecord();

                count = mDatabase.insert_into_content_infoTbl("10 AM", "dasd", "dada" +
                        "dadas", "dasds", "dada", "dasdas", "dadaas", "dasdas", 564, "dasdas", 543);
                Toast.makeText(context, "" + count + " rows inserted", Toast.LENGTH_SHORT).show();
                count = 0;
                count = mDatabase.insert_into_user_content_viewTbl(5435, "dadas", "adasdas", "dasdas" +
                        "dadas", "dghdhd", "dasdfa", "gdsfdas", 152, 546, 9856, 5412);
                Toast.makeText(context, "" + count + " rows inserted", Toast.LENGTH_SHORT).show();
            }
            mContentViewModelList = modelDummyData();

        }
    }

    // this method is for populating dummy data into the list
    public List<ContentViewModel> modelDummyData() {
        List<ContentViewModel> contentViewModelList = new ArrayList<>();


        Cursor cursor = mDatabase.getData();
        if(cursor.getCount() == 0) {
            Log.e("Row","NO row selected");
        }
            else
            {
            while (cursor.moveToNext())
            {
                ContentViewModel contentViewModelInstance = new ContentViewModel();
                contentViewModelInstance.mTitle = cursor.getString(0);
                contentViewModelInstance.mImage = R.drawable.shahruk_khan;
                contentViewModelInstance.mLastSeen = cursor.getString(2);
                contentViewModelInstance.mStatus = cursor.getString(1);
                contentViewModelInstance.mNoOfParticipants = cursor.getString(4);
                contentViewModelInstance.mNoOfViews = Integer.parseInt(cursor.getString(3));
                contentViewModelList.add(contentViewModelInstance);
            }
        }
        return contentViewModelList;


        // creating 5 ContentViewModel instance and storing it into a mDatabase
        /*for (int i = 0; i < 5; i++)
        {
            ContentViewModel contentViewModelInstance = new ContentViewModel();
            contentViewModelInstance.mTitle = "shahruk khan";
            contentViewModelInstance.mImage = R.drawable.shahruk_khan;
            contentViewModelInstance.mLastSeen = "11:00 AM";
            contentViewModelInstance.mStatus = "opened";
            contentViewModelInstance.mNoOfParticipants = 1000;if(cursor.getCount() == 0) {
            Log.e("Row","NO row selected");
        }
            contentViewModelInstance.mNoOfViews = 2000;
            contentViewModelList.add(contentViewModelInstance);
        }
        return contentViewModelList;*/
    }
}

