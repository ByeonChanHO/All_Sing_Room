package com.example.myapplication;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myapplication.link_php_db.Link_insert;
import com.example.myapplication.link_php_db.SingData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Time;

public class ReparePage extends AppCompatActivity {

    private static String IP_ADDRESS = "192.168.0.14";
    private static String TAG = "phptest";

    private String Nickname;
    private String Email;
    //SingData data;
    private String Kakaoid;
    private String Storename;
    private String Intro;
    private String Urimage;
    private String Advan;
    private String Phonenum;
    private String Opentime;
    private String Whereroom;
    private String Wheremap;
    private String Comadd;
    private String Comnum;
    private String Review;
    private String Review_num;
    private String Latitude;
    private String Longitude;
    private String Coinroom;
    private String Timeroom;
    private String Holiday;

    public EditText editStorname;
    public EditText editIntro;
    public EditText editAdvan;
    public EditText editBill;
    public EditText editOpen;
    public EditText editHoli;
    public EditText editPhone;
    public EditText editWhere;
    public EditText editLat;
    public EditText editLong;
    public EditText editComa;
    public EditText editComn;

    public CheckBox Checktime;
    public CheckBox Checkcoin;

    public Button saveBtn;
    public Button URLBtn;
    public String URLString;
    public Bitmap bitmap;
    public ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.repare_page);

        Nickname = getIntent().getStringExtra("NICKNAME");
        Email = getIntent().getStringExtra("Email");
        Kakaoid = getIntent().getStringExtra("Kakaoid");
        //Log.e("Kakaoid3", Kakaoid);
        Storename = getIntent().getStringExtra("name");
        Intro = getIntent().getStringExtra("intro");
        Urimage = getIntent().getStringExtra("urimage");
        Advan = getIntent().getStringExtra("advan");
        Phonenum = getIntent().getStringExtra("phonenum");
        Opentime = getIntent().getStringExtra("opentime");
        Whereroom = getIntent().getStringExtra("whereroom");
        Wheremap = getIntent().getStringExtra("wheremap");
        Comadd = getIntent().getStringExtra("comadd");
        Comnum = getIntent().getStringExtra("comnum");
        Review = getIntent().getStringExtra("review");
        Review_num = getIntent().getStringExtra("review_num");
        Timeroom = getIntent().getStringExtra("timeroom");
        Coinroom = getIntent().getStringExtra("coinroom");
        Holiday = getIntent().getStringExtra("holiday");
        Latitude = getIntent().getStringExtra("latitude");
        Longitude = getIntent().getStringExtra("longitude");

        Intent intent = getIntent();
        final String Kakoid = intent.getStringExtra("Kakaoid");
        /*final EditText name = (EditText)findViewById(R.id.editText_main_name);
        final EditText intro = (EditText)findViewById(R.id.editText_main_intro);
        Button btm = (Button)findViewById(R.id.button_main_insert);
        btm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Link_insert A = new Link_insert();
                SingData B = new SingData();
                String Name = name.getText().toString();
                B.setMember_Kakaoid(Kakoid);
                B.setMember_name(Name);
                B.setMember_intro(intro.getText().toString());
                B.setMember_comadd("sdfsdaf");
                B.setMember_comnum("123425624");
                B.setMember_phonenum("010154978");
                B.setMember_whereroom("fasf");
                B.setMember_wheremap("dsfasdfd");
                A.setSingData(B);
                A.setContext(ReparePage.this);
                A.Start_insert();

            }
        });*/
        editStorname = (EditText)findViewById(R.id.store_text);
        editIntro = (EditText)findViewById(R.id.RtextView_list_intro);
        editAdvan = (EditText)findViewById(R.id.RtextView_list_advan);
        editBill = (EditText)findViewById(R.id.RtextView_list_money);
        editOpen = (EditText)findViewById(R.id.RtextView_list_opentime);
        editHoli = (EditText)findViewById(R.id.RtextView_list_holiday);
        editPhone = (EditText)findViewById(R.id.RtextView_list_phonenum);
        editWhere = (EditText)findViewById(R.id.RtextView_list_whereroom);
        editLat = (EditText)findViewById(R.id.RtextView_list_latitude);
        editLong = (EditText)findViewById(R.id.RtextView_list_longitude);
        editComa = (EditText)findViewById(R.id.RtextView_list_comadd);
        editComn = (EditText)findViewById(R.id.RtextView_list_comnum);

        Checkcoin = (CheckBox)findViewById(R.id.coin_text);
        Checktime = (CheckBox)findViewById(R.id.time_text);

        editStorname.setText(Storename);
        editIntro.setText(Intro);
        editAdvan.setText(Advan);
        editBill.setText(Wheremap);
        editOpen.setText(Opentime);
        editHoli.setText(Holiday);
        editPhone.setText(Phonenum);
        editWhere.setText(Whereroom);
        editLat.setText(Latitude);
        editLong.setText(Longitude);
        editComa.setText(Comadd);
        editComn.setText(Comnum);

        if(Coinroom != null&& Coinroom != "null"&&Coinroom.equals("true")){
            Checkcoin.setChecked(true);
        }
        if(Timeroom != null&& Timeroom != "null"&&Timeroom.equals("true")){
            Checktime.setChecked(true);
        }

        URLString = Urimage;

        saveBtn=(Button)findViewById(R.id.save_repare);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Storename = (editStorname.getText()).toString();
                Intro = (editIntro.getText()).toString();
                Urimage = (URLString);
                Advan = (editAdvan.getText()).toString();
                Opentime = (editOpen.getText()).toString();
                Holiday = (editHoli.getText()).toString();
                Phonenum = (editPhone.getText()).toString();
                Whereroom = (editWhere.getText()).toString();
                Wheremap = (editBill.getText()).toString();
                Comadd = (editComa.getText()).toString();
                Comnum = (editComn.getText()).toString();
                Latitude = (editLat.getText()).toString();
                Longitude = (editLong.getText()).toString();
                if(Checkcoin.isChecked()){
                    Log.d("체크", "채크1");
                    Coinroom = "true";
                }
                if(Checktime.isChecked()){
                    Log.d("체크", "채크2");
                    Timeroom = "true";
                }


                InsertData task = new InsertData();
                task.execute("http://" + IP_ADDRESS + "/insertProject.php", Kakaoid,Storename,Intro,Urimage,
                        Advan, Opentime, Holiday, Phonenum, Whereroom, Wheremap, Comadd, Comnum,Coinroom,Timeroom,Latitude,Longitude);
            }
        });
        image=(ImageView)findViewById(R.id.picture);
        URLString = Urimage;
        Log.e("URLcheck:", "URLString");
        Thread mThread = new Thread(){
            public void run(){
                try{
                    URL url = new URL(URLString);
                    HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                    conn.setDoInput(true);
                    conn.connect();
                    InputStream is = conn.getInputStream();
                    bitmap = BitmapFactory.decodeStream(is);
                }catch(MalformedURLException e){
                    e.printStackTrace();
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
        };

        mThread.start();
        try{
            mThread.join();
            image.setImageBitmap(bitmap);

        }catch(InterruptedException e){
            e.printStackTrace();
        }

        URLBtn=(Button)findViewById(R.id.repare_picture);
        URLBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder ad = new AlertDialog.Builder(ReparePage.this);

                ad.setTitle("사진변경");       // 제목 설정
                ad.setMessage("URL을 입력하세요");   // 내용 설정


                final EditText et = new EditText(ReparePage.this);
                ad.setView(et);


                ad.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        // Text 값 받아서 로그 남기기
                        URLString = et.getText().toString();

                        dialog.dismiss();
                        Toast.makeText(ReparePage.this, "사진을 성공적으로 저장했습니다.", Toast.LENGTH_SHORT).show();

                        Thread mThread = new Thread(){
                            public void run(){
                                try{
                                    URL url = new URL(URLString);
                                    HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                                    conn.setDoInput(true);
                                    conn.connect();
                                    InputStream is = conn.getInputStream();
                                    bitmap = BitmapFactory.decodeStream(is);
                                }catch(MalformedURLException e){
                                    e.printStackTrace();
                                }catch(IOException e){
                                    e.printStackTrace();
                                }
                            }
                        };

                        mThread.start();
                        try{
                            mThread.join();
                            image.setImageBitmap(bitmap);

                        }catch(InterruptedException e){
                            e.printStackTrace();
                        }
                    }
                });


                ad.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Toast.makeText(ReparePage.this, "취소했습니다.", Toast.LENGTH_SHORT).show();
                    }
                });

                ad.show();



            }
        });
    }
    class InsertData extends AsyncTask<String, Void,String> {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.e("ple : ","2");
            progressDialog = ProgressDialog.show(ReparePage.this,
                    "Please Wait", null, true, true);
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Log.e("ple : ","3");
            progressDialog.dismiss();
            Log.d(TAG, "POST response  - " + result);
            Intent go_back = new Intent(ReparePage.this,MainActivity.class);
            //Log.e("Kakaoid2",User_id);
            go_back.putExtra("name1",Storename);
            go_back.putExtra("intro1",Intro);
            go_back.putExtra("urimage1",Urimage);
            go_back.putExtra("holiday1",Holiday);
            go_back.putExtra("advan1",Advan);
            go_back.putExtra("phonenum1",Phonenum);
            go_back.putExtra("opentime1",Opentime);
            go_back.putExtra("whereroom1",Whereroom);
            go_back.putExtra("wheremap1", Wheremap);
            go_back.putExtra("comadd1", Comadd);
            go_back.putExtra("comnum1", Comnum);
            go_back.putExtra("latitude1",Latitude);
            go_back.putExtra("longitude1",Longitude);
            go_back.putExtra("coinroom1",Coinroom);
            go_back.putExtra("timeroom1",Timeroom);
            Log.d("가즈아", Intro);
            setResult(RESULT_OK,go_back);
            finish();
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
            String coinroom = (String)params[13];
            String timeroom = (String)params[14];
            String latitude = (String)params[15];
            String longitude = (String)params[16];

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
