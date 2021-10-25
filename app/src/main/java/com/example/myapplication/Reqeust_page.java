package com.example.myapplication;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapplication.link_php_db.SingData;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Reqeust_page extends AppCompatActivity {
    private String name;
    private EditText title;
    private EditText body_request;
    private Button save;

    private static String IP_ADDRESS = "192.168.0.14";
    private static String TAG = "phptest";

    protected void onCreate(Bundle savedInstanceState) {
        Log.d("찬호", "바란다.");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.request_page);
        name = getIntent().getStringExtra("storename");
        title = (EditText)findViewById(R.id.input_title);
        body_request = (EditText)findViewById(R.id.write_body);
        save = (Button)findViewById(R.id.save_request);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String temp_title = (title.getText()).toString();
                String temp_body = (body_request.getText()).toString();

                InsertData task = new InsertData();
                task.execute("http://" + IP_ADDRESS + "/insertRequest.php", name,temp_title,temp_body);
            }
        });



    }
    class InsertData extends AsyncTask<String, Void,String> {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.e("ple : ","2");
            progressDialog = ProgressDialog.show(Reqeust_page.this,
                    "Please Wait", null, true, true);
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Log.e("ple : ","3");
            progressDialog.dismiss();
            Log.d(TAG, "POST response  - " + result);
            Intent back = new Intent(Reqeust_page.this,MainActivity.class);
            startActivity(back);
        }


        @Override
        protected String doInBackground(String... params) {


            String Name = (String)params[1];
            String Title = (String)params[2];
            String Body_request = (String)params[3];


            String serverURL = (String)params[0];
            String postParameters = "&name=" + Name + "&title=" + Title + "&body_request=" + Body_request;


            try {

                URL url = new URL(serverURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();


                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.connect();


                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(postParameters.getBytes("UTF-8"));
                outputStream.flush();
                outputStream.close();


                int responseStatusCode = httpURLConnection.getResponseCode();
                Log.d(TAG, "POST response code - " + responseStatusCode);

                InputStream inputStream;
                if(responseStatusCode == HttpURLConnection.HTTP_OK) {
                    inputStream = httpURLConnection.getInputStream();
                }
                else{
                    inputStream = httpURLConnection.getErrorStream();
                }


                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuilder sb = new StringBuilder();
                String line = null;

                while((line = bufferedReader.readLine()) != null){
                    sb.append(line);
                }


                bufferedReader.close();


                return sb.toString();


            } catch (Exception e) {

                Log.d(TAG, "InsertData: Error ", e);

                return new String("Error: " + e.getMessage());
            }

        }

    }
}
