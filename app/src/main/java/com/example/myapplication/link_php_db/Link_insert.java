package com.example.myapplication.link_php_db;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Link_insert extends AppCompatActivity{
    private static String IP_ADDRESS = "172.30.1.39";
    private static String TAG = "phptest";
    private SingData information;
    private Context exe;


    public void setContext(Context A){
        exe = A;
    }
    public void setSingData(SingData infor){
        information = infor;
    }
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
        String coinroom = information.getMember_coinroom();
        String timeroom = information.getMember_timeroom();
        String latitude = information.getMember_latitude();
        String longitude = information.getMember_longitude();
        Log.e("tags : " , Kakaoid + " " +name + " " + intro + " " +urimage + "");
        InsertData task = new InsertData();
        task.execute("http://" + IP_ADDRESS + "/insertProject.php", Kakaoid,name,intro,urimage,
                advan, opentime, holiday, phonenum, whereroom, wheremap, comadd, comnum,coinroom,timeroom,latitude,longitude);


    }
    class InsertData extends AsyncTask<String, Void,String> {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.e("ple : ","2");
            progressDialog = ProgressDialog.show(exe,
                    "Please Wait", null, true, true);
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Log.e("ple : ","3");
            progressDialog.dismiss();
            Log.d(TAG, "POST response  - " + result);
        }


        @Override
        protected String doInBackground(String... params) {


            String Kakaoid = (String)params[1];
            String name = (String)params[2];
            String intro = (String)params[3];
            String urimage = (String)params[4];
            String advan = (String)params[5];
            String opentime = (String)params[6];
            String holiday = (String)params[7];
            String phonenum = (String)params[8];
            String whereroom = (String)params[9];
            String wheremap = (String)params[10];
            String comadd = (String)params[11];
            String comnum = (String)params[12];
            String coinroom = (String)params[9];
            String timeroom = (String)params[10];
            String latitude = (String)params[11];
            String longitude = (String)params[12];

            String serverURL = (String)params[0];
            String postParameters = "&Kakaoid=" + Kakaoid + "&name=" + name + "&intro=" + intro + "&urimage=" + urimage + "&advan=" + advan + "&opentime=" + opentime + "&holiday=" + holiday + "&phonenum=" + phonenum + "&whereroom=" + whereroom
                    + "&wheremap=" + wheremap + "&comadd=" + comadd + "&comnum=" + comnum + "&coinroom="+ coinroom + "&timeroom=" + timeroom
                    + "&latitude=" + latitude + "&longitude=" + longitude;


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
