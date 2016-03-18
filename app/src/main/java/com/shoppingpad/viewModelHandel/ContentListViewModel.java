package com.shoppingpad.viewModelHandel;

import android.content.Context;
import android.graphics.BitmapFactory;

import com.shoppingpad.controller.ContentListController;
import com.shoppingpad.model.ContentInfoModel;
import com.shoppingpad.model.ContentViewModel1;

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
    public List<ContentViewModel> mContentListViewList;
    ContentListController mContentListControllerInstance;


    public ContentListViewModel(Context context)
    {
        // calling the ContentListController
        mContentListControllerInstance = new ContentListController(context);
        if(UNIT_TEST)
            mContentListViewList = viewModelDummyData();
        else
        // This will get the list containing all the data required for
        // populating the recyclerview
            mContentListViewList = getRequiredDataForAdapter();
    }

    // This method returns the list containing all the data required for
    // populating the recyclerview
    public List<ContentViewModel> getRequiredDataForAdapter()
    {
        List<ContentViewModel> requiredList = new ArrayList();
        URL url = null;
        for(int i = 0; i <  mContentListControllerInstance.mContentInfoModelList.size(); i++)
        {

            ContentViewModel contentViewModelInstance = new ContentViewModel();
            ContentInfoModel contentInfoModel= mContentListControllerInstance.
                                                        mContentInfoModelList.get(i);
            ContentViewModel1 contentViewModel1 = mContentListControllerInstance.
                                                        mContentViewModelList.get(i);
            try {
                url = new URL("https://hilleletv.files.wordpress.com/2015/11/shahrukhkhan-jan30.jpg");
                contentViewModelInstance.setmImage(url.toString());
                contentViewModelInstance.setmTitle(contentInfoModel.mDisplay_name);
                contentViewModelInstance.setmNoOfViews(contentViewModel1.mNumberOfViews);
                contentViewModelInstance.setmLastSeen(contentViewModel1.mLastViewedDateTime);
                contentViewModelInstance.setmNoOfParticipants(contentInfoModel.mContentId);
                contentViewModelInstance.setmStatus(contentViewModel1.mFirstName);
                requiredList.add(contentViewModelInstance);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
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
           // contentViewModelInstance.mImage = R.drawable.shahruk_khan;
            contentViewModelInstance.mLastSeen = "11:00 AM";
            contentViewModelInstance.mNoOfParticipants = "1000";
            contentViewModelInstance.mNoOfViews = "2000";
            contentViewModelList.add(contentViewModelInstance);
        }
        return contentViewModelList;
    }

    // this will give the ContentViewModel object stored at a specified possition
    // in the list.
    public ContentViewModel getdata(int position) {
        return mContentListViewList.get(position);
    }

    // This will return the list size of the mContentListViewList
    public int getListSize()
    {
       return mContentListViewList.size();
    }
}
