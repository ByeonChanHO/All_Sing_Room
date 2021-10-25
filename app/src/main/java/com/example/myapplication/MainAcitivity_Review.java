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
import android.widget.ImageView;
import android.widget.TextView;


import com.example.myapplication.link_php_db.ReviewData;
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


public class MainAcitivity_Review extends RecyclerView.Adapter<MainAcitivity_Review.CustomViewHolder> {

    private ArrayList<ReviewData> mList = null;
    private Activity context = null;


    public MainAcitivity_Review(Activity context, ArrayList<ReviewData> list) {
        this.context = context;
        this.mList = list;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder { ;
        protected TextView writename;
        protected ImageView star_img;
        protected TextView body;



        public CustomViewHolder(View view) {
            super(view);
            this.writename =(TextView)view.findViewById(R.id.writename_text);
            this.star_img =(ImageView)view.findViewById(R.id.star_Img);
            this.body =(TextView) view.findViewById(R.id.body_text);

        }
    }


    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.review_page, null);
        CustomViewHolder viewHolder = new CustomViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final CustomViewHolder viewholder, int position) {
        Log.e("찬호", "가자  ");
        Log.e("찬호", "가자1  ");
        viewholder.writename.setText(mList.get(position).getWritename());
        Log.e("찬호", "가자2");
        viewholder.body.setText(mList.get(position).getBody());
        Log.e("찬호", "가자3");
        String temp = mList.get(position).getStar();
        Log.e("찬호", "가자4");
        int Temp = Integer.parseInt(temp);
        if(Temp > 450){
            viewholder.star_img.setImageResource(R.drawable.star5_0);
        }else if(Temp > 400){
            viewholder.star_img.setImageResource(R.drawable.star4_5);
        }else if(Temp > 350){
            viewholder.star_img.setImageResource(R.drawable.star4_0);
        }else if(Temp > 300){
            viewholder.star_img.setImageResource(R.drawable.star3_5);
        }else if(Temp > 250){
            viewholder.star_img.setImageResource(R.drawable.star3_0);
        }else if(Temp > 200){
            viewholder.star_img.setImageResource(R.drawable.star2_5);
        }else if(Temp > 150){
            viewholder.star_img.setImageResource(R.drawable.star2_0);
        }else if(Temp > 100){
            viewholder.star_img.setImageResource(R.drawable.star1_5);
        }else if(Temp > 50){
            viewholder.star_img.setImageResource(R.drawable.star1_0);
        }else if(Temp > 0){
            viewholder.star_img.setImageResource(R.drawable.star0_5);
        }



    }

    public int getItemCount() {
        return (null != mList ? mList.size() : 0);
    }


}