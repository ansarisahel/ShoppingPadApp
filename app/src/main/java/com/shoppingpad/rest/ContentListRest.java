package com.shoppingpad.rest;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.shoppingpad.zip.ZipUtility;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.Buffer;
import java.nio.ByteBuffer;

/**
 * Created by bridgelabz on 13/3/16.
 *
 * purpose:
 * 1:This is the REST class which takes data from the server and then give it
 * back to the Controller.
 */
public class ContentListRest {

    Context context;
    public ContentListRest(Context context) {
        this.context = context;
    }

    // This method will return the ContentInfoJSON which is then stored in the
    // mContentInfoData variable and passed it into the Controller
    public String getContentInfoDataFromREST()
    {
        InputStream contentInfoDataStream;
        StringBuffer ContentInfoDataBuffer = new StringBuffer();
        String data;
        String contentInfoDataJSON = null;
        try {
            URL contentInfoDataUrl = new URL("http://54.86.64.100:3000/api/v1/content/content-info");
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

    // This method will return the ContentViewJSON which is then stored in the
    // mContentViewData variable and passed it into the Controller
    public String getContentViewDataFromREST()
    {
        InputStream contentViewDataStream;
        StringBuffer contentViewDataBuffer = new StringBuffer();
        String data;
        String contentViewDataJSON = null;
        try {
            URL contentViewDataUrl = new URL("http://54.86.64.100:3000/api/v1/content/user-content-view");
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

    // this method will download the zip file passed in the url and returns the path where
    // the images have been downloaded.
    public void downloadZip(String zipUrl,String zipTargetLocation)
    {
        new ZipUtility().getZipFile(zipUrl,zipTargetLocation);
    }
}
