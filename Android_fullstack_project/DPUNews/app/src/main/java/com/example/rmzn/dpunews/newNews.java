package com.example.rmzn.dpunews;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpResponseException;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;

import android.util.Base64;

/**
 * Created by Rmzn on 11/25/2015.
 */
public class newNews extends Activity
{

    TextView headline, fullNews;
    EditText head, full;
    Button uploadButton, uploadPicBtn;
    private String encodedImage = "unencodedImage";
    private String filePath = "defaultFile";

    private static final int RESULT_LOAD_IMAGE = 1;
    private static final String SOAP_ACTION = "http://tempuri.org/Add";
    private static final String METHOD_NAME = "Add";
    private static final String NAMESPACE = "http://tempuri.org/";
    private static final String URL = "http://ramazan.knighttube.com/mobileservice.asmx";
    private static final String USER_NAME = "Ramazan Sakin";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_news);

        headline = (TextView)findViewById(R.id.baslikTv);
        fullNews = (TextView)findViewById(R.id.fullTv);

        head = (EditText)findViewById(R.id.baslikEt);
        full = (EditText)findViewById(R.id.fullEt);

        uploadPicBtn = (Button) findViewById(R.id.uploadPic);
        uploadPicBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galeryInt = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galeryInt, RESULT_LOAD_IMAGE);

            }
        });

        uploadButton = (Button)findViewById(R.id.uploadbttn);
        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoadData loadData = new LoadData();
                loadData.execute();

                //Toast.makeText(getApplicationContext(), "Haber başarıyla yüklendi!", Toast.LENGTH_LONG).show();
            }
        });
    }


    private class  LoadData extends AsyncTask<Void,Void,Boolean>
    {
        ProgressDialog dialog;
        private static final String SOAP_ACTION = "http://tempuri.org/Add";
        private static final String METHOD_NAME = "Add";
        private static final String NAMESPACE = "http://tempuri.org/";
        private static final String URL = "http://ramazan.knighttube.com/mobileservice.asmx";

        @Override
        protected void onPreExecute() {

            dialog = new ProgressDialog(newNews.this);
            dialog.setMessage("Yükleniyor");
            dialog.setCancelable(false);
            dialog.show();
            super.onPreExecute();
        }

        @Override
        protected Boolean doInBackground(Void... voids) {

            Boolean result = null;
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            request.addProperty("title", head.getText().toString() );
            request.addProperty("description", full.getText().toString() );
            request.addProperty("userName", USER_NAME );
            request.addProperty("token", "80rOfzge7mNeBe4qtEZ6zGsANYuk3SOT" );
            request.addProperty("base64", encodedImage );
            request.addProperty("fileName", filePath );


            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.setOutputSoapObject(request);
            envelope.dotNet=true;

            HttpTransportSE androidHttpTransportSE = new HttpTransportSE(URL);
            try {
                androidHttpTransportSE.call(SOAP_ACTION,envelope);

                if (envelope.getResponse() != null) {
                    result =  Boolean.parseBoolean("" + envelope.getResponse());
                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            dialog.dismiss();
            finish();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if( requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null ){

            Uri selectedImage = data.getData();
            String s = getRealPathFromURI( selectedImage );
            filePath = s.substring(s.lastIndexOf("/") + 1);

            //Toast.makeText( getApplicationContext(), "filePAth = " + filePath, Toast.LENGTH_LONG ).show();

            InputStream inpStream;
            try{
                inpStream = getContentResolver().openInputStream(selectedImage);

                Bitmap image = BitmapFactory.decodeStream(inpStream);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                image.compress(Bitmap.CompressFormat.JPEG , 100 , baos );

                byte[] bA = baos.toByteArray();
                encodedImage = Base64.encodeToString(bA, Base64.DEFAULT);

            }catch ( Exception e ){
                e.printStackTrace();
            }

        }

    }

    public String getRealPathFromURI(Uri contentUri) {

        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = managedQuery( contentUri,
                proj, // Which columns to return
                null, // WHERE clause; which rows to return (all rows)
                null, // WHERE clause selection arguments (none)
                null); // Order-by clause (ascending by name)

        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();

        return cursor.getString(column_index);
    }


}
