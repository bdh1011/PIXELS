package com.goodle.mapia.addPost;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.goodle.mapia.R;
import com.goodle.mapia.home.HomeActivity;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.model.LatLng;

/**
 * Created by JahyunKim on 15. 8. 16..
 */
public class AddPostFragment extends Fragment implements View.OnClickListener{
    TextView txt_center_loc, txt_rect_size;
    //private static int LAUNCH_PlacePicker = 1;
    private static int REQUEST_LOCATION_PICKER = 1;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_add_post, container, false);
        Toast.makeText(getActivity(), "AddPostFragment", Toast.LENGTH_SHORT).show();
        Button btn_place_picker = (Button)v.findViewById(R.id.btn_place_picker);
        btn_place_picker.setOnClickListener(this);
        txt_center_loc = (TextView)v.findViewById(R.id.txt_center_loc);
        txt_rect_size = (TextView)v.findViewById(R.id.txt_rect_size);
        return v;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUEST_LOCATION_PICKER){
            if(resultCode==Activity.RESULT_OK){
                Bundle bundle = data.getParcelableExtra("bundle");
                LatLng location = bundle.getParcelable("location");
                Toast.makeText(this.getActivity(), "location pick success", Toast.LENGTH_LONG).show();
                txt_center_loc.setText(location.latitude + "/" + location.longitude);
                txt_rect_size.setText("");
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_place_picker:
                Intent intent = new Intent(this.getActivity(), LocationPicker.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable("current_location", HomeActivity.getCurrentLocation());
                bundle.putFloat("current_zoom",16);
                intent.putExtra("bundle",bundle);
                startActivityForResult(intent,REQUEST_LOCATION_PICKER);
                break;
        }
    }
}
