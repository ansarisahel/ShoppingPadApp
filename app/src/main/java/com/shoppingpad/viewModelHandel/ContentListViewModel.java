package com.shoppingpad.viewModelHandel;

import android.content.Context;

import com.shoppingpad.controller.ContentListController;
import com.shoppingpad.model.ContentInfoModel;
import com.shoppingpad.model.ContentViewModel;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
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


public class ContentListViewModel {

    private static final boolean UNIT_TEST = false;
    private List<ContentModel> mContentListViewList;
    ContentListController mContentListControllerInstance;


    public ContentListViewModel(Context context)
    {
        if(UNIT_TEST)
            mContentListViewList = viewModelDummyData();
        else
            // calling the ContentListController
            mContentListControllerInstance = new ContentListController(context);

    }

    // This method returns the list containing all the data required for
    // populating the recyclerView
    public List<ContentModel> getRequiredDataForContentListViewAdapter()
    {
        mContentListViewList = new ArrayList<>();
        List<ContentInfoModel> contentInfoModelList = mContentListControllerInstance.getContentInfoData();
        List<ContentViewModel> contentViewModelList = mContentListControllerInstance.getContentViewData();
        URL url = null;
        for(int i = 0; i <  contentInfoModelList.size(); i++)
        {

            ContentModel contentModelInstance = new ContentModel();
            ContentInfoModel contentInfoModel= contentInfoModelList.get(i);
            ContentViewModel contentViewModel = contentViewModelList.get(i);
            try {
                URL displayProfileUrl = new URL(contentViewModel.mDisplayProfile);
                contentModelInstance.setmImage(displayProfileUrl.toString());
                contentModelInstance.setmTitle(contentViewModel.mFirstName);
                contentModelInstance.setmNoOfViews(contentViewModel.mNumberOfViews + " views");
                contentModelInstance.setmLastSeen(contentViewModel.mContent_id);
                contentModelInstance.setmNoOfParticipants(contentViewModel.mNumberOfParticipants + " participants");
                contentModelInstance.setmStatus(contentViewModel.mAction);
                mContentListViewList.add(contentModelInstance);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return mContentListViewList;
    }



    // this method is for populating dummy data into the list
    private List<ContentModel> viewModelDummyData() {
        List<ContentModel> contentModelList = new ArrayList<>();

        // creating 5 ContentModel instance and storing it into a database
        for (int i = 0; i < 5; i++)
        {
            ContentModel contentModelInstance = new ContentModel();
            contentModelInstance.mTitle = "shahruk khan";
           // contentModelInstance.mViewPagerImage = R.drawable.shahruk_khan;
            contentModelInstance.mLastSeen = "11:00 AM";
            contentModelInstance.mNoOfParticipants = "1000";
            contentModelInstance.mNoOfViews = "2000";
            contentModelList.add(contentModelInstance);
        }
        return contentModelList;
    }

    // this will give the ContentModel object stored at a specified position
    // in the list.
    public ContentModel getdata(int position) {
        return mContentListViewList.get(position);
    }

    // This will return the list size of the mContentListViewList
    public int getListSize()
    {
       return mContentListViewList.size();
    }
}
