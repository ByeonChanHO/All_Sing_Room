package com.example.myapplication.link_php_db;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Link_get_Kakaoid extends AppCompatActivity {
    private static String IP_ADDRESS = "192.168.0.16";
    private static String TAG = "phptest";
    private SingData information;
    private Context exe;
    private String mJsonString;

    public void setContext(Context A){
        exe = A;
    }
    public void setSingData(SingData infor){
        information = infor;
    }
    public SingData getSingData() {return information;}
    public void Start_insert(){
        Log.e("ple : ","1");
        String Kakaoid = information.getMember_Kakaoid();
        String name = information.getMember_name();
        String intro = information.getMember_intro();
        String urimage = information.getMember_urimage();
        String advan = information.getMember_advan();
        String opentime = information.getMember_opentime();
        String holiday = information.getMember_holiday();
        String phonenum = information.getMember_phonenum();
        String whereroom = information.getMember_whereroom();
        String wheremap = information.getMember_wheremap();
        String comadd = information.getMember_comadd();
        String comnum = information.getMember_comnum();
        Log.e("tags : " , Kakaoid + " " +name + " " + intro + " " +urimage + "");
        GetData task = new GetData();
        task.execute("http://" + IP_ADDRESS + "/getProject_Kakaoid.php", Kakaoid,name,intro,urimage, advan, opentime, holiday, phonenum, whereroom, wheremap, comadd, comnum);


    }
    private class GetData extends AsyncTask<String, Void, String> {

        ProgressDialog progressDialog;
        String errorString = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(exe,
                    "Please Wait", null, true, true);
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            progressDialog.dismiss();
            //mTextViewResult.setText(result);
            Log.d(TAG, "response - " + result);

            if (result == null){

                //mTextViewResult.setText(errorString);
                Log.e("errorStirng : ", ""+errorString);
            }
            else {

                mJsonString = result;
                showResult();
            }
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



        try {
            JSONObject jsonObject = new JSONObject(mJsonString);
            JSONArray jsonArray = jsonObject.getJSONArray(TAG_JSON);

            for(int i=0;i<jsonArray.length();i++){
                Log.e("why","not1");
                JSONObject item = jsonArray.getJSONObject(i);

                String name = item.getString(TAG_NAME);
                String intro = item.getString(TAG_INTRO);
                String urimage = item.getString(TAG_URIMAGE);
                String advan = item.getString(TAG_ADVAN);
                String opentime = item.getString(TAG_OPENTIME);
                String whereroom = item.getString(TAG_WHEREROOM);
                String wheremap = item.getString(TAG_WHEREMAP);
                String comadd = item.getString(TAG_COMADD);
                String comnum = item.getString(TAG_COMNUM);
                String review = item.getString(TAG_REVIEW);
                String review_num = item.getString(TAG_REVIEW_NUM);


                SingData singData = new SingData();

                singData.setMember_name(name);
                singData.setMember_intro(intro);
                singData.setMember_urimage(urimage);
                singData.setMember_advan(advan);
                singData.setMember_opentime(opentime);
                singData.setMember_whereroom(whereroom);
                singData.setMember_wheremap(wheremap);
                singData.setMember_comadd(comadd);
                singData.setMember_comnum(comnum);
                singData.setMember_review(review);
                singData.setMember_review_num(review_num);

                //mArrayList.add(personalData);
                //mAdapter.notifyDataSetChanged();
            }



        } catch (JSONException e) {

            Log.d(TAG, "showResult : ", e);
        }

    }

}

