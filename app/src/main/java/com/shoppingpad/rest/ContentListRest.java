package com.shoppingpad.rest;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by bridgelabz on 13/3/16.
 *
 * purpose:
 * 1:This is the REST class which takes data from the server and then give it
 * back to the Controller.
 */
public class ContentListRest {

    public String mContentInfoData;
    public String mContentViewData;
    public ContentListRest() {
        mContentInfoData = getContentInfoDataFromREST();
        mContentViewData = getContentViewDataFromREST();
    }

    public String getContentInfoDataFromREST()
    {
        InputStream contentInfoDataStream;
        StringBuffer ContentInfoDataBuffer = new StringBuffer();
        String data;
        String contentInfoDataJSON = null;
        try {
            URL contentInfoDataUrl = new URL("http://54.165.130.78:3000/api/v4/contentinfo");
            HttpURLConnection connection = (HttpURLConnection) contentInfoDataUrl.openConnection();
            contentInfoDataStream = connection.getInputStream();
            BufferedReader in =  new BufferedReader(new InputStreamReader(contentInfoDataStream));
            while ((data= in.readLine()) != null)
            {
                ContentInfoDataBuffer.append(data);
            }
            contentInfoDataJSON = ContentInfoDataBuffer.toString();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contentInfoDataJSON;
    }


    public String getContentViewDataFromREST()
    {
        InputStream contentViewDataStream;
        StringBuffer contentViewDataBuffer = new StringBuffer();
        String data;
        String contentViewDataJSON = null;
        try {
            URL contentViewDataUrl = new URL("http://54.165.130.78:3000/api/v4/usercontentview");
            HttpURLConnection connection = (HttpURLConnection) contentViewDataUrl.openConnection();
            contentViewDataStream = connection.getInputStream();
            BufferedReader in =  new BufferedReader(new InputStreamReader(contentViewDataStream));
            while ((data= in.readLine()) != null)
            {
                contentViewDataBuffer.append(data);
            }
            contentViewDataJSON = contentViewDataBuffer.toString();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contentViewDataJSON;
    }

}
