package com.example.dell.bazarayonsa;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import java.io.IOException;
import java.net.URL;

public class ImageAdapter extends ArrayAdapter<String> {

    private String[] imageURLArray;
    private LayoutInflater inflater;

    public ImageAdapter(Context context, int textViewResourceId,
                        String[] imageArray) {
        super(context, textViewResourceId, imageArray);
        // TODO Auto-generated constructor stub

        inflater = ((Activity)context).getLayoutInflater();
        imageURLArray = imageArray;
    }

    private static class ViewHolder {
        ImageView imageView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        ViewHolder viewHolder = null;
        if(convertView == null) {
            convertView = inflater.inflate(R.layout.image_listview, null);

            viewHolder = new ViewHolder();
            viewHolder.imageView = (ImageView)convertView.findViewById(R.id.item_image);
            convertView.setTag(viewHolder);
        }

        viewHolder = (ViewHolder)convertView.getTag();

        //load image directly
        Bitmap imageBitmap = null;
        try {

            URL imageURL = new URL(imageURLArray[position]);
            imageBitmap = BitmapFactory.decodeStream(imageURL.openStream());
            viewHolder.imageView.setImageBitmap(imageBitmap);
        } catch (IOException e) {
            // TODO: handle exception
            Log.e("error", "Downloading Image Failed");
            viewHolder.imageView.setImageResource(R.drawable.home_octmuebles);
        }

        return convertView;
    }
}
