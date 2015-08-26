package com.goodle.mapia.common;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.goodle.mapia.R;
import com.goodle.mapia.home.HomeActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolygonOptions;

import java.util.ArrayList;

/**
 * Created by daehyun on 15. 8. 21..
 */
public class BaseMapFragment extends Fragment implements View.OnClickListener, LocationListener , GoogleMap.OnMapClickListener, GoogleMap.OnCameraChangeListener {
    protected GoogleMap gMap;
    protected LocationManager locationManager;
    protected Activity mActivity;
    protected LatLng currentLatlng =  new LatLng(37.498360, 127.027400);;
    protected boolean cameraMoveWhenCreate = false;
    protected float currentZoom=-1, lastZoom=-1;

    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        mActivity = activity;
        locationManager = (LocationManager) mActivity.getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 10000, 50, this);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 50, this);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_my_map, container, false);
        Toast.makeText(getActivity(), "MyMapFragment", Toast.LENGTH_SHORT).show();

        SupportMapFragment supportMapFragment = (SupportMapFragment)getChildFragmentManager().findFragmentById(R.id.map);
        gMap = supportMapFragment.getMap();
        gMap.animateCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition.Builder().target(currentLatlng).zoom(16).build()));
        gMap.setOnCameraChangeListener(this);
        drawColorBlock();
        return v;
    }

    private void drawColorBlock(){
        ArrayList<PolygonOptions> blocks = HomeActivity.getMyBlocks();
        for(int i=0;i<blocks.size();i++){
            gMap.addPolygon(blocks.get(i));
        }
    }



    @Override
    public void onLocationChanged(Location location) {
        HomeActivity.currentLatlng = new LatLng(location.getLatitude(), location.getLongitude());

        SharedPreferences prefs = getActivity().getSharedPreferences("Location", getActivity().MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        editor.putFloat("latitude", (float)(HomeActivity.currentLatlng.latitude));
        editor.putFloat("longitude", (float) (HomeActivity.currentLatlng.longitude));
        editor.commit();

        if (cameraMoveWhenCreate == false) {
            if (HomeActivity.cameraLatlng != null)
                this.gMap.animateCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition.Builder().target(currentLatlng).zoom(16).build()));
            cameraMoveWhenCreate = true;
        }
        //locationManager.removeUpdates(this);

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }
    @Override
    public void onProviderEnabled(String provider) {
    }
    @Override
    public void onProviderDisabled(String provider) {
    }

    @Override
    public void onClick(View v) {
    }



    @Override
    public void onMapClick(LatLng point) {
//        Intent intent = new Intent(getActivity(), PostActivity.class);
//		String address = ConvertPointToLocation(point);
//        intent.putExtra("latlng",point);
//		String placeName = getPlaceName();


//        startActivityForResult(intent, 0);
        // TODO Auto-generated method stub
    }

    @Override
    public void onCameraChange(CameraPosition cameraPosition) {
        HomeActivity.cameraLatlng = cameraPosition.target;
        HomeActivity.cameraZoom = cameraPosition.zoom;
        if(currentLatlng==null) currentLatlng = cameraPosition.target;
        if(currentZoom==-1){
            currentZoom = cameraPosition.zoom;
            lastZoom = currentZoom;
            return;
        }

        if(cameraPosition.zoom!=lastZoom && cameraPosition.zoom-currentZoom<2){
            lastZoom = cameraPosition.zoom;
            return;
        }

        currentZoom = cameraPosition.zoom;
    }


}
