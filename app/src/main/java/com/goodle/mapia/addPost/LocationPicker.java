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
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;


public class LocationPicker extends Activity implements View.OnClickListener{
    LatLng center_location;
    GoogleMap gMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_picker);

        Button btn_close_location_picker = (Button)findViewById(R.id.btn_close_location_picker);
        btn_close_location_picker.setOnClickListener(this);

        Bundle bundle = getIntent().getBundleExtra("bundle");
        center_location = bundle.getParcelable("current_location");

        MapFragment mapFragment = (MapFragment)getFragmentManager().findFragmentById(R.id.map);
        gMap = mapFragment.getMap();
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btn_close_location_picker:
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putParcelable("location", center_location);
                intent.putExtra("bundle", bundle);
                setResult(RESULT_OK, intent);
                finish();
                break;
        }
    }
}
