package com.example.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.myapplication.link_php_db.SingData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Between_First_Main extends AppCompatActivity {
    private static String IP_ADDRESS = "192.168.0.14";
    private static String TAG = "phptest";
    private String mJsonString;
    private String Nickname;
    private String User_id;
    private String Email;
    Intent Next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page);


        Intent intent = getIntent();
        Nickname = intent.getStringExtra("NICKNAME");
        User_id = intent.getStringExtra("Kakaoid");
        Email = intent.getStringExtra("Email");
        Log.e("Kakaoid2",User_id);
        GetData task = new GetData();
        task.execute( "http://" + IP_ADDRESS + "/getProject_Kakaoid.php", User_id);




    }
    private class GetData extends AsyncTask<String, Void, String> {

        ProgressDialog progressDialog;
        String errorString = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(Between_First_Main.this,
                    "Please Wait", null, true, true);
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            progressDialog.dismiss();
            Log.d(TAG, "response - " + result);

            if (result == null){

                //mTextViewResult.setText(errorString);
                Log.e("errorStirng : ", ""+errorString);

            }
            else {

                mJsonString = result;
                showResult();

            }
            /*try {
                wait(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            startActivity(Next);*/
        }


        @Override
        protected String doInBackground(String... params) {

            String serverURL = params[0];
            String Kakaoid = params[1];
            //Log.d("name : ", ""+name);
            String postParameters = "&Kakaoid=" + Kakaoid;
            //Log.d(" postParameters  : ", ""+ postParameters );


            try {

                URL url = new URL(serverURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();


                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.connect();


                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(postParameters.getBytes("UTF-8"));
                outputStream.flush();
                outputStream.close();


                int responseStatusCode = httpURLConnection.getResponseCode();
                Log.d(TAG, "response code - " + responseStatusCode);

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
                String line;

                while((line = bufferedReader.readLine()) != null){
                    sb.append(line);
                }

                bufferedReader.close();

                return sb.toString().trim();


            } catch (Exception e) {

                Log.d(TAG, "GetData : Error ", e);
                errorString = e.toString();

                return null;
            }

        }
    }


    private void showResult(){

        String TAG_JSON="webnautes";
        String TAG_NAME = "name";
        String TAG_INTRO = "intro";
        String TAG_URIMAGE = "urimage";
        String TAG_ADVAN = "advan";
        String TAG_OPENTIME = "opentime";
        String TAG_HOLIDAY = "holiday";
        String TAG_PHONENUM ="phonenum";
        String TAG_WHEREROOM = "whereroom";
        String TAG_WHEREMAP = "wheremap";
        String TAG_COMADD = "comadd";
        String TAG_COMNUM = "comnum";
        String TAG_REVIEW = "review";
        String TAG_REVIEW_NUM = "review_num";
        String TAG_LONGITUDE = "longitude";
        String TAG_LATITUDE = "latitude";
        String TAG_COINROOM = "coinroom";
        String TAG_TIMEROOM = "timeroom";



        try {
            JSONObject jsonObject = new JSONObject(mJsonString);
            JSONArray jsonArray = jsonObject.getJSONArray(TAG_JSON);
            JSONObject item = jsonArray.getJSONObject(0);

            String name = item.getString(TAG_NAME);
            String intro = item.getString(TAG_INTRO);
            Log.d("너의 이미지 과연","과연");
            String urimage = item.getString(TAG_URIMAGE);
            Log.d("너의 이미지 과연","과연1");
            String advan = item.getString(TAG_ADVAN);
            String phonenum = item.getString(TAG_PHONENUM);
            String opentime = item.getString(TAG_OPENTIME);
            String holiday = item.getString(TAG_HOLIDAY);
            String whereroom = item.getString(TAG_WHEREROOM);
            String wheremap = item.getString(TAG_WHEREMAP);
            String comadd = item.getString(TAG_COMADD);
            String comnum = item.getString(TAG_COMNUM);
            String review = item.getString(TAG_REVIEW);
            String review_num = item.getString(TAG_REVIEW_NUM);
            String longitude = item.getString(TAG_LONGITUDE);
            String latitude = item.getString(TAG_LATITUDE);
            String timeroom = item.getString(TAG_TIMEROOM);
            String coinroom = item.getString(TAG_COINROOM);
            Next = new Intent(Between_First_Main.this, MainActivity.class);

            Log.e("ChanHo : ", "Go way");
            Next.putExtra("Nickname",Nickname);
            Next.putExtra("Email",Email);
            Next.putExtra("Kakaoid", User_id);
            //Log.e("Kakaoid2",User_id);
            Next.putExtra("name",name);
            Next.putExtra("intro",intro);
            Next.putExtra("urimage",urimage);
            Next.putExtra("holiday",holiday);
            Next.putExtra("advan",advan);
            Next.putExtra("phonenum",phonenum);
            Next.putExtra("opentime",opentime);
            Next.putExtra("whereroom",whereroom);
            Next.putExtra("wheremap", wheremap);
            Next.putExtra("comadd", comadd);
            Next.putExtra("comnum", comnum);
            Next.putExtra("review", review);
            Next.putExtra("review_num", review_num);
            Next.putExtra("latitude",latitude);
            Next.putExtra("longitude",longitude);
            Next.putExtra("coinroom",coinroom);
            Next.putExtra("timteroom",timeroom);


            startActivity(Next);
            /*
            SingData information = new SingData();

            information.setMember_name(name);
            information.setMember_intro(intro);
            information.setMember_urimage(urimage);
            information.setMember_phonenum(phonenum);
            information.setMember_advan(advan);
            information.setMember_opentime(opentime);
            information.setMember_whereroom(whereroom);
            information.setMember_wheremap(wheremap);
            information.setMember_comadd(comadd);
            information.setMember_comnum(comnum);
            information.setMember_review(review);
            information.setMember_review_num(review_num);*/


            //Log.d("setname1", singData.getMember_phonenum() +"");
            //Log.d("setname2", information.getMember_phonenum() +"");

            //mArrayList.add(personalData);
            //mAdapter.notifyDataSetChanged();




        } catch (JSONException e) {

            Log.d(TAG, "showResult : ", e);
        }


    }

}
