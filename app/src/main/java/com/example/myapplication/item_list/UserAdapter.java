package com.example.myapplication.item_list;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.link_php_db.SingData;

import java.util.ArrayList;


public class UserAdapter extends RecyclerView.Adapter<UserAdapter.CustomViewHolder> {

    private ArrayList<SingData> mList = null;
    private Activity context = null;


    public UserAdapter(Activity context, ArrayList<SingData> list) {
        this.context = context;
        this.mList = list;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        protected TextView Kakaoid;
        protected TextView name;
        protected TextView intro;
        protected TextView urimage;
        protected TextView advan;
        protected TextView opentime;
        protected TextView holiday;
        protected TextView phonenum;
        protected TextView whereroom;
        protected TextView wheremap;
        protected TextView comadd;
        protected TextView comnum;
        protected TextView review;
        protected TextView review_num;


        public CustomViewHolder(View view) {
            super(view);
            this.intro = (TextView) view.findViewById(R.id.textView_list_intro);
            this.advan = (TextView) view.findViewById(R.id.textView_list_advan);
            this.opentime = (TextView) view.findViewById(R.id.textView_list_opentime);
            this.holiday = (TextView) view.findViewById(R.id.textView_list_holiday);
            this.phonenum = (TextView) view.findViewById(R.id.textView_list_phonenum);
            this.whereroom = (TextView) view.findViewById(R.id.textView_list_whereroom);
            this.comadd = (TextView) view.findViewById(R.id.textView_list_comadd);
            this.comnum = (TextView) view.findViewById(R.id.textView_list_comnum);
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