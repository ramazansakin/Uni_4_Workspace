package com.example.rmzn.dpunews;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.InputStream;

/**
 * Created by Rmzn on 3/3/2016.
 */
public class NewsDetail extends Activity{

    private String newsDetail;
    private EditText headline;
    private String date;
    private ImageView newsImage;
    private EditText fullNewsText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newsdetail);

        newsImage = (ImageView)findViewById(R.id.imageView3);
        headline = (EditText)findViewById(R.id.editText);
        fullNewsText = (EditText)findViewById(R.id.editText2);

        headline.setEnabled(false);
        fullNewsText.setEnabled(false);

        Bundle infos = getIntent().getExtras();
        if( infos != null ){
            newsDetail = infos.getString("about");
            date = infos.getString("upTime");
            headline.setText(infos.getString("title"));
            fullNewsText.setText(newsDetail);

            new DownloadImageTask((ImageView) findViewById(R.id.imageView3))
                    .execute("http://ramazan.knighttube.com/images/" +
                    infos.getString("filePath") );

        }

    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;
        ProgressDialog dialog;

        @Override
        protected void onPreExecute() {

            super.onPreExecute();
            dialog = new ProgressDialog(NewsDetail.this);
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
