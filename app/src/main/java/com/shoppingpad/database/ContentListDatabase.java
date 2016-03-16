package com.shoppingpad.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by bridgelabz on 13/3/16.
 * Purpose:
 * 1: This is the database class.
 * 2:This class stores the data provided by the Controller Class and
 * also gives back the data to the controller class
    */
public class ContentListDatabase extends SQLiteOpenHelper {

   // SQLiteDatabase db;
    private static final String DATABASE_NAME = "ShoppingPadDatabase";
    private static final int VERSION = 10;
    private static final String CONTENT_INFO_TABLE = "content_infoTbl";
    private static final String CONTENT_VIEW_TABLE = "user_content_viewTbl";
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

    String content_infoTbl = "CREATE Table "+CONTENT_INFO_TABLE+" " +
            "( modified_at VARCHAR(230),created_at VARCHAR(230)," +
            "syncDateTime VARCHAR(230),description VARCHAR(230)," +
            "contentLink VARCHAR(230),imagesLink VARCHAR(230)," +
            "display_name VARCHAR(230),url VARCHAR(230)," +
            "title INTEGER,contentType VARCHAR(230),content_id INTEGER);";

    String user_content_viewTbl = "CREATE TABLE "+ CONTENT_VIEW_TABLE +"" +
            "(numberOfViews INTEGER,lastViewedDateTime VARCHAR(230)," +
            "displayProfile VARCHAR(230),email VARCHAR(230)," +
            "mobile VARCHAR(230),lastName VARCHAR(230)," +
            "firstName VARCHAR(230),userId INTEGER," +
            "content_id INTEGER,userAdminId INTEGER," +
            "userContentId INTEGER);";


    public ContentListDatabase(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
      //  db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(content_list_view_tbl);
        db.execSQL(content_infoTbl);
        db.execSQL(user_content_viewTbl);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + CONTENT_LIST_VIEW_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + CONTENT_INFO_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + CONTENT_VIEW_TABLE);
        onCreate(db);
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

    public int insert_into_user_content_viewTbl(int numberOfViews, String lastViewedDateTime,
                          String displayProfile, String email,String mobile,
                          String lastName,String firstName,int userId,
                          int content_id,int userAdminId,int userContentId) {
        int count = 0;
        SQLiteDatabase db = getWritableDatabase();
        for(int i = 0; i < 5; i++) {
            ContentValues values = new ContentValues();
            values.put("numberOfViews", numberOfViews);
            values.put("lastViewedDateTime", lastViewedDateTime);
            values.put("displayProfile", displayProfile);
            values.put("email", email);
            values.put("mobile", mobile);
            values.put("lastName", lastName);
            values.put("firstName", firstName);
            values.put("userId", userId);
            values.put("content_id", content_id);
            values.put("userAdminId", userAdminId);
            values.put("userContentId", userContentId);

            if(db.insert(CONTENT_VIEW_TABLE, null, values) > 0)
                count++;
        }
        return count;
    }



    public int insert_into_content_infoTbl(String modified_at, String created_at,
                                           String syncDateTime, String description,
                                           String contentLink, String imagesLink,
                                           String display_name,String url,
                                           int title,String contentType,
                                           int content_id) {
        int count = 0;
        SQLiteDatabase db = getWritableDatabase();
        for(int i = 0; i < 5; i++) {
            ContentValues values = new ContentValues();
            values.put("modified_at", modified_at);
            values.put("created_at", created_at);
            values.put("syncDateTime", syncDateTime);
            values.put("description", description);
            values.put("contentLink", contentLink);
            values.put("imagesLink", imagesLink);
            values.put("display_name", display_name);
            values.put("url", url);
            values.put("title", title);
            values.put("contentType", contentType);
            values.put("content_id", content_id);

            if(db.insert(CONTENT_INFO_TABLE, null, values) > 0)
                count++;
        }
        return count;
    }

    public Cursor getData()
    {
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery("SELECT * FROM "+CONTENT_LIST_VIEW_TABLE,null);
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