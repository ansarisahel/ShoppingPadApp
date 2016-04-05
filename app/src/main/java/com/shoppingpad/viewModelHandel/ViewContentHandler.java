package com.shoppingpad.viewModelHandel;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Environment;

import com.shoppingpad.R;
import com.shoppingpad.controller.ContentListController;
import com.shoppingpad.model.ContentInfoModel;
import com.shoppingpad.model.ContentViewModel;
import com.shoppingpad.view.ViewContent;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

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
        ContentViewModel contentViewModelInstance = mContentListControllerInstance.getContentViewDataFromTable(contentId);
        ContentInfoModel contentInfoModelInstance = mContentListControllerInstance.getContentInfoDataFromTable(contentId);
        viewContentViewModelInstance.setmImage(contentViewModelInstance.mDisplayProfile);
        viewContentViewModelInstance.setmNoOfParticipants( contentViewModelInstance.mNumberOfParticipants + " participants");
        viewContentViewModelInstance.setmNoOfViews(contentViewModelInstance.mNumberOfViews + " views");
        viewContentViewModelInstance.setmTitle(contentViewModelInstance.mFirstName);
        viewContentViewModelInstance.setmSvgImage1(contentInfoModelInstance.mSvgImage1);
        viewContentViewModelInstance.setmSvgImage2(contentInfoModelInstance.mSvgImage2);
        viewContentViewModelInstance.setmPngImage1(contentInfoModelInstance.mPngImage1);
        viewContentViewModelInstance.setmPngImage2(contentInfoModelInstance.mPngImage2);

        return viewContentViewModelInstance;
    }

}
