package com.shoppingpad.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.google.repacked.kotlin.properties.NULL_VALUE;
import com.shoppingpad.controller.ContentListController;
import com.shoppingpad.model.ContentInfoModel;
import com.shoppingpad.model.ContentViewModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

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
    private static final int VERSION = 18;
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
    public void insertIntoContentInfoTbl(JSONObject contentInfoData) {
        Log.e("sertIntoContentInfoTbl","inserting");
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        try {
            values.put("zip",contentInfoData.getString("zip"));
            values.put("modified_at", contentInfoData.getString("modified_at"));
            values.put("created_at", contentInfoData.getString("created_at"));
            values.put("syncDateTime", contentInfoData.getString("syncDateTime"));
            values.put("description", contentInfoData.getString("decription"));
            values.put("contentLink", contentInfoData.getString("contentLink"));
            values.put("imagesLink", contentInfoData.getString("imagesLink"));
            values.put("display_name", contentInfoData.getString("display_name"));
            values.put("url", contentInfoData.getString("url"));
            values.put("title", contentInfoData.getString("title"));
            values.put("contentType", contentInfoData.getString("contentType"));
            values.put("content_id", contentInfoData.getString("content_id"));
            db.insert(CONTENT_INFO_TABLE, null, values);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    // Inserting data into the ContentViewTbl
    public void insertIntoContentViewTbl(JSONObject contentViewData) {
        ContentValues values123 = new ContentValues();
        SQLiteDatabase db = getWritableDatabase();
        try {
            values123.put("action",contentViewData.getString("action"));
            values123.put("numberOfParticipant",contentViewData.getString("numberofparticipant"));
            values123.put("numberOfViews", contentViewData.getString("numberOfViews"));
            values123.put("lastViewedDateTime",contentViewData.getString("lastViewedDateTime"));
            values123.put("displayProfile", contentViewData.getString("displayProfile"));
            values123.put("email", contentViewData.getString("email"));
            values123.put("lastName", contentViewData.getString("lastName"));
            values123.put("firstName", contentViewData.getString("firstName"));
            values123.put("userId", contentViewData.getString("userId"));
            values123.put("contentId", contentViewData.getString("contentId"));
            values123.put("userAdminId", contentViewData.getString("userAdminId"));
            values123.put("userContentId", contentViewData.getString("userContentId"));
            db.insert(CONTENT_VIEW_TABLE, null, values123);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    // getting all the data from ContentInfoTable data
    public Cursor getContentInfoDataFromTbl()
    {
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery("SELECT * FROM "+CONTENT_INFO_TABLE, null);
    }


    // getting all the data from ContentViewTable data
    public Cursor getContentViewDataFromTbl()
    {
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery("SELECT * FROM "+ CONTENT_VIEW_TABLE,null);
    }


    // checking if the SyncDateTime entry in the database is same or not as SyncDateTime
    // retrieved from the REST
    public boolean checkSyncDateTimeEntry(JSONObject tableRow)
    {
        String title = null;
        SQLiteDatabase db = getReadableDatabase();
        try {
            title = tableRow.getString("title");
        } catch (JSONException e) {
            e.printStackTrace();
        }
            Cursor cursor= db.rawQuery("SELECT * FROM " + CONTENT_INFO_TABLE +
                    " WHERE " + "title ="+title, null);

            if (cursor != null)
                return true;
            else
                return false;
    }

    // update record in the contentInfoTbl
    public void updateContentInfoTblEntry(JSONObject contentInfoData)
    {
        try {
            String contentId = contentInfoData.getString("content_id");
            SQLiteDatabase db = getWritableDatabase();
            ContentValues values = new ContentValues();

            values.put("zip",contentInfoData.getString("zip"));
            values.put("modified_at", contentInfoData.getString("modified_at"));
            values.put("created_at", contentInfoData.getString("created_at"));
            values.put("syncDateTime", contentInfoData.getString("syncDateTime"));
            values.put("description", contentInfoData.getString("decription"));
            values.put("contentLink", contentInfoData.getString("contentLink"));
            values.put("imagesLink", contentInfoData.getString("imagesLink"));
            values.put("display_name", contentInfoData.getString("display_name"));
            values.put("url", contentInfoData.getString("url"));
            values.put("title", contentInfoData.getString("title"));
            values.put("contentType", contentInfoData.getString("contentType"));
            values.put("content_id", contentInfoData.getString("content_id"));
            db.update(CONTENT_INFO_TABLE,values,"content_id = ?",new String[] {contentId});
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    // update the content link so that images can be fetched through this link in sd card
    public void updateContentInfoTblEntry(String contentId,String contentLink)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("contentLink",contentLink);
        db.update(CONTENT_INFO_TABLE,values,"content_id = ?",new String[] {contentId});
    }

    public ArrayList<Cursor> getDataFromSDCardDatabase(String uri)
    {
        ArrayList<Cursor> sdCardData = new ArrayList<>();
        SQLiteDatabase db = SQLiteDatabase.openDatabase(uri,null,0);
        Cursor pageData = db.rawQuery("SELECT page_svg FROM PageData",null);
        Cursor pageMedia = db.rawQuery("SELECT media_file FROM PageMedia",null);
        sdCardData.add(pageData);
        sdCardData.add(pageMedia);
        return sdCardData;
    }
}