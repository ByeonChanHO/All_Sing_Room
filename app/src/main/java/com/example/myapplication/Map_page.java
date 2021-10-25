package com.example.myapplication;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;


import com.example.myapplication.link_php_db.SingData;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

import java.util.ArrayList;


public class Map_page extends RecyclerView.Adapter<Map_page.CustomViewHolder> {

    private ArrayList<SingData> mList = null;
    private Activity context = null;


    public Map_page(Activity context, ArrayList<SingData> list) {
        this.context = context;
        this.mList = list;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder { ;
        protected MapView mapView;
        protected ViewGroup mapViewContainer;
        protected MapPoint mapPoint;
        protected MapPOIItem marker;


        public CustomViewHolder(View view) {
            super(view);
            this.mapView = new MapView(context);
            this.mapViewContainer = (ViewGroup) view.findViewById(R.id.mapact);


        }
    }


    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.map_page, null);
        CustomViewHolder viewHolder = new CustomViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final CustomViewHolder viewholder, int position) {


        //viewholder.name = mList.get(position).getMember_name();
        //viewholder.mapView = new MapView(context);
        viewholder.mapView.setDaumMapApiKey("@string/kakao_app_key");
        viewholder.mapViewContainer.addView(viewholder.mapView);
        viewholder.mapPoint = MapPoint.mapPointWithGeoCoord(37.5514579595, 126.951949155);
        viewholder.mapView.setMapCenterPoint(viewholder.mapPoint, true);
        //true면 앱 실행 시 애니메이션 효과가 나오고 false면 애니메이션이 나오지않음.
        viewholder.marker = new MapPOIItem();

        viewholder.marker.setItemName("한세사이버보안고등학교");
        viewholder.marker.setTag(0);
        viewholder.marker.setMapPoint(viewholder.mapPoint);
        // 기본으로 제공하는 BluePin 마커 모양.
        viewholder.marker.setMarkerType(MapPOIItem.MarkerType.BluePin);
        // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.
        viewholder.marker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);
        viewholder.mapView.addPOIItem(viewholder.marker);


    }

    @Override
    public int getItemCount() {
        return (null != mList ? mList.size() : 0);
    }

}