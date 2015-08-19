package com.goodle.mapia.addPost;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.goodle.mapia.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;


public class LocationPicker extends Activity implements View.OnClickListener{
    LatLng center_location;
    float center_zoom;
    GoogleMap gMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_picker);

        Button btn_close_location_picker = (Button)findViewById(R.id.btn_close_location_picker);
        btn_close_location_picker.setOnClickListener(this);

        Bundle bundle = getIntent().getBundleExtra("bundle");
        center_location = bundle.getParcelable("current_location");
        center_zoom = bundle.getFloat("current_zoom");

        MapFragment mapFragment = (MapFragment)getFragmentManager().findFragmentById(R.id.map);
        gMap = mapFragment.getMap();
        gMap.animateCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition.Builder().target(center_location).zoom(16).build()));
        gMap.setOnCameraChangeListener(new GoogleMap.OnCameraChangeListener() {
            @Override
            public void onCameraChange(CameraPosition cameraPosition) {
                center_location = new LatLng(cameraPosition.target.latitude, cameraPosition.target.longitude);
                center_zoom = cameraPosition.zoom;
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btn_close_location_picker:
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putParcelable("location", center_location);
                bundle.putFloat("zoom", center_zoom);
                intent.putExtra("bundle", bundle);
                setResult(RESULT_OK, intent);
                finish();
                break;
        }
    }
}
