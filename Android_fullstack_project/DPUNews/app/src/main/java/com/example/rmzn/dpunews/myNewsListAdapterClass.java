package com.example.rmzn.dpunews;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rmzn on 3/23/2016.
 */
public class myNewsListAdapterClass extends BaseAdapter {

    private Activity activity;
    private static LayoutInflater inflater=null;
    private List<News> data = new ArrayList<News>();

    public myNewsListAdapterClass(Activity a, ArrayList<News> d) {
        activity = a;
        data=d;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //imageLoader=new ImageLoader(activity.getApplicationContext());
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View itemView = convertView;
        if( itemView == null ){
            itemView = inflater.inflate(R.layout.view_news, parent, false);
        }

        // Use the current news
        News currNews = data.get(position);

        // Current news' icon
        ImageView newsImg = (ImageView) itemView.findViewById(R.id.news_image);

        newsImg.setImageResource(R.drawable.news1);
        // Current news' Title
        TextView titleText = (TextView) itemView.findViewById(R.id.title_news);
        titleText.setText(currNews.getNewsTitle());

        // Current news' short content
        TextView  AboutText = (TextView)itemView.findViewById(R.id.About );
        AboutText.setText(currNews.getAbout().length()>25 ? currNews.getAbout().substring(0,25)+"..." : currNews.getAbout() );

        // Current news' upload time
        TextView  dateText = (TextView)itemView.findViewById(R.id.uploadTime );
        dateText.setText(currNews.getUploadTime());

        return  itemView;
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;
        ProgressDialog dialog;

        @Override
        protected void onPreExecute() {

            super.onPreExecute();
            dialog = new ProgressDialog(activity);
            dialog.setMessage("Yükleniyor");
            dialog.setCancelable(false);
            dialog.show();
        }

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
            dialog.dismiss();
        }
    }

}
