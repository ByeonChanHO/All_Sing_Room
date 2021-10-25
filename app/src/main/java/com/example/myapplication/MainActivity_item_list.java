package com.example.myapplication;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import com.example.myapplication.link_php_db.SingData;

import org.w3c.dom.Text;

import java.util.ArrayList;


public class MainActivity_item_list extends RecyclerView.Adapter<MainActivity_item_list.CustomViewHolder> {

    private ArrayList<SingData> mList = null;
    private Activity context = null;


    public MainActivity_item_list(Activity context, ArrayList<SingData> list) {
        this.context = context;
        this.mList = list;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder { ;
        protected TextView name;
        protected TextView intro;
        protected TextView urimage;
        protected TextView advan;
        protected TextView phonenum;
        protected TextView opentime;
        protected TextView holiday;
        protected TextView whereroom;
        protected TextView wheremap;
        protected TextView comadd;
        protected TextView comnum;
        protected TextView review;
        protected TextView review_num;
        protected TextView longitude;
        protected TextView latitude;
        protected TextView timeroom;
        protected TextView coinroom;



        public CustomViewHolder(View view) {
            super(view);
            this.intro = (TextView)view.findViewById(R.id.textView_list_intro);
            this.advan = (TextView)view.findViewById(R.id.textView_list_advan);
            this.opentime = (TextView)view.findViewById(R.id.textView_list_opentime);
            this.holiday = (TextView)view.findViewById(R.id.textView_list_holiday);
            this.phonenum = (TextView)view.findViewById(R.id.textView_list_phonenum);
            this.whereroom = (TextView)view.findViewById(R.id.textView_list_whereroom);
            this.comadd = (TextView)view.findViewById(R.id.textView_list_comadd);
            this.comnum = (TextView)view.findViewById(R.id.textView_list_comnum);
            this.wheremap = (TextView)view.findViewById(R.id.textView_list_money);
        }
    }


    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list, null);
        CustomViewHolder viewHolder = new CustomViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder viewholder, int position) {

        viewholder.intro.setText(mList.get(position).getMember_intro());
        viewholder.advan.setText(mList.get(position).getMember_advan());
        viewholder.opentime.setText(mList.get(position).getMember_opentime());
        viewholder.wheremap.setText(mList.get(position).getMember_wheremap());
        viewholder.holiday.setText(mList.get(position).getMember_holiday());
        viewholder.phonenum.setText(mList.get(position).getMember_phonenum());
        viewholder.whereroom.setText(mList.get(position).getMember_whereroom());
        viewholder.comadd.setText(mList.get(position).getMember_comadd());
        viewholder.comnum.setText(mList.get(position).getMember_comnum());

    }

    @Override
    public int getItemCount() {
        return (null != mList ? mList.size() : 0);
    }

}