package com.shoppingpad.viewModelHandel;

import android.content.Context;

import com.shoppingpad.R;
import com.shoppingpad.controller.ContentListController;
import com.shoppingpad.model.ContentInfoModel;
import com.shoppingpad.model.ContentViewModel;

/**
 * Created by bridgelabz on 26/3/16.
 */
public class ViewContentHandler {

    ContentListController mContentListControllerInstance;
    public ViewContentHandler(Context context)
    {
        this.mContentListControllerInstance = new ContentListController(context);
    }


    // This method returns all the data required for populating the ViewContent
    public ViewContentViewModel getRequiredDataForViewContent(String contentId)
    {
        ViewContentViewModel viewContentViewModelInstance = new ViewContentViewModel();
        ContentInfoModel contentInfoModelInstance = mContentListControllerInstance.getContentInfoDataFromTable(contentId);
        ContentViewModel contentViewModelInstance = mContentListControllerInstance.getContentViewDataFromTable(contentId);
        viewContentViewModelInstance.mImage = R.drawable.shahruk;
        viewContentViewModelInstance.mNoOfParticipants = contentViewModelInstance.mNumberOfParticipants;
        viewContentViewModelInstance.mNoOfViews = contentViewModelInstance.mNumberOfViews;
        viewContentViewModelInstance.mTitle = contentInfoModelInstance.mTitle;
        return viewContentViewModelInstance;
    }
}
