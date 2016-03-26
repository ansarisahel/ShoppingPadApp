package com.shoppingpad.viewModelHandel;

import android.content.Context;

import com.shoppingpad.controller.ContentListController;

/**
 * Created by bridgelabz on 26/3/16.
 */
public class ViewContentHandler {

    ContentListController mContentListControllerInstance;
    public ViewContentHandler(Context context)
    {
        this.mContentListControllerInstance = new ContentListController(context);
    }

    public void getRequiredDataForViewContent(String contentId)
    {
        mContentListControllerInstance.getContentInfoDataFromTable(contentId);
    }
}
