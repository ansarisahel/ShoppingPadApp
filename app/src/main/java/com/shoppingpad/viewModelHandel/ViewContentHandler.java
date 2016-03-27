package com.shoppingpad.viewModelHandel;

import android.content.Context;
import android.databinding.DataBindingUtil;

import com.shoppingpad.R;
import com.shoppingpad.controller.ContentListController;
import com.shoppingpad.model.ContentInfoModel;
import com.shoppingpad.model.ContentViewModel;
import com.shoppingpad.view.ViewContent;

/**
 * Created by bridgelabz on 26/3/16.
 */
public class ViewContentHandler {

    ContentListController mContentListControllerInstance;
    ViewContent context;
    public ViewContentHandler(ViewContent context)
    {
        this.mContentListControllerInstance = new ContentListController(context);
        this.context = context;
    }


    // This method returns all the data required for populating the ViewContent
    public ViewContentViewModel getRequiredDataForViewContent(String contentId)
    {
        ViewContentViewModel viewContentViewModelInstance = new ViewContentViewModel();
        ContentInfoModel contentInfoModelInstance = mContentListControllerInstance.getContentInfoDataFromTable(contentId);
        ContentViewModel contentViewModelInstance = mContentListControllerInstance.getContentViewDataFromTable(contentId);
        viewContentViewModelInstance.setmImage(R.drawable.shahruk);
        viewContentViewModelInstance.setmNoOfParticipants( contentViewModelInstance.mNumberOfParticipants);
        viewContentViewModelInstance.setmNoOfViews(contentViewModelInstance.mNumberOfViews);
        viewContentViewModelInstance.setmTitle(contentInfoModelInstance.mTitle);
        return viewContentViewModelInstance;
    }
}
