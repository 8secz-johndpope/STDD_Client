package com.nematjon.edd_client_season_two;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;

import androidx.annotation.RequiresApi;

import java.util.HashMap;

@RequiresApi(api = Build.VERSION_CODES.Q)
public class StoredMedia {
    int total_num_of_images = 0;
    int total_num_of_video_files = 0;

    public int totalNumberOfImages(ContentResolver CR) {

        HashMap<Long, String> folders = new HashMap<>();  //hashmap to track(no duplicates) folders by using their ids

        String[] projection = {MediaStore.Images.Media._ID,
                MediaStore.Images.Media.BUCKET_ID,
                MediaStore.Images.Media.BUCKET_DISPLAY_NAME,
                MediaStore.Images.Media.DATE_ADDED};


        Uri root = MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL);

        Cursor c = CR.query(root, projection, null, null, MediaStore.Images.Media.DATE_ADDED + " desc");

        if (c != null && c.moveToFirst()) {
            int folderIdIndex = c.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_ID);
            int folderNameIndex = c.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME);

            do {
                Long folderId = c.getLong(folderIdIndex);

                if (!folders.containsKey(folderId)) { //proceed only if the folder data has not been inserted already :)
                    String folderName = c.getString(folderNameIndex);
                    folders.put(folderId, folderName);
                    total_num_of_images = c.getCount();
                }

            } while (c.moveToNext());

            c.close(); //close cursor
            folders.clear(); //clear the hashmap becuase it's no more useful
        }
        return total_num_of_images;
    }

    public int totalNumberOfVideoFiles(ContentResolver CR) {

        HashMap<Long, String> folders = new HashMap<>();  //hashmap to track(no duplicates) folders by using their ids

        String[] projection = {MediaStore.Video.Media._ID,
                MediaStore.Video.Media.BUCKET_ID,
                MediaStore.Video.Media.BUCKET_DISPLAY_NAME,
                MediaStore.Video.Media.DATE_ADDED};


        Uri root = MediaStore.Video.Media.getContentUri(MediaStore.VOLUME_EXTERNAL);

        Cursor c = CR.query(root, projection, null, null, MediaStore.Video.Media.DATE_ADDED + " desc");

        if (c != null && c.moveToFirst()) {
            int folderIdIndex = c.getColumnIndexOrThrow(MediaStore.Video.Media.BUCKET_ID);
            int folderNameIndex = c.getColumnIndexOrThrow(MediaStore.Video.Media.BUCKET_DISPLAY_NAME);

            do {
                Long folderId = c.getLong(folderIdIndex);

                if (!folders.containsKey(folderId)) { //proceed only if the folder data has not been inserted already :)
                    String folderName = c.getString(folderNameIndex);
                    folders.put(folderId, folderName);
                    total_num_of_video_files = c.getCount();
                }

            } while (c.moveToNext());

            c.close(); //close cursor
            folders.clear(); //clear the hashmap becuase it's no more useful
        }
        return total_num_of_video_files;
    }

}