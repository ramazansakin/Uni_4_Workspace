package com.example.rmzn.dpunews;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.ksoap2.*;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

/**
 * Created by Rmzn on 11/21/2015.
 */
public class MainScreen extends Activity implements AdapterView.OnItemClickListener {

    private List<News> dpuNews = new ArrayList<News>();
    private ImageView addnews;
    private ListView lview;
    private myNewsListAdapterClass newsAdap;
    private static final int  defaultNewsPhoto = R.drawable.news1;
    private String imgFilesPath = "http://ramazan.knighttube.com/images/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainscreen );

        addnews = (ImageView)findViewById(R.id.addnewsbutton);

        // Filling the News list from Database
        populateNewsList();
        // Showing the list of News with listView
        populateListView();

        addnews.setClickable(true);
        addnews.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent( MainScreen.this, newNews.class ));
            }
        });

        lview.setOnItemClickListener(this);

    }


    private void populateNewsList(){

        GetData loadData = new GetData();
        loadData.execute();

    }



    private void populateListView(){

        newsAdap = new myNewsListAdapterClass( this, (ArrayList<News>) dpuNews);
        lview = (ListView)findViewById(R.id.listView );
        lview.setAdapter(newsAdap);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Intent mIntent = new Intent( MainScreen.this, NewsDetail.class );

        mIntent.putExtra("title",   dpuNews.get(position).getNewsTitle() );
        mIntent.putExtra("about",   dpuNews.get(position).getAbout() );
        mIntent.putExtra("upTime",  dpuNews.get(position).getUploadTime() );
        mIntent.putExtra("filePath", dpuNews.get(position).getNewsImg() );

        startActivity( mIntent );

    }

    private class  GetData extends AsyncTask<Void,Void,SoapObject>
    {
        ProgressDialog dialog;
        private static final String  SOAP_ACTION = "http://tempuri.org/List";
        private static final String  METHOD_NAME = "List";
        private static final String  NAMESPACE = "http://tempuri.org/";
        private static final String  URL = "http://ramazan.knighttube.com/mobileservice.asmx";


        @Override
        protected void onPreExecute() {

            super.onPreExecute();
            dialog = new ProgressDialog(MainScreen.this);
            dialog.setMessage("Yükleniyor");
            dialog.setCancelable(false);
            dialog.show();

        }

        @Override
        protected SoapObject doInBackground(Void... voids) {

            SoapObject result = null;

            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.setOutputSoapObject(request);
            envelope.dotNet=true;

            HttpTransportSE androidHttpTransportSE = new HttpTransportSE(URL);
            try {

                androidHttpTransportSE.call(SOAP_ACTION,envelope);
                if (envelope.getResponse() != null)
                    result = (SoapObject)envelope.getResponse();

            } catch (IOException e) {
                e.printStackTrace();
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            }

            return result;
        }

        @Override
        protected void onPostExecute(SoapObject result) {

            if( result != null ) {
                for (int i = 0; i < result.getPropertyCount(); ++i) {

                    SoapObject soapObject = (SoapObject) result.getProperty(i);
                    News  news = new News();

                    news.setNewsTitle(soapObject.getPrimitivePropertyAsString("Title"));
                    news.setAbout(soapObject.getPropertyAsString("Description"));
                    news.setUploadTime(soapObject.getPropertyAsString("DateTime").toString().substring(0,10) );
                    news.setNewsImg( soapObject.getPropertyAsString("FilePath").toString() );

                    dpuNews.add(news);
                }
            }else
                Toast.makeText(getApplicationContext(),"Not any News ... Check the Internet Connection!!", Toast.LENGTH_LONG).show();

            dialog.dismiss();
        }

    }

}
