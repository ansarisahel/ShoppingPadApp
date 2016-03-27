package com.shoppingpad.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.shoppingpad.controller.ContentListController;
import com.shoppingpad.model.ContentInfoModel;
import com.shoppingpad.model.ContentViewModel;

/**
 * Created by bridgelabz on 13/3/16.
 * Purpose:
 * 1: This is the database class.
 * 2:This class stores the data provided by the Controller Class and
 * also gives back the data to the controller class
    */
public class ContentListDatabase extends SQLiteOpenHelper {

    Context context;
    private ContentListController mContentListController;
    private static final String DATABASE_NAME = "ShoppingPadDatabase";
    private static final int VERSION = 13;
    private static final String CONTENT_INFO_TABLE = "content_infoTbl";
    private static final String CONTENT_VIEW_TABLE = "content_viewTbl";
    private static final String CONTENT_LIST_VIEW_TABLE = "content_list_view_tbl";



    ////////////////// this is the table for sustaining output /////////////
    public static final String TITLE="Title";
    public static final String STATUS="Status";
    public static final String VIEWS="Views";
    public static final String PARTICIPANT="Participants";
    public static final String TIME="Time";

    public static final String content_list_view_tbl="CREATE TABLE "+CONTENT_LIST_VIEW_TABLE+"("+
            TITLE+" TEXT,"+STATUS+" TEXT,"+VIEWS+" TEXT,"+PARTICIPANT+" TEXT,"+
            TIME+" TEXT);";

    String content_infoTbl = "CREATE TABLE "+CONTENT_INFO_TABLE+" " +
            "(content_id VARCHAR(230),contentType VARCHAR(230)," +
            "title VARCHAR(230),url VARCHAR(230)," +
            "display_name VARCHAR(230),imagesLink VARCHAR(230)," +
            "contentLink VARCHAR(230),description VARCHAR(230)," +
            "syncDateTime VARCHAR(230),created_at VARCHAR(230)," +
            "modified_at VARCHAR(230),zip VARCHAR(230));";

    String content_viewTbl = "CREATE TABLE "+ CONTENT_VIEW_TABLE +"" +
            "(userContentId VARCHAR(230),userAdminId VARCHAR(230)," +
            "contentId VARCHAR(230),userId VARCHAR(230)," +
            "firstName VARCHAR(230),lastName VARCHAR(230)," +
            "email VARCHAR(230),displayProfile VARCHAR(230)," +
            "lastViewedDateTime VARCHAR(230),numberOfViews VARCHAR(230)," +
            "numberOfParticipant VARCHAR(230),action VARCHAR(230));";


    public ContentListDatabase(Context context, ContentListController contentListController) {
        super(context, DATABASE_NAME, null, VERSION);
        mContentListController = contentListController;
        this.context = context;
        getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(content_infoTbl);
        db.execSQL(content_viewTbl);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + CONTENT_LIST_VIEW_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + CONTENT_INFO_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + CONTENT_VIEW_TABLE);
        onCreate(db);
    }


    public Cursor getSpecificDataFromContentInfoTbl(String content_id)
    {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor= db.rawQuery(" SELECT * FROM " + CONTENT_INFO_TABLE +
                " WHERE " + "content_id" + " = " + "" + content_id + " ", null);
        return cursor;
    }

    public Cursor getSpecificDataFromContentViewTbl(String content_id)
    {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor= db.rawQuery(" SELECT * FROM " + CONTENT_VIEW_TABLE +
                " WHERE " + "contentId" + " = " + "" + content_id + " ", null);
        return cursor;
    }

    public void addRecord(){
        SQLiteDatabase db = getWritableDatabase();
        for(int i=0;i<5;i++) {
            ContentValues contentValues=new ContentValues();
            contentValues.put(TITLE, "Salman khan");
            contentValues.put(STATUS, "opened");
            contentValues.put(VIEWS, 1020);
            contentValues.put(PARTICIPANT, 2000);
            contentValues.put(TIME, "10 AM");

            db.insert(CONTENT_LIST_VIEW_TABLE, null, contentValues);
        }
    }

    // inserting data into the ContentInfoTbl
    public void insertIntoContentInfoTbl(ContentInfoModel contentInfoModel) {
        Log.e("inserting","inserting");
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("zip",contentInfoModel.mZip);
        values.put("modified_at", contentInfoModel.mModified_at);
        values.put("created_at", contentInfoModel.mCreated_at);
        values.put("syncDateTime", contentInfoModel.mSyncDateTime);
        values.put("description", contentInfoModel.mDescription);
        values.put("contentLink", contentInfoModel.mContentLink);
        values.put("imagesLink", contentInfoModel.mImagesLink);
        values.put("display_name", contentInfoModel.mDisplay_name);
        values.put("url", contentInfoModel.mUrl);
        values.put("title", contentInfoModel.mTitle);
        values.put("contentType", contentInfoModel.mContentType);
        values.put("content_id", contentInfoModel.mContentId);

        db.insert(CONTENT_INFO_TABLE, null, values);
    }


    // Inserting data into the ContentViewTbl
    public void insertIntoContentViewTbl(ContentViewModel contentViewModel) {
        Log.e("inserting","inserting");
        SQLiteDatabase db = getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("action",contentViewModel.mAction);
            values.put("numberOfParticipant",contentViewModel.mNumberOfParticipants);
            values.put("numberOfViews", contentViewModel.mNumberOfViews);
            values.put("lastViewedDateTime",contentViewModel.mLastViewedDateTime);
            values.put("displayProfile", contentViewModel.mDisplayProfile);
            values.put("email", contentViewModel.mEmail);
            values.put("lastName", contentViewModel.mLastName);
            values.put("firstName", contentViewModel.mFirstName);
            values.put("userId", contentViewModel.mUserId);
            values.put("contentId", contentViewModel.mContent_id);
            values.put("userAdminId", contentViewModel.mUserAdminId);
            values.put("userContentId", contentViewModel.mUserContentId);

            db.insert(CONTENT_VIEW_TABLE, null, values);
    }


    // getting all the data from ContentInfoTable data
    public Cursor getContentInfoData()
    {
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery("SELECT * FROM "+CONTENT_INFO_TABLE, null);
    }


    // getting all the data from ContentViewTable data
    public Cursor getContentViewData()
    {
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery("SELECT * FROM "+ CONTENT_VIEW_TABLE,null);
    }


}