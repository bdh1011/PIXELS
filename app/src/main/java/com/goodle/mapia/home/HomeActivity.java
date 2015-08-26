package com.goodle.mapia.home;

import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;

import com.goodle.mapia.R;
import com.goodle.mapia.addPost.AddPostFragment;
import com.goodle.mapia.alert.AlertFragment;
import com.goodle.mapia.groupMap.GroupMapFragment;
import com.goodle.mapia.myMap.MyMapFragment;
import com.goodle.mapia.newsFeed.NewsFeedFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolygonOptions;

import java.util.ArrayList;


public class HomeActivity extends FragmentActivity implements View.OnClickListener{//}, LocationListener{

    static ArrayList<PolygonOptions> myBlocks = new ArrayList<PolygonOptions>();
    LocationManager locationManager;
    public static LatLng currentLatlng = new LatLng(37.498360, 127.027400);
    public static LatLng cameraLatlng = new LatLng(37.498360, 127.027400);
    public static float cameraZoom = 8;


    int currentFragmentIndex = 3;
    public final static int FragmentAddPost = 0;
    public final static int FragmentAlert = 1;
    public final static int FragmentGroupMap = 2;
    public final static int FragmentMyMap = 3;

    public final static int FragmentNewsFeed = 4;
    public Fragment currentFragment;

    public HomeActivity(){
        super();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button btn_add_post = (Button)findViewById(R.id.btn_add_post);
        btn_add_post.setOnClickListener(this);
        Button btn_alert = (Button)findViewById(R.id.btn_alert);
        btn_alert.setOnClickListener(this);
        Button btn_group_map = (Button)findViewById(R.id.btn_group_map);
        btn_group_map.setOnClickListener(this);
        Button btn_my_map = (Button)findViewById(R.id.btn_my_map);
        btn_my_map.setOnClickListener(this);
        Button btn_news_feed = (Button)findViewById(R.id.btn_news_feed);
        btn_news_feed.setOnClickListener(this);


        fragmentReplace(currentFragmentIndex);

    }

    private void fragmentReplace(int newFragmentIndex){
        currentFragment = getFragment(newFragmentIndex);
        final FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction();
        transaction.replace(R.id.ll_fragment, currentFragment);
        transaction.commit();
    }

    private Fragment getFragment(int newFragmentIndex){
        currentFragment = null;
        switch(newFragmentIndex){
            case FragmentAddPost:
                currentFragment = new AddPostFragment();
                break;
            case FragmentAlert:
                currentFragment = new AlertFragment();
                break;
            case FragmentGroupMap:
                currentFragment = new GroupMapFragment();
                break;
            case FragmentMyMap:
                currentFragment = new MyMapFragment();
                break;
            case FragmentNewsFeed:
                currentFragment = new NewsFeedFragment();
                break;
        }
        return currentFragment;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_add_post:
                currentFragmentIndex = FragmentAddPost;
                fragmentReplace(currentFragmentIndex);
                break;
            case R.id.btn_alert:
                currentFragmentIndex = FragmentAlert;
                fragmentReplace(currentFragmentIndex);
                break;
            case R.id.btn_group_map:
                currentFragmentIndex = FragmentGroupMap;
                fragmentReplace(currentFragmentIndex);
                break;
            case R.id.btn_my_map:
                currentFragmentIndex = FragmentMyMap;
                fragmentReplace(currentFragmentIndex);
                break;
            case R.id.btn_news_feed:
                currentFragmentIndex = FragmentNewsFeed;
                fragmentReplace(currentFragmentIndex);
                break;
        }
    }

    public static LatLng getCurrentLatlng(){
        return currentLatlng;
    }

    public static ArrayList<PolygonOptions> getMyBlocks(){ return myBlocks;}

    public static void addMyBlocks(PolygonOptions po){
        myBlocks.add(po);
    }

//    @Override
//    public void onLocationChanged(Location location) {
//        if (location.getProvider().equals(LocationManager.GPS_PROVIDER)) {
//        //Gps 위치제공자에 의한 위치변화. 오차범위가 좁다.
//            double longitude = location.getLongitude();    //경도
//            double latitude = location.getLatitude();         //위도
//            float accuracy = location.getAccuracy();        //신뢰도
//            currentLatlng = new LatLng(location.getLatitude(), location.getLongitude());
//
//            SharedPreferences prefs = getSharedPreferences("Location", MODE_PRIVATE);
//            SharedPreferences.Editor editor = prefs.edit();
//
//            editor.putFloat("latitude", (float)(currentLatlng.latitude));
//            editor.putFloat("longitude", (float)(currentLatlng.longitude));
//            editor.commit();
//
//            if(currentFragmentIndex == 0){
//                ((AddPostFragment)currentFragment).updateLocation(currentLatlng);
//            }
//        }
//        else {
//        //Network 위치제공자에 의한 위치변화
//        //Network 위치는 Gps에 비해 정확도가 많이 떨어진다.
//        }
//
//
//    }
//    @Override
//    public void onStatusChanged(String provider, int status, Bundle extras) {
//    }
//    @Override
//    public void onProviderEnabled(String provider) {
//    }
//    @Override
//    public void onProviderDisabled(String provider) {
//    }

}
