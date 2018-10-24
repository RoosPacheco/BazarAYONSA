package com.example.dell.bazarayonsa;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

public class ListAdapter extends  BaseAdapter  {

    private Context context;
    private ArrayList<String> listURL;

    public ListAdapter(Context context, ArrayList<String> listURL) {
        this.context = context;
        this.listURL = listURL;
    }

    @Override
    public int getCount() {
        return listURL.size();
    }

    @Override
    public Object getItem(int i) {
        return listURL.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        RecyclerView.ViewHolder holder;

        if (view == null){
            view = View.inflate(context,R.layout.image_listview, null);

        }
        ImageView imageView = (ImageView) view.findViewById(R.id.item_image);

        new getImageUrl(imageView).execute(listURL.get(i));



        return view;
    }

    private class getImageUrl extends AsyncTask<String, Void, Bitmap> {

        ImageView image;

        public getImageUrl(ImageView image) {
            this.image = image;
        }

        protected Bitmap doInBackground(String... urls) {
            String url = urls[0];
            Bitmap img = null;
            try {
                InputStream in = new java.net.URL(url).openStream();
                img = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
            }
            return img;
        }

        protected void onPostExecute(Bitmap result) {
            image.setImageBitmap(result);
        }
    }
}
