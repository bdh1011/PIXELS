package com.goodle.mapia.volley;

import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by daehyun on 15. 6. 8..
 */

public class PostJsonRequest extends JSONObject{
    final String content;
    final double lat, lng;
    String maptype;
    ArrayList<String> filelist = new ArrayList<String>();

    public PostJsonRequest(String mapType, String content, LatLng latlng, ArrayList<String> filelist){
        this.content = content;
        this.lat = latlng.latitude;
        this.lng = latlng.longitude;
        this.filelist = filelist;
        this.maptype = mapType;
        Log.i("file", filelist.toString());
    }

    public PostJsonRequest(String mapType, String content, LatLng latlng){
        this.maptype = mapType;
        this.content = content;
        this.lat = latlng.latitude;
        this.lng = latlng.longitude;
    }
}
