package com.shoppingpad.viewmodelHandel;

import android.content.Context;

import com.shoppingpad.R;
import com.shoppingpad.controller.ContentListController;
import com.shoppingpad.model.ContentInfoModel;
import com.shoppingpad.model.ContentViewModel1;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bridgelabz on 13/3/16.
 *
 * Purpose:
  1:This class takes input from view class then passes it to the controller
    for further processing i.e for getting the data for the view.
  2:when the controller returns the data it will give the data back
    to the view. */


//
public class ContentListViewModel {

    private static final boolean UNIT_TEST = false;
    public List<ContentViewModel> mContentListViewList;
    ContentListController mContentListControllerInstance;


    public ContentListViewModel(Context context)
    {
        mContentListControllerInstance = new ContentListController(context);
        if(UNIT_TEST)
            mContentListViewList = viewModelDummyData();
        else
            mContentListViewList = getRequiredDataForAdapter();
    }

    public List<ContentViewModel> getRequiredDataForAdapter()
    {
        List<ContentViewModel> requiredList = new ArrayList();
        for(int i = 0; i <  mContentListControllerInstance.mContentInfoModelList.size(); i++)
        {
            ContentViewModel contentViewModelInstance = new ContentViewModel();
            ContentInfoModel contentInfoModel= mContentListControllerInstance.
                                                        mContentInfoModelList.get(i);
            ContentViewModel1 contentViewModel1 = mContentListControllerInstance.
                                                        mContentViewModelList.get(i);
            contentViewModelInstance.mImage = R.drawable.shahruk_khan;
            contentViewModelInstance.mTitle = contentInfoModel.mDisplay_name;
            contentViewModelInstance.mNoOfViews = contentViewModel1.mNumberOfViews;
            contentViewModelInstance.mLastSeen = contentViewModel1.mLastViewedDateTime;
            contentViewModelInstance.mNoOfParticipants = Integer.toString(contentInfoModel.mContentId);
            contentViewModelInstance.mStatus = contentViewModel1.mFirstName;
            requiredList.add(contentViewModelInstance);
        }
        return requiredList;
    }



    // this method is for populating dummy data into the list
    private List<ContentViewModel> viewModelDummyData() {
        List<ContentViewModel> contentViewModelList = new ArrayList<>();
        // creating 5 ContentViewModel instance and storing it into a database
        for (int i = 0; i < 5; i++)
        {
            ContentViewModel contentViewModelInstance = new ContentViewModel();
            contentViewModelInstance.mTitle = "shahruk khan";
            contentViewModelInstance.mImage = R.drawable.shahruk_khan;
            contentViewModelInstance.mLastSeen = "11:00 AM";
            contentViewModelInstance.mNoOfParticipants = "1000";
            contentViewModelInstance.mNoOfViews = 2000;
            contentViewModelList.add(contentViewModelInstance);
        }
        return contentViewModelList;
    }

    public ContentViewModel getdata(int position) {
        return mContentListViewList.get(position);
    }

    public int getListSize()
    {
       return mContentListViewList.size();
    }
}
