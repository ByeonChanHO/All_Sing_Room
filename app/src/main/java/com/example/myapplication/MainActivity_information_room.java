package com.example.myapplication;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;


import com.example.myapplication.link_php_db.SingData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


public class MainActivity_information_room extends RecyclerView.Adapter<MainActivity_information_room.CustomViewHolder> {

    private ArrayList<SingData> mList = null;
    private Activity context = null;
    private static String IP_ADDRESS = "192.168.0.14";
    private static String TAG = "phptest";
    private String mJsonString;


    public MainActivity_information_room(Activity context, ArrayList<SingData> list) {
        this.context = context;
        this.mList = list;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder { ;
        protected Button save;
        protected ImageButton ten_up;
        protected ImageButton one_up;
        protected TextView ten_num;
        protected TextView one_num;
        protected ImageButton ten_down;
        protected ImageButton one_down;
        protected TextView oneandthree_num;
        protected ImageButton oneandthree_text_left;
        protected ImageButton oneandthree_text_right;
        protected TextView four_text_num;
        protected ImageButton four_text_left;
        protected ImageButton four_text_right;
        protected TextView six_text_num;
        protected ImageButton six_text_left;
        protected ImageButton six_text_right;
        protected TextView eigt_text_num;
        protected ImageButton eigt_text_left;
        protected ImageButton eigt_text_right;
        protected TextView ten_text_num;
        protected ImageButton ten_text_left;
        protected ImageButton ten_text_right;
        protected TextView tentwo_text_num;
        protected ImageButton tentwo_text_left;
        protected ImageButton tentwo_text_right;
        protected String name;


        public CustomViewHolder(View view) {
            super(view);
            this.save =(Button)view.findViewById(R.id.room_save);
            this.ten_up =(ImageButton)view.findViewById(R.id.ten_up);
            this.one_up =(ImageButton)view.findViewById(R.id.one_up);
            this.ten_down =(ImageButton)view.findViewById(R.id.ten_down);
            this.one_down =(ImageButton)view.findViewById(R.id.one_down);
            this.oneandthree_text_left =(ImageButton)view.findViewById(R.id.oneandthree_text_left);
            this.oneandthree_text_right =(ImageButton)view.findViewById(R.id.oneandthree_text_right);
            this.four_text_left =(ImageButton)view.findViewById(R.id.four_text_left);
            this.four_text_right =(ImageButton)view.findViewById(R.id.four_text_right);
            this.six_text_left =(ImageButton)view.findViewById(R.id.six_text_left);
            this.six_text_right =(ImageButton)view.findViewById(R.id.six_text_right);
            this.eigt_text_left =(ImageButton)view.findViewById(R.id.eigt_text_left);
            this.eigt_text_right =(ImageButton)view.findViewById(R.id.eigt_text_right);
            this.ten_text_left =(ImageButton)view.findViewById(R.id.ten_text_left);
            this.ten_text_right =(ImageButton)view.findViewById(R.id.ten_text_right);
            this.tentwo_text_left =(ImageButton)view.findViewById(R.id.tentwo_text_left);
            this.tentwo_text_right =(ImageButton)view.findViewById(R.id.tentwo_text_right);

            this.ten_num = (TextView)view.findViewById(R.id.ten_num);
            this.one_num = (TextView)view.findViewById(R.id.one_num);
            this.oneandthree_num = (TextView)view.findViewById(R.id.oneandthree_num);
            this.four_text_num = (TextView)view.findViewById(R.id.four_text_num);
            this.six_text_num = (TextView)view.findViewById(R.id.six_text_num);
            this.eigt_text_num = (TextView)view.findViewById(R.id.eigt_text_num);
            this.ten_text_num = (TextView)view.findViewById(R.id.ten_text_num);
            this.tentwo_text_num = (TextView)view.findViewById(R.id.tentwo_text_num);


        }
    }


    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.information_room, null);
        CustomViewHolder viewHolder = new CustomViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final CustomViewHolder viewholder, int position) {
        viewholder.name = mList.get(position).getMember_name();
        viewholder.ten_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Temp = (viewholder.ten_num.getText()).toString();
                int temp = Integer.parseInt(Temp);
                temp = (temp + 1)%10;
                Temp = String.valueOf(temp);
                viewholder.ten_num.setText(Temp);
            }
        });
        viewholder.one_up.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String Temp = (viewholder.one_num.getText()).toString();
                int temp = Integer.parseInt(Temp);
                temp = (temp + 1)%10;
                Temp = String.valueOf(temp);
                viewholder.one_num.setText(Temp);
            }
        });
        viewholder.ten_down.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String Temp = (viewholder.ten_num.getText()).toString();
                int temp = Integer.parseInt(Temp);
                temp = (temp - 1)%10;
                if(temp < 0){temp = 9;}
                Temp = String.valueOf(temp);
                viewholder.ten_num.setText(Temp);

            }
        });
        viewholder.one_down.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String Temp = (viewholder.one_num.getText()).toString();
                int temp = Integer.parseInt(Temp);
                temp = (temp - 1)%10;
                if(temp < 0){temp = 9;}
                Temp = String.valueOf(temp);
                viewholder.one_num.setText(Temp);

            }
        });
        viewholder.oneandthree_text_right.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String Temp = (viewholder.oneandthree_num.getText()).toString();
                int temp = Integer.parseInt(Temp);
                temp = temp + 1;
                Temp = String.valueOf(temp);
                viewholder.oneandthree_num.setText(Temp);

            }
        });
        viewholder.oneandthree_text_left.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String Temp = (viewholder.oneandthree_num.getText()).toString();
                int temp = Integer.parseInt(Temp);
                temp = temp - 1;
                if(temp < 0){temp = 9;}
                Temp = String.valueOf(temp);
                viewholder.oneandthree_num.setText(Temp);

            }
        });
        viewholder.four_text_right.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String Temp = (viewholder.four_text_num.getText()).toString();
                int temp = Integer.parseInt(Temp);
                temp = temp + 1;
                Temp = String.valueOf(temp);
                viewholder.four_text_num.setText(Temp);

            }
        });
        viewholder.four_text_left.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String Temp = (viewholder.four_text_num.getText()).toString();
                int temp = Integer.parseInt(Temp);
                temp = temp - 1;
                if(temp < 0){temp = 9;}
                Temp = String.valueOf(temp);
                viewholder.four_text_num.setText(Temp);

            }
        });
        viewholder.six_text_right.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String Temp = (viewholder.six_text_num.getText()).toString();
                int temp = Integer.parseInt(Temp);
                temp = temp + 1;
                Temp = String.valueOf(temp);
                viewholder.six_text_num.setText(Temp);

            }
        });
        viewholder.six_text_left.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String Temp = (viewholder.six_text_num.getText()).toString();
                int temp = Integer.parseInt(Temp);
                temp = temp - 1;
                if(temp < 0){temp = 9;}
                Temp = String.valueOf(temp);
                viewholder.six_text_num.setText(Temp);

            }
        });
        viewholder.eigt_text_right.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String Temp = (viewholder.eigt_text_num.getText()).toString();
                int temp = Integer.parseInt(Temp);
                temp = temp + 1;
                Temp = String.valueOf(temp);
                viewholder.eigt_text_num.setText(Temp);

            }
        });
        viewholder.eigt_text_left.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String Temp = (viewholder.eigt_text_num.getText()).toString();
                int temp = Integer.parseInt(Temp);
                temp = temp - 1;
                if(temp < 0){temp = 9;}
                Temp = String.valueOf(temp);
                viewholder.eigt_text_num.setText(Temp);

            }
        });
        viewholder.ten_text_right.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String Temp = (viewholder.ten_text_num.getText()).toString();
                int temp = Integer.parseInt(Temp);
                temp = temp + 1;
                Temp = String.valueOf(temp);
                viewholder.ten_text_num.setText(Temp);

            }
        });
        viewholder.ten_text_left.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String Temp = (viewholder.ten_text_num.getText()).toString();
                int temp = Integer.parseInt(Temp);
                temp = temp - 1;
                if(temp < 0){temp = 9;}
                Temp = String.valueOf(temp);
                viewholder.ten_text_num.setText(Temp);

            }
        });
        viewholder.tentwo_text_right.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String Temp = (viewholder.tentwo_text_num.getText()).toString();
                int temp = Integer.parseInt(Temp);
                temp = temp + 1;
                Temp = String.valueOf(temp);
                viewholder.tentwo_text_num.setText(Temp);

            }
        });
        viewholder.tentwo_text_left.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String Temp = (viewholder.tentwo_text_num.getText()).toString();
                int temp = Integer.parseInt(Temp);
                temp = temp - 1;
                if(temp < 0){temp = 9;}
                Temp = String.valueOf(temp);
                viewholder.tentwo_text_num.setText(Temp);

            }
        });
        viewholder.save.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String Temp1 = (viewholder.ten_num.getText()).toString();
                String Temp2 = (viewholder.one_num.getText()).toString();
                String Temp3 = Temp1 + Temp2;
                String name = viewholder.name;
                String waitperson = Temp3;
                String oneandthree = (viewholder.oneandthree_num.getText()).toString();
                String four = (viewholder.four_text_num.getText()).toString();
                String six = (viewholder.six_text_num.getText()).toString();
                String eigt = (viewholder.eigt_text_num.getText()).toString();
                String ten = (viewholder.ten_text_num.getText()).toString();
                String tentwo = (viewholder.tentwo_text_num.getText()).toString();

                InsertData task = new InsertData();
                task.execute("http://" + IP_ADDRESS + "/insertwaitandroom.php",name,waitperson,oneandthree,four,six,eigt,ten,tentwo);
            }
        });



    }

    @Override
    public int getItemCount() {
        return (null != mList ? mList.size() : 0);
    }

    private class InsertData extends AsyncTask<String, Void, String> {

        ProgressDialog progressDialog;
        String errorString = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(context,
                    "Please Wait", null, true, true);
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            progressDialog.dismiss();
            Log.d(TAG, "response - " + result);

        }


        @Override
        protected String doInBackground(String... params) {

            String serverURL = params[0];
            String name = params[1];
            String waitperson = params[2];
            String oneandthree = params[3];
            String four = params[4];
            String six = params[5];
            String eigt = params[6];
            String ten = params[7];
            String tentwo = params[8];
            //Log.d("name : ", ""+name);
            String postParameters = "&name=" + name + "&waitperson="+waitperson +"&oneandthree=" + oneandthree +"&four=" + four +"&six=" + six
                    +"&eigt=" + eigt + "&ten=" + ten + "&tentwo=" + tentwo;
            //Log.d(" postParameters  : ", ""+ postParameters );


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

                Log.d(TAG, "InsertData : Error ", e);
                errorString = e.toString();

                return null;
            }

        }
    }



}