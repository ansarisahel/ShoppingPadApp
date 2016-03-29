package com.shoppingpad.zip;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * Created by bridgelabz on 29/3/16.
 */

// this class is used for handling zip operation
public class ZipUtility {

    // unzipping the file
    public boolean unZip(String inputZipFile, String destinationDirectory)
    {
        try {
            int BUFFER = 2048;
            List<String> zipFiles = new ArrayList<String>();
            File sourceZipFile = new File(inputZipFile);
            File unzipDestinationDirectory = new File(destinationDirectory);
            unzipDestinationDirectory.mkdir();
            ZipFile zipFile;
            zipFile = new ZipFile(sourceZipFile, ZipFile.OPEN_READ);
            Enumeration zipFileEntries = zipFile.entries();
            while (zipFileEntries.hasMoreElements()) {
                ZipEntry entry = (ZipEntry) zipFileEntries.nextElement();
                String currentEntry = entry.getName();
                File destFile = new File(unzipDestinationDirectory, currentEntry);
                if (currentEntry.endsWith(".zip")) {
                    zipFiles.add(destFile.getAbsolutePath());
                }

                File destinationParent = destFile.getParentFile();

                destinationParent.mkdirs();

                try {
                    if (!entry.isDirectory()) {
                        BufferedInputStream is =
                                new BufferedInputStream(zipFile.getInputStream(entry));
                        int currentByte;
                        byte data[] = new byte[BUFFER];

                        FileOutputStream fos = new FileOutputStream(destFile);
                        BufferedOutputStream dest =
                                new BufferedOutputStream(fos, BUFFER);
                        while ((currentByte = is.read(data, 0, BUFFER)) != -1) {
                            dest.write(data, 0, currentByte);
                        }
                        dest.flush();
                        dest.close();
                        is.close();
                    }
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
            zipFile.close();

            for (Iterator<String> iter = zipFiles.iterator(); iter.hasNext();) {
                String zipName = (String)iter.next();
                unZip(
                        zipName,
                        destinationDirectory +
                                File.separatorChar +
                                zipName.substring(0,zipName.lastIndexOf(".zip"))
                );
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false ;
        }
        Log.e("File has been extracted","File has been extracted");
        return true;
    }
}
