package com.nematjon.edd_client_season_two;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

public class ImageAdapter extends BaseAdapter {

    private Context mContext;
    ArrayList<File> takenPhotos = new ArrayList<File>();
    File folder = new File(Objects.requireNonNull(mContext).getExternalFilesDir("Cropped Faces").toString() + File.separator); //getting the app folder
    File[] files = folder.listFiles();


    public ImageAdapter(Context mContext) {
        this.mContext = mContext;
    }


    @Override
    public int getCount() {
        return takenPhotos.size();
    }

    @Override
    public Object getItem(int position) {

        if (folder.exists()) {
            if (files.length > 0) {
                Log.e("TAG", "getItem: " + files.length );
                for (File file : files) {
                    if (!file.isDirectory()) {
                        takenPhotos.add(file);
                    }

                }
            }else{
                Log.e("TAG", "getItem: " + files.length );
            }
            return takenPhotos.get(position);
        }

        else{
            Log.e("TAG", "getItem: folder does not exist" );
            return null;
        }

    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        ImageView imageView = new ImageView(mContext);
        imageView.setImageURI(Uri.fromFile(takenPhotos.get(position)));
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setLayoutParams(new GridView.LayoutParams(340, 350));

        return imageView;
    }
}