package com.goodle.mapia.addPost;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.goodle.mapia.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;


public class LocationPickerActivity extends Activity implements View.OnClickListener{
    LatLng center_location;
    float center_zoom;
    double center_width, center_height;
    Point screenLocation, screenLocationTmp;
    int center_color = 0;
    GoogleMap gMap;
    ImageView img_location_rect;
    ImageView[] colors = new ImageView[6];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_picker);

        img_location_rect = (ImageView)findViewById(R.id.img_location_rect);
        colors[0] = (ImageView)findViewById(R.id.color_red);
        colors[1] = (ImageView)findViewById(R.id.color_orange);
        colors[2] = (ImageView)findViewById(R.id.color_green);
        colors[3] = (ImageView)findViewById(R.id.color_blue);
        colors[4] = (ImageView)findViewById(R.id.color_white);
        colors[5] = (ImageView)findViewById(R.id.color_black);
        for(int i=0;i<6;i++){
            colors[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ColorDrawable cd = (ColorDrawable)v.getBackground();
                    center_color = cd.getColor();
                    img_location_rect.setBackgroundColor(cd.getColor());
                    img_location_rect.setAlpha((float)0.5);
                }
            });
        }
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
                screenLocation = gMap.getProjection().toScreenLocation(center_location);
                screenLocationTmp = gMap.getProjection().toScreenLocation(center_location);
                screenLocation.x += dipToPixels(getApplicationContext(),35);
                screenLocation.y += dipToPixels(getApplicationContext(),35);
                center_width = gMap.getProjection().fromScreenLocation(screenLocation).longitude
                        - gMap.getProjection().fromScreenLocation(screenLocationTmp).longitude;
                center_height = gMap.getProjection().fromScreenLocation(screenLocation).latitude
                        - gMap.getProjection().fromScreenLocation(screenLocationTmp).latitude;
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
                if(center_color==0){
                    Toast.makeText(getApplicationContext(),"Select Color", Toast.LENGTH_SHORT);
                    break;
                }
                bundle.putInt("color",center_color);
                bundle.putDouble("width", center_width);
                bundle.putDouble("height",center_height);
                intent.putExtra("bundle", bundle);
                setResult(RESULT_OK, intent);
                finish();
                break;
        }
    }

    public static float dipToPixels(Context context, float dipValue) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dipValue, metrics);
    }
}