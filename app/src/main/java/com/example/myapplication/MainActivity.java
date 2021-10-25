package com.example.myapplication;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.Resource;
import com.example.myapplication.link_php_db.ReviewData;
import com.example.myapplication.link_php_db.SingData;
import com.kakao.auth.KakaoAdapter;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.LogoutResponseCallback;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
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


    private static String IP_ADDRESS = "192.168.0.14";
    private static String TAG = "phptest";
    private String mJsonString;
    private ArrayList<ReviewData> reviewArrayList;
    private MainAcitivity_Review Review_mAdapter;


    public static final int sub = 1001;

    private final int PERMISSIONS_ACCESS_FINE_LOCATION = 1000;
    private final int PERMISSIONS_ACCESS_COARSE_LOCATION = 1001;
    private boolean isAccessFineLocation = false;
    private boolean isAccessCoarseLocation = false;
    private boolean isPermission = false;
    private GpsInfo gps;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e("Chan : " , "go way");

        final MapView mapView = new MapView(this);
        mapView.setDaumMapApiKey("API KEY");
        final ViewGroup mapViewContainer = (ViewGroup) findViewById(R.id.mapGo);



        Nickname = getIntent().getStringExtra("NICKNAME");
        Email = getIntent().getStringExtra("Email");
        Kakaoid = getIntent().getStringExtra("Kakaoid");
        Log.e("Kakaoid3", Kakaoid);
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
        Log.d("위도 경도", Latitude + "   " + Longitude);
        //Log.e("allN",""+ data.getMember_Kakaoid());
        //Log.e("allN",""+ Email);
        Log.e("Chan : " , "go way1   " + Kakaoid +"   "+Storename);
        if(Storename != null && Storename != "null"){
            Toolbar title_stroename = (Toolbar)findViewById(R.id.title_stroename);
            title_stroename.setTitle(Storename);
        }




        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Log.e("Chan : " , "go way2");
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "추후 공개", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
        });
        Log.e("Chan : " , "go way3");
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        Log.e("Chan : " , "go way4");
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //그림 설정 로그인 후 보이는 노래방 사진 (이거 데이터 베이스에서 가져와서 넣어야함)
        ImageView iv = (ImageView)findViewById(R.id.sing_picture);



        Glide.with(this).load(Urimage).into(iv);


        출처: https://itpangpang.xyz/243 [ITPangPang]


        Log.e("Chan : " , "go way5");
        Button btm = (Button)findViewById(R.id.int_btn);
        btm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mapViewContainer.removeView(mapView);

                RecyclerView viewing = (RecyclerView)findViewById(R.id.listView_main_list);
                viewing.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                ArrayList<SingData> mArrayList = new ArrayList<>();
                MainActivity_item_list mAdapter = new MainActivity_item_list(MainActivity.this,mArrayList);
                viewing.setAdapter(mAdapter);

                mArrayList.clear();
                mAdapter.notifyDataSetChanged();

                SingData Temp = new SingData();
                Temp.setMember_Kakaoid(Kakaoid);
                Temp.setMember_intro(Intro);
                Temp.setMember_urimage(Urimage);
                Temp.setMember_advan(Advan);
                Temp.setMember_phonenum(Phonenum);
                Temp.setMember_opentime(Opentime);
                Temp.setMember_whereroom(Whereroom);
                Temp.setMember_wheremap(Wheremap);
                Temp.setMember_comadd(Comadd);
                Temp.setMember_comnum(Comnum);
                Temp.setMember_name(Storename);
                Temp.setMember_coinroom(Coinroom);
                Temp.setMember_holiday(Holiday);
                Temp.setMember_timeroom(Timeroom);
                Temp.setMember_latitude(Latitude);
                Temp.setMember_longitude(Longitude);
                Temp.setMember_review(Review);
                Temp.setMember_review_num(Review_num);

                mArrayList.add(Temp);
                mAdapter.notifyDataSetChanged();

            }
        });

        Button imp_btn = (Button)findViewById(R.id.imp_btn);
        imp_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mapViewContainer.removeView(mapView);

                RecyclerView viewing = (RecyclerView)findViewById(R.id.listView_main_list);
                viewing.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                ArrayList<SingData> mArrayList = new ArrayList<>();
                MainActivity_information_room mAdapter = new MainActivity_information_room(MainActivity.this,mArrayList);
                viewing.setAdapter(mAdapter);

                mArrayList.clear();
                mAdapter.notifyDataSetChanged();

                SingData temp = new SingData();
                temp.setMember_name(Storename);
                mArrayList.add(temp);
                mAdapter.notifyDataSetChanged();


            }
        });

        Button see_btn = (Button)findViewById(R.id.see_btn);
        see_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mapViewContainer.removeView(mapView);

                RecyclerView viewing = (RecyclerView)findViewById(R.id.listView_main_list);
                viewing.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                reviewArrayList = new ArrayList<>();
                Review_mAdapter = new MainAcitivity_Review(MainActivity.this,reviewArrayList);
                viewing.setAdapter(Review_mAdapter);
                reviewArrayList.clear();
                Review_mAdapter.notifyDataSetChanged();

                GetData_Review task = new GetData_Review();
                task.execute("http://" + IP_ADDRESS + "/getreviews.php", Storename);


            }
        });


        Button mapBtn = (Button)findViewById(R.id.map_btn);
        mapBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                RecyclerView viewing = (RecyclerView)findViewById(R.id.listView_main_list);
                viewing.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                ArrayList<SingData> mArrayList = new ArrayList<>();
                Map_page mAdapter = new Map_page(MainActivity.this,mArrayList);
                viewing.setAdapter(mAdapter);

                mArrayList.clear();
                mAdapter.notifyDataSetChanged();

                SingData temp = new SingData();

                mArrayList.add(temp);
                mAdapter.notifyDataSetChanged();


                //mapViewContainer.addView(mapView);*/
                double latitude = Double.parseDouble(Latitude);
                double longitude = Double.parseDouble(Longitude);
                Log.e("URLcheck:", Urimage);
                if(Latitude.equals("null") || Longitude.equals("null")){
                    // 못 받아 올경우
                    if (!isPermission) {
                        callPermission();
                        return;
                    }

                    gps = new GpsInfo(MainActivity.this);
                    // GPS 사용유무 가져오기
                    if (gps.isGetLocation()) {

                        latitude = gps.getLatitude();
                        longitude = gps.getLongitude();



                    } else {
                        // GPS 를 사용할수 없으므로
                        gps.showSettingsAlert();
                    }

                }


                MapPoint mapPoint = MapPoint.mapPointWithGeoCoord(latitude, longitude);
                mapView.setMapCenterPoint(mapPoint, true);
                //true면 앱 실행 시 애니메이션 효과가 나오고 false면 애니메이션이 나오지않음.

                MapPOIItem marker = new MapPOIItem();
                marker.setItemName(Storename);
                marker.setTag(0);
                marker.setMapPoint(mapPoint);
                // 기본으로 제공하는 BluePin 마커 모양.
                marker.setMarkerType(MapPOIItem.MarkerType.BluePin);
                // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.
                marker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);
                mapView.addPOIItem(marker);

                RecyclerView viewing = (RecyclerView)findViewById(R.id.listView_main_list);
                viewing.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                reviewArrayList = new ArrayList<>();
                Review_mAdapter = new MainAcitivity_Review(MainActivity.this,reviewArrayList);
                viewing.setAdapter(Review_mAdapter);
                reviewArrayList.clear();
                Review_mAdapter.notifyDataSetChanged();

                mapViewContainer.addView(mapView);
            }
        });

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            Log.d("Enter : ","it1");
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d("Enter : ","it2");
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        /*if (id == R.id.action_settings) {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Log.d("Enter : ","it3");
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            Intent go_repare = new Intent(MainActivity.this,ReparePage.class);
            go_repare.putExtra("Nickname",Nickname);
            go_repare.putExtra("Email",Email);
            go_repare.putExtra("Kakaoid", Kakaoid);
            go_repare.putExtra("name",Storename);
            go_repare.putExtra("intro",Intro);
            go_repare.putExtra("urimage",Urimage);
            go_repare.putExtra("advan",Advan);
            go_repare.putExtra("phonenum",Phonenum);
            go_repare.putExtra("opentime",Opentime);
            go_repare.putExtra("whereroom",Whereroom);
            go_repare.putExtra("wheremap", Wheremap);
            go_repare.putExtra("comadd", Comadd);
            go_repare.putExtra("comnum", Comnum);
            go_repare.putExtra("review", Review);
            go_repare.putExtra("review_num", Review_num);
            go_repare.putExtra("latitude", Latitude);
            go_repare.putExtra("longitude", Longitude);
            go_repare.putExtra("holiday", Holiday);
            go_repare.putExtra("timeroom", Timeroom);
            go_repare.putExtra("coinroom",Coinroom);
            startActivityForResult(go_repare,3000);

        }else if (id == R.id.nav_manage) {

            UserManagement.getInstance().requestLogout(new LogoutResponseCallback() {
                @Override
                public void onCompleteLogout() {
                    Intent first = new Intent(MainActivity.this,FirstActivity.class);
                    startActivity(first);
                }
            });

        } else if (id == R.id.nav_send) {
            Intent request = new Intent(MainActivity.this, Reqeust_page.class);
            request.putExtra("storename", Storename);
            //Log.d("Storename",Storename);
            startActivity(request);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

   /* // content_main 의 coor 뭐시기 구현하기 위한 스크롤 바
    public static class TestViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public TestViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(android.R.id.text1);
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }*/
   private class GetData_Review extends AsyncTask<String, Void, String> {

       ProgressDialog progressDialog;
       String errorString = null;

       @Override
       protected void onPreExecute() {
           super.onPreExecute();

           progressDialog = ProgressDialog.show(MainActivity.this,
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
           String name = params[1];
           //Log.d("name : ", ""+name);
           String postParameters = "&name=" + name;
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
        String TAG_REVIEW = "review";
        String TAG_BODY = "body";
        String TAG_WRITENAME = "writename";




        try {
            JSONObject jsonObject = new JSONObject(mJsonString);
            JSONArray jsonArray = jsonObject.getJSONArray(TAG_JSON);

            for(int i=0;i<jsonArray.length();i++) {
                JSONObject item = jsonArray.getJSONObject(i);

                String name = item.getString(TAG_NAME);
                String review = item.getString(TAG_REVIEW);
                String body = item.getString(TAG_BODY);
                String writename = item.getString(TAG_WRITENAME);


                ReviewData reviewdata = new ReviewData();
                reviewdata.setStorename(name);
                reviewdata.setBody(body);
                reviewdata.setStar(review);
                reviewdata.setWritename(writename);
                reviewArrayList.add(reviewdata);
                Review_mAdapter.notifyDataSetChanged();
            }



        } catch (JSONException e) {

            Log.d(TAG, "showResult : ", e);
        }


    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        if (requestCode == PERMISSIONS_ACCESS_FINE_LOCATION
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            isAccessFineLocation = true;

        } else if (requestCode == PERMISSIONS_ACCESS_COARSE_LOCATION
                && grantResults[0] == PackageManager.PERMISSION_GRANTED){

            isAccessCoarseLocation = true;
        }

        if (isAccessFineLocation && isAccessCoarseLocation) {
            isPermission = true;
        }
    }

    private void callPermission() {
        // Check the SDK version and whether the permission is already granted or not.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            requestPermissions(
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_ACCESS_FINE_LOCATION);

        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED){

            requestPermissions(
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                    PERMISSIONS_ACCESS_COARSE_LOCATION);
        } else {
            isPermission = true;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
       super.onActivityResult(requestCode,resultCode,data);
        Log.d("보자", "1");
       if(resultCode != RESULT_OK){
           Log.d("보자" , "2");
       }else {
            if(requestCode == 3000){
                Log.d("보자" , "3");
                Storename = data.getStringExtra("name1");
                Log.d("보자" , Storename);
                Intro = data.getStringExtra("intro1");
                Log.d("보자" , Intro);
                Urimage = data.getStringExtra("urimage1");
                Log.d("보자" , Urimage);
                Advan = data.getStringExtra("advan1");
                Log.d("보자" , Advan);
                Phonenum = data.getStringExtra("phonenum1");
                Log.d("보자" , Phonenum);
                Opentime = data.getStringExtra("opentime1");
                Log.d("보자" , Opentime);
                Whereroom = data.getStringExtra("whereroom1");
                Log.d("보자" , Whereroom);
                Wheremap = data.getStringExtra("wheremap1");
                Log.d("보자" , Wheremap);
                Comadd = data.getStringExtra("comadd1");
                Log.d("보자" , Comadd);
                Comnum = data.getStringExtra("comnum1");
                Log.d("보자" , Comnum);
                Timeroom = data.getStringExtra("timeroom1");
                Log.d("보자" , Timeroom);
                Coinroom = data.getStringExtra("coinroom1");
                Log.d("보자" , Coinroom);
                Holiday = data.getStringExtra("holiday1");
                Log.d("보자" , Holiday);
                Latitude = data.getStringExtra("latitude1");
                Log.d("보자" , Latitude);
                Longitude = data.getStringExtra("longitude1");
                Log.d("보자" , Longitude);
                //Log.d("보자" , Intro);
            }
       }
    }

}
